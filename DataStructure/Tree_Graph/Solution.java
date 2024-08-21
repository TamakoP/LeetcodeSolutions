package Tree_Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/20 下午2:33
 * @Version: 1.0
 */
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
}
