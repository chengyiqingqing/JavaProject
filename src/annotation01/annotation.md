# 编程思想之注解

笔者喜欢从三个角度来写技术文章。`what`，`why`，`how`。

讲我理解的注解前，首先简单看下`java`自己提供的`Junit`注解单元测试框架。`@Test`就是实现了Annotation的`注解`，用于单元测试

```java
public class Testable {

    public static void execute() {
        System.out.println("Executing...");
    }

    @Test
    public void testExecute2() {
        System.out.println("testExecute2...");
        execute();
    }

    @Test
    public void testExecute1() {
        System.out.println("testExecute1...");
        execute();
    }

}
```


## 我理解的注解

文章中的所有代码可参考仓库代码。

### 一.注解的概念

> 注解是什么

注解又称为标注，是jdk5.0引入的一种注释机制。

> 为什么会产生注解的语法

Java语言中的类，方法，变量，参数和包等都可以被标注。和 Javadoc 不同，Java 标注可以通过反射获取标注内容。在编译器生成类文件时，标注可以被嵌入到字节码中。
Java 虚拟机可以保留标注内容，在运行时可以获取到标注内容 。 当然它也支持自定义 Java 标注。

### 一.自定义注解

在 `AndroidStudio` 中创建一个纯净的`Module` 文件。

**Gradle会默认在配置初始化前**所以如果`Module` 命名为 `buildSrc` 那么不需要在 `Project` 级别的 `build.gradle` 文件中使用 `classpath` 引入。  

下面我们介绍的是自定义的 `Plugin` 插件，是编译之后需要引入项目的。

> 类继承`Plugin` 

```java
public class AmsPlugin
```

> `build.gradle` 文件配置

![](./uploadArchives.png)

> 配置`properties`

在 `/src/main/resources/META-INF/gradle-plugins` 目录下创建`xxx.properties` 文件，这个 `xxx` 就是以后项目`build.gradle` 文件中需要`apply` 的插件名称。

```properties
implementation-class=com.tzx.ams.plugin.AmsPlugin
```

`com.tzx.ams.plugin.AmsPlugin` 就是插件对应的类。

![properties](./properties.png)

### 生成插件

我们在执行 `uploadArchives` 的任务的时候就在我们对于的仓库生成了我们需要的插件。

![](./repo.png)

### 在项目中引入插件

> 项目的根 `build.gradle`配置

```groovy
buildscript {
    repositories {
        maven {//添加repo本地仓库
            url uri("repo")
        }
        google()
        jcenter()
    }
   
}
```

> 项目的 `build.gradle` 配置

```groovy
apply plugin: 'com.android.application'
apply plugin: 'amsplugin'
/***部分代码省略***/
```

## plugin自定义配置

> 配置类

```java
public class AmsConfig {
    //日志开关
    public boolean isDebug;
    //class包含str则不处理
    public String[] filterContainsClassStr;
    //class以str开头则不处理
    public String[] filterstartsWithClassStr;
    //拦截在这个文件中声明的class
    public String filterClassFile;
    public List<String> filterClassNameList;
    //需要进行注入的method
    public String amsMethodFile;
    //需要进行注入的method对应的tag
    public String amsMethodTag;
    public List<Pair<String, String>> amsMethodFileList;
}
```

> 创建`AmsConfig` 的类对象

```java
public class AmsPlugin implements Plugin<Project> {
    @Override
    public void apply(@NonNull Project project) {
        AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);
        assert appExtension != null;
        //注册优先于task任务的添加
        project.getExtensions().create("AmsConfig", AmsConfig.class);
        appExtension.registerTransform(new AmsTransform(project));
    }
}
```

> `build.gradle` 文件中定义配置（如果gradle中没有定义，那么会反射构造一个对象）

```groovy
apply plugin: 'com.android.application'
apply plugin: 'amsplugin'
AmsConfig{
    isDebug = true
    filterContainsClassStr = ["R.class", ".R\$"]
    filterstartsWithClassStr = ["android"]
    filterClassFile = "amsfilterclass.text"//build.gradle相同目录级别的文件名
    amsMethodFile = "amsmethods.text"//build.gradle相同目录级别的文件名
    amsMethodTag = "TEST"
}
```

> 获取`gradle` 中定义的配置

```java
AmsConfig amsConfig = (AmsConfig) this.project.getExtensions().getByName(AmsConfig.class.getSimpleName());
//配置文件的获取
String projectDri = this.project.getProjectDir().getAbsolutePath()
String fileName = projectDri + File.separatorChar + amsConfig.filterClassFile;
```

这个 `amsConfig` 在使用的时候不用判空，如果调用`project.getExtensions().create` 添加了那么就会反射构造出这个对象。如果`gradle` 文件中定义了，那么这个对象就会被赋与相应的属性值。

如果在获取的时候，没有被`create`那么就会跑出一下异常：

```log
* Where:
Build file '/Users/tanzx/AndroidStudioWorkSpace/GitHub/AmsDemo/app/build.gradle' line: 3
* What went wrong:
A problem occurred evaluating project ':app'.
> Could not find method AmsConfig() for arguments [build_5n6idkxwtmzfflm5k30ynjblo$_run_closure1@2cf1f355] on project ':app' of type org.gradle.api.Project
```

## 手码代码的思路梳理

#### 自定义注解
（1）定义注解类：通过@interface定义注解类
（2）定义注解类型：通过@Target(ElementType.)定义注解类型，常用可选择的类型为type，method，field
（3）定义注解运行范围：通过@Retention(RetentionPolicy.) 定义

#### 自定义注解处理器


