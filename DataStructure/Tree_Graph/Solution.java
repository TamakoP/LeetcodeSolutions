package Tree_Graph;

import java.util.*;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/20 下午2:33
 * @Version: 1.0
 */

class Pair
{
    int first,second;
    // Return an immutable singleton map containing only the specified
    // key-value pair mapping
    public Pair(int first, int second) {
        this.first=first;
        this.second=second;
    }
    public int get(int index){
        if(index==0)
            return first;

        return second;
    }
}

public class Solution {
    public boolean isBipartite(int[][] graph){
        int nodeNum=graph.length;
        //指定长度默认初始化为0
        int[] colors=new int[nodeNum];
        int[] colored=new int[nodeNum];
        for(int i=0;i<nodeNum;i++){

            if(colored[i]!=-1){
                colors[i]=1;
                colored[i]=-1;
            }
            for(int j=0;j<graph[i].length;j++){
                int curColor=colors[i];
                int curNode=graph[i][j];
                if(colored[curNode]==-1){
                    if(colors[curNode]==colors[i]){
                        return false;
                    }
                    continue;
                }


                colors[curNode]=curColor+1;
                colored[curNode]=-1;

            }
        }
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graphic = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graphic[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graphic[pre[0]].add(pre[1]);
        }
        Stack<Integer> postOrder = new Stack<>();
        boolean[] globalMarked = new boolean[numCourses];
        boolean[] localMarked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(globalMarked, localMarked, graphic, i, postOrder)) {
                return new int[0];
            }
        }
        int[] orders = new int[numCourses];
        for (int i = numCourses - 1; i >= 0; i--) {
            orders[i] = postOrder.pop();
        }
        return orders;
    }

    private boolean hasCycle(boolean[] globalMarked, boolean[] localMarked, List<Integer>[] graphic,
                             int curNode, Stack<Integer> postOrder) {

        if (localMarked[curNode]) {
            return true;
        }
        if (globalMarked[curNode]) {
            return false;
        }
        globalMarked[curNode] = true;
        localMarked[curNode] = true;
        for (int nextNode : graphic[curNode]) {
            if (hasCycle(globalMarked, localMarked, graphic, nextNode, postOrder)) {
                return true;
            }
        }
        localMarked[curNode] = false;
        postOrder.push(curNode);
        return false;
    }

    private class UF{
        private int[] id;
        UF(int n){
            id=new int[n+1];
            for(int i=0;i<id.length;i++){
                //每个node自成一个集合，所以下标为自身
                id[i]=i;
            }

        }

        void union(int u,int v){
            int uId=find(u);
            int vId=find(v);
            if(uId==vId)
                return;
            for(int i=0;i<id.length;i++){
                if(id[i]==uId)
                    id[i]=vId;
            }
        }

        int find(int p){
            return id[p];
        }
        //若两个节点位于同一个集合里面，那么必可以连通
        boolean connect(int u,int v){
            return find(u)==find(v);
        }
    }
    /*
       BFS
     */

    /**
     * @Description 计算在网格中从原点到特定点的最短路径长度
     * @Param [grid]
     * @return int
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0||grid[0][0]==1) {
            return -1;
        }
        Queue<Pair> queue = new LinkedList<>();
        Set<Pair> visited=new HashSet<>();
        queue.offer(new Pair(0,0));
        visited.add(new Pair(0,0));
        //方向数组，方便访问相邻节点
        int[][] directions={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        int step=0;
        while (!queue.isEmpty()){
            int sz=queue.size();
            for (int i=0;i<sz;i++){
                Pair node= queue.poll();
                int row=node.get(0);
                int col=node.get(1);
                if(row== grid.length-1&&col==grid[0].length-1)
                    return step;
                for(int[] dire:directions){
                    //越界判断
                    if(row+dire[0]<0||row+dire[0]==grid.length||col+dire[1]<0||col+dire[1]==grid[0].length)
                        continue;
                    //可行性判断
                    if (grid[row+dire[0]][col+dire[1]]==1)
                        continue;
                    Pair adj=new Pair(row+dire[0],col+dire[1]);
                    if(!visited.contains(adj)){
                        queue.offer(adj);
                        visited.add(adj);
                    }
                }
            }
            step++;
        }
        return step;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        List<Integer>[] graph= buildGraph(wordList);
        Queue<Integer> q=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        q.offer(wordList.size()-1);
        visited.add(wordList.size()-1);
        int step=0;
        while(!q.isEmpty()){
            int sz=q.size();
            for(int i=0;i<sz;i++){
                int cur=q.poll();
                for (int adj:graph[cur]){
                    if(!visited.contains(adj)){
                        visited.add(adj);
                        q.offer(adj);
                    }
                }
            }
            step++;
        }
        return step;
    }

    private boolean isConnect(String s1, String s2) {
        int diffCnt = 0;
        for (int i = 0; i < s1.length() && diffCnt <= 1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }
    private List<Integer>[] buildGraph(List<String> wordLists){
        List<Integer>[] graph=new List[wordLists.size()];
        for(int i=0;i<wordLists.size();i++){
            graph[i]=new ArrayList<>();
            for(int j=0;j<wordLists.size();j++){
                if(isConnect(wordLists.get(i), wordLists.get(j)))
                    graph[i].add(j);
            }
        }
        return graph;

    }

    /*
       DFS
     */
    private int m,n;

    public int maxAreaOfIsland(int[][] grid) {
        m= grid.length;
        n=grid[0].length;
        int area=0;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==1)
                    area=Math.max(area,dfs(grid,i,j));

            }
        }
        return area;

    }

    private int dfs(int[][] grid,int i,int j){
        if (i<0||i==m||j<0||j==n)
            return 0;
        if (grid[i][j]==0)
            return 0;
        //只有陆地能进入这个函数，将陆地淹没可以避免维护visited数组
        grid[i][j]=0;
        return dfs(grid,i-1,j)+dfs(grid,i+1,j)+dfs(grid,i,j+1)+dfs(grid,i,j-1)
                +1;

    }

    public int numIslands(String[][] grid) {
        m= grid.length;
        n=grid[0].length;
        int area=0;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                //dfs函数负责将陆地周围的陆地都淹没，这样可以避免重复计算岛屿数量，直至遇到下一个陆地（该陆地必然是前面的陆地无法访问到的土地，即无法淹没
                if (grid[i][j]=="1"){
                    area+=1;
                    dfs(grid,i,j);
                }

            }
        }
        return area;


    }

    private void dfs(String[][] grid,int i,int j){
        if (i<0||i==m||j<0||j==n)
            return ;
        if (grid[i][j]=="0")
            return ;
        //只有陆地能进入这个函数，将陆地淹没可以避免维护visited数组
        grid[i][j]="0";
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);


    }

    public void solve(String[][] board) {
        m=board.length;
        n=board[0].length;
        boolean[][] visited=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=="X")
                //if(board[i][j]=="X"&&!visited[i][j]){

                    dfs_board(i,j,board,visited);
                    //visited[i][i]=true;


            }
        }
        System.out.println(board);

    }

    private void dfs_board(int i,int j,String[][] board,boolean[][] visited){
        if(i<0||i==m||j<0||j==n)
            return;
        if((i==0||j==0||i==m-1||j==n-1)&&board[i][j]=="O")
            return;
        if (visited[i][j])
            return;
        visited[i][j]=true;
        if(board[i][j]!="X")
            board[i][j]="X";


        dfs_board(i-1,j,board,visited);
        dfs_board(i+1,j,board,visited);
        dfs_board(i,j+1,board,visited);
        dfs_board(i,j-1,board,visited);
        //visited[i][j]=true;
    }

    /*
       Backtracking
     */

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        doCombination(new StringBuilder(), combinations, digits);
        return combinations;
    }

    private void doCombination(StringBuilder prefix, List<String> combinations, final String digits) {
        if (prefix.length() == digits.length()) {
            combinations.add(prefix.toString());
            return;
        }
        int curDigits = digits.charAt(prefix.length()) - '0';
        String letters = KEYS[curDigits];
        for (char c : letters.toCharArray()) {
            prefix.append(c);                         // 添加
            doCombination(prefix, combinations, digits);
            prefix.deleteCharAt(prefix.length() - 1); // 删除
        }

    }

    public List<String> restoreIpAddresses(String s) {
        List<String>  res=new ArrayList<>();
        restoreIp(0,res,new StringBuilder(),s);
        return res;
    }

    private void restoreIp(int k,List<String> res,StringBuilder track,String s){
        if (k==4||s.length()==0){
            if (k==4&&s.length()==0){
                res.add(track.toString());

            }
            return;
        }


        for(int i=0;i<s.length()&&i<=2;i++){
             String part=s.substring(0,i+1);
             if (Integer.valueOf(part)<=255){
                 //判断是否为前4位IP
                 if (track.length()!=0)
                     part="."+part;
                 track.append(part);
                 restoreIp(k+1,res,track,s.substring(i+1));
                 track.delete(track.length()-part.length(),track.length());
             }

        }

    }

    public List<List<String>> partition(String s) {
        List<List<String>> partitions=new ArrayList<>();
        boolean[] used=new boolean[s.length()];
        List<String> track=new ArrayList<>();
        doPartition(s,track,partitions);
        return null;

    }

    private void doPartition(String s,List<String> track,List<List<String>> partitions){

        for(int i=0;i<s.length();i++){
            if(isPalindrome(s,0,i)){
                track.add(s.substring(0,i+1));
                doPartition(s,track,partitions);
                track=track.subList(0,i-1);
            }
        }

    }

    private boolean isPalindrome(String s ,int start,int end){
        if(s.length()==1){
            return true;
        }
        //双指针用while
        while(start<end){
            if(s.charAt(start)!=s.charAt(end)){

                return false;
            }
            start++;
            end--;
        }
        return true;
    }



    public char FirstNotRepeatingChar(String str) {
        HashMap<Character, Integer> mp = new HashMap<>();
        //统计每个字符出现的次数
        for(int i = 0; i < str.length(); i++)
            mp.put(str.charAt(i), mp.getOrDefault(str.charAt(i), 0) + 1);
        //找到第一个只出现一次的字母
        for(int i = 0; i < str.length(); i++)
            if(mp.get(str.charAt(i)) == 1)
                return str.charAt(i);

        return ' ';
    }

    public long continuousSubarrays(int[] nums) {
        long ans = 0;
        TreeMap<Integer, Integer> window = new TreeMap<>();
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            window.merge(nums[right], 1, Integer::sum);
            while (window.lastKey() - window.firstKey() > 2) {
                if (window.merge(nums[left], -1, Integer::sum) == 0) {
                    window.remove(nums[left]);
                }
                left++;
            }
            ans += right - left + 1;
            right++;
        }
        return ans;
    }



}
