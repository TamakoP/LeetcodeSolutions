package spring;

import java.util.*;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/2/27 下午3:42
 * @Version: 1.0
 */
public class test {
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int buildNum=in.nextInt();
        int[] power=new int[buildNum];
        for(int i=0;i<buildNum;i++){
            power[i]=in.nextInt();
        }
        System.out.println(solution.findInitial(power,buildNum));
        // 注意 hasNext 和 hasNextLine 的区别
        //检查输入流中是否有一个完整的整数。如果输入流中有一个完整的整数，该方法返回 true；否则返回 false。这个方法通常用于循环中，直到用户输入一个整数为止

            // 读取两个整数并赋值给变量 nextInt自动下一个整数，但不会消费输入缓冲区中的换行符,连续调用没问题，但若换类型，则换行符无法跳过
            //nextLine()自动跳过tab 空格 换行
            //next()方法读取到空白符就结束
            //nextLine()读取到回车结束也就是“\r”；




    }
}
