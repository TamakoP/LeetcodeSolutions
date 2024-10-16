package dynamicStrategy;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/10/15 下午10:11
 * @Version: 1.0
 */
public class dsSolutions {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        // pre2代表
        int pre2 = 1, pre1 = 2;
        for (int i = 2; i < n; i++) {
            int cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    public int rob(int[] nums) {
        //pre1存储i-1的金钱值，pre2存储i-2的金钱值
        int pre1=0,pre2=0;
        for(int i=0;i<nums.length;i++){
            //比较截止当前i金钱值和i-1的金钱值
            int cur=Math.max(pre2+nums[i],pre1);
            //对于下一个屋舍：更新其i-2为当前pre1
            pre2=pre1;
            pre1=cur;
        }
        return pre1;
    }

    public int rob2(int[] nums) {
        //pre1存储i-1的金钱值，pre2存储i-2的金钱值
        int pre1=0,pre2=0;
        for(int i=0;i<nums.length;i++){
            int cur=pre2+nums[i];
            pre2=pre1;
            pre1=cur;
        }
        return pre1>pre2?pre1:pre2;
    }

    public int robRound(int[] nums) {
        //pre1存储i-1的金钱值，pre2存储i-2的金钱值
        int pre1=0,pre2=0;
        for(int i=0;i<nums.length;i++){
            if(i==nums.length-1){
                return pre1>nums[i]?pre1:nums[i];
            }
            //比较截止当前i金钱值和i-1的金钱值
            int cur=Math.max(pre2+nums[i],pre1);
            //对于下一个屋舍：更新其i-2为当前pre1
            pre2=pre1;
            pre1=cur;
        }
        return pre1;
    }
}
