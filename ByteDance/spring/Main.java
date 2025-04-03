package spring;
import java.util.*;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/2/18 上午9:48
 * @Version: 1.0
 */
public class Main {
    public String verify(String s){
        //退出条件

        StringBuilder sb=new StringBuilder(s.length());
        for(int i =0;i<s.length();i++){
            if(sb.length()<2){
                sb.append(s.charAt(i));
                continue;
            }
            if (sb.length()>=2){
                if(s.charAt(i)==sb.charAt(sb.length()-1))
                    continue;
            }
            // sb=CAA
            if (sb.length()>=3){

                int len=sb.length();
                if (s.charAt(i)==sb.charAt(len-1)&&s.charAt(len-2)==s.charAt(i)){
                    continue;

                }
            }
            sb.append(s.charAt(i));

        }
        return sb.toString();
    }
    int com(int n){
        if(n==2)
            return 1;
        int result=n;
        while(n!=2){
            if(n-1==2)
                return n;
            result*=--n;
        }
        return result;
    }
    //移动窗口：本题目是排列组合即选择合适的数值组合，但由于数组是从小到大排列的，distance用于控制组间距离，若组间max-min<=distance;
    //那么固定min之后，其余两个处于（min,max]间的数的组合必定满足distance:注意窗口会移动是因为min的取值决定了max的取值，这是双头移动
    //从min出发，找最远能到达的下标max，max的标准是第一个符合build[max]-build[min]<=distance的max（因为max是从最大的值开始内缩

    public int combination(int buildNum, int distance,int[] build) {
        int min,max=buildNum-1;
        int result=0;
        for(int i=0;i<buildNum;i++){
            min=i;//min是当前窗口的起点
            // max>=min+2保证区间起点和终点至少含3个数值
            while (max>=min+2){
                if (build[max]-build[min]<=distance){
                    result+=com(max-min);
                    break;
                }
                max--;
            }
            max=buildNum-1;
        }
        return result;
    }

    public List<Integer> hupai(int[] poker){
        List<Integer> result=new ArrayList<>();
        List<Integer> pai=new ArrayList<>();
        int pokerHead=0; //shunzi = 0;
        int left=13;
        for(int i=1;i<=9;i++){
            if(poker[i]==4)
                continue;
            poker[i]++;
            left++;
            if(isWin(poker,pai,left,pokerHead))
                result.add(i);
            poker[i]--;
            left--;
            pokerHead=0;
//            shunzi=0;
            pai=new ArrayList<>();
        }
        return result;
    }

    public boolean isWin(int[] poker,List<Integer> pai,int left,int pokerHead){
//        if (pokerHead!=0&&shunzi==4)
//            return true;
        //剩牌为0
        if(left==0)
            return true;

        for(int i=1;i<=9;i++){
            //isWin-for的时候应该从第一个不为0的元素开始
            if(poker[i]==0)
                continue;
            //最外层if：判断雀头的有无，无则先找雀头
            if(pokerHead!=0){
                //尝试找到三个相同的或者三个连续的
                // how判断shunzi不会重复？？？？
                if(poker[i]>=3){
                    //shunzi++;
                    poker[i]-=3;
                    left-=3;
                    pai.addAll(Arrays.asList(i,i,i));
                    if(isWin(poker, pai, left,pokerHead))
                        return true;
                    poker[i]+=3;
                    pai.removeAll(Arrays.asList(i,i+1,i+2));
                    left+=3;
                    //shunzi--;
                }else{
                    //越界判断
                    if(i>7)
                        continue;
                    if(poker[i]>=1&&poker[i+1]>=1&&poker[i+2]>=1){
                        left-=3;
                        poker[i]--;
                        poker[i+1]--;
                        poker[i+2]--;
                        pai.addAll(Arrays.asList(i,i+1,i+2));
                        if(isWin(poker, pai, left,pokerHead))
                            return true;
                        pai.removeAll(Arrays.asList(i,i+1,i+2));
                        left+=3;
                        poker[i]++;
                        poker[i+1]++;
                        poker[i+2]++;
                    }
                }



            }else{
                //固定雀头，再去匹配顺子
                for(int j=1;j<=9;j++){
                    if (poker[j]>=2){
                        pokerHead=j;
                        pai.addAll(Arrays.asList(j,j));
                        poker[j]-=2;
                        left-=2;
                    }
                    if(isWin(poker, pai, left,pokerHead))
                        return true;
                    pai.removeAll(Arrays.asList(j,j));
                    poker[j]+=2;
                    left+=2;
                }

            }

        }
        return false;

    }
    /*
    输入例子：
1
8
2 1 1 2 2
2 1 1 1 4
2 1 1 2 2
2 2 2 1 4
0
0
1 1 1
1 1 1
输出例子：
3
     */

