package spring2024;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/3/14 上午9:50
 * @Version: 1.0
 */
public class solution {
    /*
    1.小美的平衡矩阵
     小美拿到了一n∗n的矩阵，其中每个元素是 0 或者 1。小美认为一个矩形区域是完美的，当且仅当该区域内 0 的数量恰好等于 1 的数量。现在，小美希望你回答有多少个i∗i的完美矩形区域。你需要回答
    */
    public void balanceTangle(int[][] matrix,int n){
        //results[奇数]=0

        int[][] preSum = new int[n][n];
        preSum[0][0] = matrix[0][0];

        //计算第一行
        for (int j = 1; j < n; j++) {
            preSum[0][j] = preSum[0][j - 1] + matrix[0][j];
        }
        //计算第一列
        for (int i = 1; i < n; i++) {
            preSum[i][0] = preSum[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                preSum[i][j] = matrix[i][j] + preSum[i-1][j]+preSum[i][j-1] - preSum[i - 1][j - 1];
            }
        }
        //找结果为square的
        for(int i=1;i<=n;i++){
            if(i%2!=0){
                System.out.println(0);
                continue;}
            System.out.println(countSquare(preSum,i,n));
        }

        }
        public int countSquare(int[][] preSum,int square,int n){
             int square_2=square*square;
             int count=0;
             if(square==n){
                 if(preSum[n-1][n-1]*2==square_2)
                     return 1;
                 else
                     return 0;
             }
            for (int i = 0; i < n; i++) {
                for(int j=0;j<n;j++){
                    int i_end=i+square-1;
                    int j_end=j+square-1;
                    int area=0;
                    if(i_end<n&&j_end<n){
                        if(i==0){
                            if(j==0)
                                area=preSum[i_end][j_end];
                            else
                                area=preSum[i_end][j_end]-preSum[i_end][j_end-square];
                        }else if(j==0){
                            area=preSum[i_end][j_end]-preSum[i_end-square][j_end];
                        }else {
                            area=preSum[i_end][j_end]-preSum[i_end-square][j_end]-preSum[i_end][j_end-square]+preSum[i-1][j-1];
                        }
                    } else{
                        continue;
                    }
                    if(area*2==square_2)
                        count++;
                }
            }
             return count;

        }
    /*
小美的查询数组：（1）注意题目中的范围，在计算乘法加法的时候要转换为long；（2）适当选择哪些变量用long哪些只需要使用int，因为long的读取比int耗时，避免超时
 */
    public void minAndmax(String[] args) {
        solution solution = new solution();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int arrayNums = in.nextInt();
            int quary = in.nextInt();
            in.nextLine();  // 读取换行符
            int count = 0;
            long sum = 0;  // 将 sum 的类型改为 long
            for (int i = 0; i < arrayNums; i++) {
                int temp=in.nextInt();  // 修改为 long
                sum+=temp;
                if (temp == 0) {
                    count++;
                }
            }
            in.nextLine();  // 读取换行符
            for (int i = 0; i < quary; i++) {
                System.out.println((sum + count * in.nextLong())+" "+(sum + count * in.nextLong()));
            }
        }
    }
    public void alterChar(){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int arrayNums = in.nextInt();
            int quary = in.nextInt();
            //in.nextLine();  // 读取换行符
            int count = 0;
            int alter=0;
            if(quary>arrayNums)
                quary=arrayNums;
            String huanhangfu=in.nextLine();
            String input=in.nextLine();
            for(int i=0;i<arrayNums;i++){
                if(input.charAt(i)=='M'||input.charAt(i)=='T'){
                    count++;
                }else{
                    alter++;
                }
            }
            if(alter>quary)
                alter=quary;
            System.out.println(count+alter);}

    }
    /*
    小美的朋友关系：数组不行，10的9次方太大，数组装不下，堆溢出
     */
    public  boolean makeFriends(int[][] relationship,int people,int media,int target,boolean[] used){
        if(relationship[media][target]==1)
            return true;
        for(int i=1;i<=people;i++){

            if(relationship[media][i]==1&&!used[i]){
                used[i]=true;
                if(makeFriends(relationship,people,i,target,used))
                    return true;

            }
        }
        return false;

    }
    public  void friends1() {
        solution solution = new solution();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int people = in.nextInt();
            int relation = in.nextInt();
            int quary = in.nextInt();
            String huanhangfu=in.nextLine();
            int[][] relationship=new int[6][6];
            for(int i=1;i<=relation;i++){
                int first=in.nextInt();
                int second=in.nextInt();
                relationship[first][second]=1;
                relationship[second][first]=1;
                String huanahang=in.nextLine();
            }
            for(int i=0;i<quary;i++){
                int incidentType=in.nextInt();
                int friendA=in.nextInt();
                int friendB=in.nextInt();
                if(incidentType==1){
                    relationship[friendA][friendB]=0;
                    relationship[friendB][friendA]=0;
                }else{
                    boolean[] used=new boolean[people+1];
                    used[friendA]=true;
                    used[friendB]=true;
                    if(makeFriends(relationship,people,friendA,friendB,used)){
                        System.out.println("Yes");

                    }else{
                        System.out.println("No");
                    }

                }
            }
        }
    }
    class UF{
        private int count;
        private int[] parent;
        private int[] size;
        public UF(int n){
            parent = new int[n];
            size = new int[n];
            for(int i=1;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }
        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootQ==rootP)
                return;
            if(size[rootP]>size[rootQ]){
                parent[rootQ]=rootP;
                size[rootP]+=size[rootQ];
            }else{
                parent[rootP]=rootQ;
                size[rootQ]+=size[rootP];
            }
            //parent[rootQ] = rootP;
            count--;
        }

        public void disunion(int p,int q){
            int rootP=find(p);
            int rootQ=find(q);
            //root判断连通性
            if(rootQ!=rootP)
                return;
            //能断开联通只能是一个节点是另一个节点的parent
            parent[q]=q;

//            if(parent[p]==q){
//                parent[p]=p;
//                size[rootQ]-=size[p];
//                return;
//            }
//            if(p==parent[q]){
//                parent[q]=q;
//                size[rootP]-=size[q];
//                return;
//            }
//            while(parent[p]!=rootP){
//                if(q==parent[p]){
//                    parent[p]=p;
//                    size[rootQ]-=size[p];
//                    return;
//                }
//            }
//            while(parent[q]!=rootQ){
//                if(p==parent[q]){
//                    parent[q]=q;
//                    size[rootP]-=size[q];
//                    return;
//                }
//            }
//
        }
        public boolean connected(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            return rootQ==rootP;
        }

        //寻找根节点
        private int find(int x){
            while(parent[x]!=x){
                parent[x]=parent[parent[x]];
                x=parent[x];
            }
            return x;
        }
        public int count(){
            return count;
        }

    }
    public void friends2(int people,int relation,int quary,Scanner in){
        //Scanner in=new Scanner(System.in);
        UF uf=new UF(people+1);
        for(int i=0;i<relation;i++){
            uf.union(in.nextInt(),in.nextInt());
        }
        for(int i=0;i<quary;i++){
            int incidentType=in.nextInt();
            int friendA=in.nextInt();
            int friendB=in.nextInt();
            if(incidentType==1){
                //disconnect
                uf.disunion(friendA,friendB);
            }else{
                if(uf.connected(friendA,friendB)){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }

        }
    }
    /*
    小美的区间删除：末尾零的个数由所有乘数中的2因子和5因子决定
     */
}

        /*  三层for超时
        public static void balanceTangle(int[][] matrix) {
        int[] results = new int[matrix.length];
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    results[i - 1] += findTangle(j, k, i, matrix);
…        }
        if (c_1 == c_0)
            return 1;
        return 0;

    }
    public static int findTangle(int start, int k, int square, int[][] matrix) {
        if (square == 1 || start + square > matrix.length || k + square > matrix.length)
            return 0;
        int c_0 = 0, c_1 = 0;
        for (int i = start; i < start + square; i++) {
            for (int j = k; j < k + square; j++) {
                if (matrix[i][j] == 1)
                    c_1++;
                else
                    c_0++;
            }
        }
        if (c_1 == c_0)
            return 1;
        return 0;

    }

         */




