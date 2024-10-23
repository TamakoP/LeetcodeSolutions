package dynamicStrategy;

import java.util.*;

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
    //母牛生产
    public int labour(int[] nums,int n){
        nums[0]=0;
        nums[1]=1;
        nums[2]=2;
        nums[3]=3;
        for(int i=4;i<=n;i++){
            nums[i]=nums[i-1]+nums[i-3];
        }
        return nums[n];
    }
    //动态规划：存储当前对应的前一个状态值
    //使用数组存储状态值
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j==0){
                    if(i==0)
                        dp[i][j]=grid[i][j];
                    else
                        dp[i][j]=dp[i-1][j]+grid[i][j];
                }else if(i==0){
                    dp[i][j]=dp[i][j-1]+grid[i][j];
                }else
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];

            }
        }
        return dp[m - 1][n-1];
    }
    public int uniquePaths(int m, int n) {
        if(m==1&&n==1) return 1;
        if(m==0&&n==0) return 0;
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0)
                    //只有一条路径能通过该点
                    dp[i][j]=1;
                else
                    //由于移动只能通过向右或者向下，所以对于某一点来说，通过该点必然通过其上或者其左边的点，这样代表两条不一样的路径
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public int numberOfArithmeticSlices(int[] nums) {
        if(nums.length<3) return 0;
        //dp记录以nums[i]结尾的等差数列的个数
        int[] dp=new int[nums.length];
        for(int i=2;i<=nums.length;i++){
            if (nums[i-1]-nums[i-2]==nums[i]-nums[i-1])
                // 若nums[i-1]-nums[i-2]==nums[i]-nums[i-1]成立，且nums[i+1]-nums[i]==nums[i]-nums[i-1]也成立
                // nums[i-2] nums[i-1] nums[i] nums[i+1]也为等差数列
                dp[i]=dp[i-1]+1;
        }
        int sum=0;
        for(int cnt:dp)
            sum+=cnt;
        return sum;


    }

    public int integerBreak(int n) {
        int[] dp=new int[n+1];
        dp[2]=1;
        for(int i=3;i<n+1;i++){
            for(int j=1;j<i/2;j++){
                dp[i]=Math.max(dp[i],Math.max((i-j)*j,dp[i-j]*j));
            }

        }
        return dp[n];
    }

    public int numSquares(int n) {
        int[] dp=new int[n+1];
        dp[1]=1;
        List<Integer> squareList=new ArrayList<>();
        for(int i=1;i<n;i++){
            squareList.add((int) Math.pow(i,2));
        }
        for(int i=2;i<=n;i++){
            int min=Integer.MAX_VALUE;
            for(int square:squareList){
                if(square>i)
                    break;
                min=Math.min(dp[i-square]+1,min);
            }
            dp[i]=min;
        }
        return dp[n];

    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 2) == '0') {
                continue;
            }
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }


    public int lengthOfLIS(int[] nums) {
        if(nums.length==0) return 0;
        //dp存储以nums[i]结束的最长递增子序列的长度
        int[] dp=new int[nums.length+1];

        for(int i=0;i<nums.length;i++){
            int max=1;
             for(int j=0;j<i;j++){
                 if(nums[j]<nums[i])
                     max=Math.max(max,dp[j]+1);

             }
             dp[i]=max;
        }
        return  Arrays.stream(dp).max().getAsInt();

    }

}
