package HashMap;

import java.util.Arrays;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/7/19 下午2:05
 * @Version: 1.0
 */
public class Test {
    public static void main(String [] args){
        int[] nums={3,3};
        int target=6;
        Solutions solutions=new Solutions();
        int[] location=solutions.twoSum(nums,target);
        System.out.println(Arrays.toString(location));

    }
}
