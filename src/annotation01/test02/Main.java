package annotation01.test02;

/**
 * 自己定义一个注解，实际上注解与接口的定义方式类似，只不过多了一个【@】符号
 * （1）查看注解定义类Test的定义方式
 */
public class Main{

    public static void main(String[] args){
        hah();
    }

    @Test
    public static void hah(){
        System.out.println("hahah");
    }

}