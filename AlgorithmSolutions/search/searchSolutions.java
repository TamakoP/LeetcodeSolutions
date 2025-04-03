package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/2/12 下午2:43
 * @Version: 1.0
 */
public class searchSolutions {
    /**
     * @Description 含有相同元素的组合求和
     * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     * A yanfa.solution set is:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * @Param [candidates, target]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(candidates);   // 1 1 2 5 6 7 10
        backtracking(new ArrayList<>(), combinations, new boolean[candidates.length], 0, target, candidates);
        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations,
                              boolean[] hasVisited, int start, int target, final int[] candidates) {
        int curSum=0;
        if(!tempCombination.isEmpty()){
            curSum=tempCombination.stream().reduce(Integer::sum).orElse(0);
        }

        if(curSum==target){
            //这里要注意直接add(tempCombination)不行，这样路径永远只有一条
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }
        //minus记录离target的距离，由于candidates已做过排序（值只会越取越大），若余下的距离在下一个候选值中取不到，那么余下的候选值都不符合
        //说明tempCombination新加入的候选值也不符合要求，应该撤回
        //e.g. [1 1 2 5]fail [1 1 2 6]包fail 循环直接break
        //按照这个减法的思路如何一劳永逸

        for(int i=start+1;i< candidates.length;i++){
            hasVisited[i-1]=true;
            tempCombination.add(candidates[i-1]);
            int minus=target-tempCombination.stream().reduce(Integer::sum).orElse(0);
            if(minus>0&&minus<candidates[i]){
              tempCombination.remove(tempCombination.toArray().length-1);
              hasVisited[i-1]=false;
              continue;
            }

           backtracking(tempCombination,combinations,hasVisited,i,target,candidates);
            tempCombination.remove(tempCombination.toArray().length-1);
           hasVisited[i-1]=false;
        }
    }

}
