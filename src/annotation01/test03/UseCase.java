package annotation01.test03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * what why how?
 * （1）【非标记注解】是什么？
 * 阶梯：该UseCase类即为标记注解。特征是有元素，如id和description
 * 用来：用来跟踪一个项目中的用例。
 * （2）id和description元素的使用。
 * 阶梯：id和description非方法，而是key-value形式的键值对中的key。
 * 用来：用来标记【目标方法】时提供【处理时提供的统一参数】，具体使用参考PasswordUtil
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {

    public int id();

    public String description() default "no description";

}


