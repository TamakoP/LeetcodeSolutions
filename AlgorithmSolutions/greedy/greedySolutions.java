package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/9/28 下午12:54
 * @Version: 1.0
 */
public class greedySolutions {
    /**
     * @Description 分配饼干 ：用最小size的饼干满足尽可能多的孩子
     * @Param [grid, size]
     * @return int
     */
    public int findContentChildren(int[] grid, int[] size) {
        if(grid==null||size==null) return 0;
        //保证grid和size都是按照从小到大排序,这样可以保证用最小的饼干满足贪心度最小的孩子
        Arrays.sort(grid);
        Arrays.sort(size);
        //优化一下num，可以直接返回i的值  num和i是同步变化的
        int num=0;
        int i=0,j=0;
        while (i<grid.length&&j<grid.length){
            if(grid[i]<=size[j]){
                num++;
                i++;
            }
            j++;
        }
        return num;

    }
    /**
     * @Description 计算让一组区间不重叠所需要移除的区间个数
     * @Param [intervals]
     * @return int
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals ==null) return 0;
        //这里的排序方法只针对前两个元素进行比较。对于类似[1 2 3]和[1 2 4]的情况不变动顺序
        Arrays.sort(intervals, new Comparator<int[]>(){
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if(o1[0]==o2[0]) return o1[1]-o2[1];
                        return o1[0]-o2[0];
                    }}
        );
        int cnt = 1;
        //使用排序后第一个数组的第二个元素end作为比较依据，贪心的做法是确保这个end足够小，也就是起步越小，后面能涵盖的才越多
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //end是代表小的基准，若数组中最小的元素都比基准小，那么这个数组必定与前面的区间有重叠，违反“贪心”原理
            if (intervals[i][0] < end) {
                continue;
            }
            //更新基准：数组首元素是逐渐增大的
            end = intervals[i][1];
            cnt++;
        }
        return intervals.length - cnt;
        
    }
    //与上面剔除重叠区间思想一致
    public int findMinArrowShots(int[][] points) {
        if(points==null) return 0;
        //排序的意义：寻找重叠
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });
        //end是每一支箭射出的位置，必须取区间端的值
        int end=points[0][1];
        int arrows=1;
        for(int i=1;i<points.length;i++){
            if(end>=points[i][0])
                continue;
            end=points[i][1];
            arrows++;
        }
        return arrows;
    }
    /**
     * @Description 重新构造并返回输入people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）
     * @Param [people]
     * @return int[][]
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return o1[1]-o2[1];
                //首元素升序
                return o2[0]-o1[0];
            }
        });
        List<int []> queue=new ArrayList<>();
        for(int[] p:people){
            //指定元素插入的位置，将其后面元素的索引统一+1
            queue.add(p[1],p);
        }
        //toArray()方法导出的是Object类型数组，而toArray(T[] a)方法导出的是指定类型的数组，入参是一个new过的实例
        return  queue.toArray(new int[queue.size()][]);


    }
    /**
     * @Description 选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润
     * @Param [prices]
     * @return int
     */
    public int maxProfit(int[] prices) {
        int max=0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                max=max>prices[j]-prices[i]?max:prices[j]-prices[i];
            }
        }
        return max;
    }

    public int maxProfitMulti(int[] prices) {

            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += (prices[i] - prices[i - 1]);
                }
            }
            return profit;
    }
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        for (int i = 0; i < len && cnt < n; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            //当前位置为空，考虑前一个位置是否为1
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                cnt++;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    public boolean isSubsequence(String s, String t) {
        int index=-1;
        for(char c:s.toCharArray()){
            //indexOf成功则返回下标，不成功返回-1;fromIndex若为负数等同于0
            //这里形参fromIndex=index+1不能为index，因为indexOf默认从fromIndex开始寻找，若遇到s="abbc"的情况，第二个b在寻找时对应的是第一个b的下标
            index=t.indexOf(c,index+1);
            System.out.println(index);
            if(index==-1)
                return false;
        }
        return true;
    }
    //修改一个数成为非递减数组
    public boolean checkPossibility(int[] nums) {
        int modify=0;
        for(int i=0;i<nums.length;i++){
            //end加入if判断防止数组溢出或者令下标从1开始
            /*for (int i = 1; i < nums.length && cnt < 2; i++) {
                   if (nums[i] >= nums[i - 1])、
             */
            boolean end=i==nums.length-1?true:false;
            if(!end&&nums[i]>nums[i+1])
                modify++;
        }
        return modify>1?false:true;
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preSum = nums[0];
        int maxSum = preSum;
        for (int i = 1; i < nums.length; i++) {
            //决定是否把当前值加上：若preSum<0，则完全抛弃
            preSum = preSum > 0 ? preSum + nums[i] : nums[i];
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }



    }
