package binarySearch;

import java.util.Arrays;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/10/15 下午4:56
 * @Version: 1.0
 */
public class bsTest {
    public static void main(String[] args){
        int[] nums={5,7,7,8,8,10};
        bsSolutions bss=new bsSolutions();
        System.out.println(Arrays.toString(bss.searchRange(nums,6)));
    }

}
