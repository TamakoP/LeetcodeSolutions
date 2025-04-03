package spring2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/3/14 上午10:13
 * @Version: 1.0
 */
public class test {


    public static void main(String[] args) {
        solution solution = new solution();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int people = in.nextInt();
            int relation = in.nextInt();
            int quary = in.nextInt();
            String huanhangfu=in.nextLine();
            solution.friends2(people,relation,quary,in);

        }
    }
}
        /*
        for (int i = 0; i < n; i++) {
            String row = in.nextLine();  // 使用 nextLine 读取整行，若有空格replace掉

            for (int j = 0; j < n; j++) {
                matrix[i][j] = row.charAt(j) - '0';  // 将字符转换为整数
            }
        }*/







//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int n = in.nextInt();
//            int q = in.nextInt();
//            int[] nums = new int[n];
//            long knownSum = 0;
//            int zeroCount = 0;
//
//            for (int i = 0; i < n; i++) {
//                nums[i] = in.nextInt();
//                if (nums[i] == 0) {
//                    zeroCount++;
//                } else {
//                    knownSum += nums[i];
//                }
//            }
//
//            StringBuilder result = new StringBuilder();
//            for (int queryNums = 0; queryNums < q; queryNums++) {
//                long left = in.nextLong();
//                long right = in.nextLong();
//
//                long minSum = knownSum + zeroCount * left;
//                long maxSum = knownSum + zeroCount * right;
//
//                result.append(minSum).append(" ").append(maxSum).append("\n");
//            }
//
//            System.out.print(result);
//        }
//        in.close();
//    }
//}
