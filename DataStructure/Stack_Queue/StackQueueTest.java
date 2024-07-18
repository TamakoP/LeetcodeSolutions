package Stack_Queue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/7/18 下午8:00
 * @Version: 1.0
 */
public class StackQueueTest {
    public static void main(String[] args) {
        StackQueueSolutions solutions = new StackQueueSolutions();
        int[] nums = {1, 7, 3, 4, 9, 8};
        nums=solutions.nextGreaterElements(nums);
        System.out.println(Arrays.toString(nums));
    }
}
