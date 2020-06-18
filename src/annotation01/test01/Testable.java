package annotation01.test01;

import org.junit.Test;

/**
 * Junit注解，简单实用
 */
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
