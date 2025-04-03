package Tree_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/20 下午2:55
 * @Version: 1.0
 */
public class test {
    public static void main (String[] args){
        String[][] graph= {
                {"1", "1", "0", "0", "0"},
                {"1", "1", "0", "0", "0"},
                {"0", "0", "1", "0", "0"},
                {"0", "0", "0", "1", "1"}
};
        //List.of方法返回一个不可修改的列表，后续不能使用add
        List<String> wordLists= new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"));

        String[][] board= {{"X","X","X","X","X"},{"X","X","O","O","X"},{"X","O","X","X","O"},{"X","O","X","X","X"}};
        String s= "25525511135";
        Solution ss=new Solution();
        ss.restoreIpAddresses(s);
        System.out.println(ss.continuousSubarrays(new int[]{5,4,2,4}));

    }
}
