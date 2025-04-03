package backend;

import java.util.*;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/3/24 上午9:40
 * @Version: 1.0
 */
public class Main {
    /*
    用户喜好：数据量大的时候使用数组做查找耗时，应该（1）离散化->查找的范围应该落实到具体的区间 （2）查找使用二分
     */
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        while(in.hasNextInt()){
//            int num=in.nextInt();
//            Map<Integer, ArrayList<Integer>> map=new HashMap<>();
//            // list有序插入
//            for (int i=0;i<num;i++) {
//                int key=in.nextInt();
//                if(!map.containsKey(key)) {
//                    ArrayList<Integer> list = new ArrayList<Integer>();
//                    list.add(i+1);
//                    map.put(key,list);
//                }else{
//                    ArrayList<Integer> list = map.get(key);
//                    list.add(i+1);
//                    map.put(key,list);
//                }
//
//            }
//            int query = in.nextInt();
//            for (int i = 0; i < query; i++) {
//                int start=in.nextInt();
//                int end=in.nextInt();
//                int k=in.nextInt();
//                if (!map.containsKey(k)){
//                    System.out.println(0);
//                }else{
//                    ArrayList<Integer> list=map.get(k);
//                    int left=0;
//                    int right=list.size()-1;
//                    int l=0;
//                    int r= list.size()-1;
//                    // 找start,mid要贴近start
//                    while(left<=right){
//                        int mid=left+(right-left)/2;
//                        if(list.get(mid)<start)
//                            left=mid+1;
//                        if(list.get(mid)>end)
//                            right=mid-1;
//                        if(list.get(mid)<=end&&list.get(mid)>=start)
//                            right=mid-1;
//                    }
//                    l=left;
//                    //重置right，end>start，所以left不需要重置为0
//                    if(end<list.get(r)){
//                        right=list.size()-1;
//                        while(left<=right){
//                            int mid=left+(right-left)/2;
//                            if(list.get(mid)<start)
//                                left=mid+1;
//                            if(list.get(mid)>end)
//                                right=mid-1;
//                            if(list.get(mid)<=end&&list.get(mid)>=start)
//                                left=mid+1;
//
//                        }
//                        r=right;
//                    }
//                    System.out.println(r-l+1);
//                }
//            }
//        }
//    }
    /*
    手串：问的是有几种颜色出错，也就是命中一次即可退出循环
     */
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        int beads=in.nextInt();
//        int[] wrong=new int[beads+1];
//        int m=in.nextInt();
//        int colors=in.nextInt();
//        Map<Integer,ArrayList<Integer>> map=new HashMap<>();
//        for (int i = 1; i <= beads; i++) {
//            int colorNum=in.nextInt();
//            for (int j = 0; j <colorNum ; j++) {
//                int curColor=in.nextInt();
//                if(!map.containsKey(curColor)){
//                    ArrayList<Integer> list=new ArrayList<>();
//                    list.add(i);
//                    map.put(curColor,list);
//                }else{
//                    ArrayList<Integer> list=map.get(curColor);
//                    list.add(i);
//                }
//            }
//        }
//        int count = 0;
//        for(List<Integer> list : map.values()) {
//            if(list.size() > 1) {
//                boolean flag = false;
//                int size = list.size();
//                for(int i = 0;i < size - 1;i++) {
//                    if(list.get(i + 1) - list.get(i) < m) {
//                        count++;
//                        flag = true;
//                        break;
//                    }
//                }
//                if(!flag) {
//                    //环形判断
//                    if(list.get(0) + beads - list.get(size - 1) < m) {
//                        count++;
//                    }
//                }
//            }
//        }
//        System.out.println(count);
//
//    }
    /*
    字母交换解读：（1）只能交换相邻位置，交换次数等价于步数；（2）题目问题多少个连续位置上字母相同等价于连续长度的最大值lenMax∈[2,每个字符的出现次数]
    解题思路：（1）步数的计算=位置的相减；(2)lenMax的求解使用穷举+动态规划
     */
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        String[] input=in.nextLine().split(" ");
        String origin=input[0];
        int step=Integer.parseInt(input[1]);
        int res=1;
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(char c='a';c<='z';c++){
            List<Integer> list=new ArrayList<>();
            for (int i = 0; i < origin.length(); i++) {
                if(origin.charAt(i)==c){
                    list.add(i);
                }
            }
            //判断list的大小,size<2的不考虑连续
            if(list.size()<=1)
                continue;
            int ans=1;
            //初始化dp，存放移动的次数
            int size=list.size();
            //dp[i][j]对应在i位置的字符移动到j位置需要的交换次数，构造长度为len的连续字符串需要的移动次数->找最小的移动次数
            // 与下一次出现的位置即list.get(i+1)有关
            int[][] dp=new int[size][size];
            //遍历连续长度的区间
            for (int len = 2; len <= size; len++) {
                //遍历list->每个字符出现的位置：从i出发，构造长度len的连续字串；注意：这里的i指list的下标，构造len，那么最大的下标值为i+len-1（从前往后构造）
                //写公式的时候考虑大范围，不要思考len=2的情况->思考len=3,4,....
                //e.g.list=[0,3,5,9],len=4
                for (int i = 0; i+len-1 < size; i++) {
                    dp[i][i+len-1]=dp[i+1][i+len-2]+ list.get(i+len-1)-list.get(i)-len+1;
                    if (dp[i][i + len - 1] <= step) {
                        ans=len;
                    }
                }
            }
            res = Math.max(res, ans);
        }
        System.out.println(res);
    }
}