    public int  featureExtract(List<String> frameList,int frameNum){
        HashMap<String,List<Integer>> memo=new HashMap<>();

        for(int i=0;i<frameNum;i++){
            //当前帧
            String frame = frameList.get(i);
            //特征数
            int featureNum=Integer.parseInt(frame.substring(0,1));
            int j=1;
            while(j<frame.length()-1){
                String feature=frame.substring(j,j+2);
                List<Integer> index=memo.getOrDefault(feature,new ArrayList<>());
                index.add(i);
                memo.put(feature,index);
                j+=2;
            }

        }
        //检查连续性
        int max=0;
        int continueNum=0;
        for (Map.Entry<String, List<Integer>> entry : memo.entrySet()){
            List<Integer> list=entry.getValue();
            for(int i=1;i<list.size();i++){
                if(list.get(i)-1== list.get(i-1)){
                    continueNum++;
                    continue;
                }
                max=Math.max(max,continueNum);
                continueNum=0;
            }
            max=Math.max(max,continueNum);
            continueNum=0;

        }
        return max+1;
    }

    /*
    ⽤「位图」的技巧，⽤⼀个 int 类型的 used 变量来替代 used 数组
    ⽤整数 used 的第 i 位（(used >> i) & 1）的 1/0 来表示 used[i] 的 true/false
     */

    public int cheapestFee(int[][] fees,int cityNum){
        int cheapest=0;
        //V=16(int)10000(binary)代替used数组：1表示已访问，0表示未访问，16是n个城市的所有集合数目，表示所有可能的城市访问状态
        //V=8,1000:忽略城市起点0，for循环遍历[001,111]
        int V=1<<(cityNum-1);
        //dp[i][j]表示:从城市 i 出发，已经访问了 j 集合的城市，所得到的最小费用
        int[][] dp=new int[cityNum][V];
        //初始化城市i回到起点的花销
        for(int i=0;i<cityNum;i++){
            dp[i][0]=fees[i][0];
        }
        //j从1=0001开始表示至少从访问一个城市开始，"0001"的1表示城市3被访问，2=0010表示只有城市2被访问
        for(int j=1;j<V;j++){
            for(int i=0;i<cityNum;i++){
                //j是一个城市集合，从城市i出发，经过集合中的每一个城市：本题目求最小值，所以初始花销设置为一个很大的值
                //题目城市最多20个，车费最大1000，花销最大值为20000；或者无穷0x7ffff
                dp[i][j]=20001;
                //若城市i在集合j中的值为1，则跳过，自己不访问自己
                //i-1是因为i从0开始，i-1=-1（1000）
                if(((j>>(i-1))&1)==1)
                    continue;
                //找到一个不包含自身的集合后，寻找集合j中第一个为1的城市（1表示本次要访问的城市
                for(int k=1;k<cityNum;k++){
                    //判断城市k是否在集合j中为1
                    //k-1是因为k从1开始
                    if(((j>>(k-1))&1)==1){
                        //异或运算将集合j中城市k的1变为0
                        dp[i][j]=Math.min(dp[i][j],fees[i][k]+dp[k][j ^ (1 << (k-1))]);
                    }
                }

            }

        }
        return dp[0][V-1];
    }

    public int minCoins(int fee){
        if(fee==0)
            return 0;

        int[] dp=new int[fee+1];
        //初始化
        dp[1]=1;
        int[] coins={1,4,16,64};
        for(int i=2;i<fee+1;i++){
            //求最小值，应该初始化一个很大的值
            dp[i]=0x7ffff;
            for(int coin:coins){
                if(i-coin<0)
                    continue;
                dp[i]=Math.min(dp[i],dp[i-coin]+1);
            }
        }
        return dp[fee];
    }
    //dp[i] = min(dp[i-1] + H(i), dp[i-2] - H(i-1) + H(i))
    public int findInitial(int[] power,int buildNum){
        int maxPower=Arrays.stream(power).max().getAsInt();
        for(int i=0;i<=maxPower;i++){
            if(backtrack(i,power))
                return i;
        }
        return maxPower;

    }

    public boolean backtrack(int maxPower,int[] power){
        for (int j : power) {
            if (maxPower < j) {
                maxPower -= j - maxPower;
            } else {
                maxPower += maxPower - j;
            }
            if (maxPower < 0)
                return false;
        }
        return true;

    }


}