package annotation01.test03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * what why how?
 * （1）【非标记注解】是什么？
 * 阶梯：该UseCase类即为标记注解。特征是有元素
 * 用来：用来跟踪一个项目中的用例。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {

    public int id();

    public String description() default "no description";

}


