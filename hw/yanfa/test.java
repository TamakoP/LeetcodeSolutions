package yanfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/3/21 下午2:35
 * @Version: 1.0
 */
public class test {
    public static void main(String[] args){
        solution solution=new solution();
        Scanner in =new Scanner(System.in);
        String origin=in.nextLine();
        System.out.println(solution.hexToDec(origin));

    }
}
