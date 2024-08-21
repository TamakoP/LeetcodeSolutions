package Array_Matrix;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/15 下午5:11
 * @Version: 1.0
 */
public class test {
    public static void main(String[] args){
        /*int[][] nums={
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };*/
        int[] nums={1,2,2,3,1,4,2};

        Solutions ss=new Solutions();

        System.out.println(ss.findShortestSubArray(nums));
    }
}
