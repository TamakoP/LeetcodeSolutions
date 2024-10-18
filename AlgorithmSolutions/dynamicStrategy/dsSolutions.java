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
}
