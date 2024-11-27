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
}
