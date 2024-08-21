package Tree_Graph;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/20 下午2:55
 * @Version: 1.0
 */
public class test {
    public static void main (String[] args){
        int[][] graph={{1,3},{0,2},{1,3},{0,2}};
        Solution ss=new Solution();
        System.out.println(ss.isBipartite(graph));

    }
}
