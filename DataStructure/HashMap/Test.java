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
        int[] nums={1,4,2,2,59,2,3,5,60};
        int target=6;
        Solutions solutions=new Solutions();
        int longest=solutions.longestConsecutive(nums);
        System.out.println(longest);

    }
}
