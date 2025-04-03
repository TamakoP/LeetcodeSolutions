package search;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/2/12 下午3:55
 * @Version: 1.0
 */
public class searchTest {
    public static void main(String[] args){
        int[] candidates={10, 1, 2, 7, 6, 1, 5};
        searchSolutions ss=new searchSolutions();
        System.out.println(ss.combinationSum2(candidates,8));
    }
}
