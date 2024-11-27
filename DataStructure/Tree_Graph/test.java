package Tree_Graph;

import java.util.Arrays;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/20 下午2:55
 * @Version: 1.0
 */
public class test {
    public static void main (String[] args){
        int[][] graph={{0,0,0,1},{0,1,0,0},{1,0,0,1},{1,0,1,0}};
        Solution ss=new Solution();
        System.out.println(ss.shortestPathBinaryMatrix(graph));

    }
}
