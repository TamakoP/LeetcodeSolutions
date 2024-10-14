package binarySearch;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/10/14 下午5:02
 * @Version: 1.0
 */
public class bsSolutions {

    //由于数组里只有一个元素是只出现一次的，所以数组长度一定是奇数
    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            //将区间一分为2 时，一定一边是偶，一边是奇
            if (m % 2 == 1) {
                m--;   // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }
}
