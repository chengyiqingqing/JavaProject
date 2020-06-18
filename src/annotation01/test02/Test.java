package annotation01.test02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * what why how?
 * （0）定义注解Test，为什么要有@Target,@Retention是什么意思？
 *      阶梯：因为定义注解时，需要一些元注解。所以才有了@Target,@Retention
 *
 * （1）@Target是什么意思？
 *      阶梯：用来定义你的注解应用于什么地方。例如是一个方法ElementType.METHOD或一个域
 *
 * （2）@Retention是什么意思？
 *      阶梯：用来定义该注解在哪一个级别可用。可以在源代码中RetentionPolicy.SOURCE源代码，CLASS类文件，RUNTIME运行时
 *
 * （3）【标记注解】是什么？
 *      阶梯：该Test类即为标记注解。特征是没有元素
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {



}


