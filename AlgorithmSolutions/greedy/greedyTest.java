package greedy;

import java.util.Arrays;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/10/9 下午4:22
 * @Version: 1.0
 */
public class greedyTest {
    public static void main(String[] args){
        //int[][] intervals={{7,0},{4,4},{7,1},{6,1},{5,0},{5,2}};
        int[] nums={4,2,3,1};
        String s = s = "eccbbbbdec";


        greedySolutions gs=new greedySolutions();
        System.out.println(gs.partitionLabels(s));
    }
}
