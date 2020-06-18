package controller;

public class MoneyController {

    public static final int OUTPUT_THRESHOLD = 20;

    public static final int MISS_VALUE = 20;

    public static final int[] OUTPUT_STRATEGY_A = {10, 30, 50, 100};
    public static final int[] OUTPUT_STRATEGY_B = {20, 40, 60, 100};

//    （1）提现一次。提现两次
//    （2）提现大于多少。

    public static void calculateStrategy(int money) {

//        小于15元，20元

        System.out.println("----------- 当前金额为：" + money + " ---------");
        if (money < OUTPUT_THRESHOLD) {
            // 走策略A。金钱比例小于20直接设置不可提现。 【20】 【50】【100】
            System.out.println("最低提现金额为20，不支持提现");
            for (int index = 0;index<4;index++){
                System.out.print(OUTPUT_STRATEGY_B[index]+"  ");
            }
        } else {
            int target = (money - MISS_VALUE) / 10;
            System.out.println(target * 10);
            if (target == 0 || target % 2 == 1) {
                for (int index = 0;index<4;index++) {
                    System.out.print(OUTPUT_STRATEGY_A[index] + "  ");
                }
            } else {
                for (int index = 0;index<4;index++){
                    System.out.print(OUTPUT_STRATEGY_B[index]+"  ");
                }
            }

        }
    }

    // 他们把缺值直接提到了75，那么超过多少的部分，根本提不出来。

}
