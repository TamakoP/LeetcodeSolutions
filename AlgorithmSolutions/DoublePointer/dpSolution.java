package DoublePointer;

import java.util.List;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/22 上午11:15
 * @Version: 1.0
 */
public class dpSolution {
    /**
     * @Description 有序数组：关键字“有序”，可以做到最多遍历一遍？
     * @Param [numbers, target]
     * @return int[]
     */
    public int[] twoSum(int[] numbers, int target){
        int i=0,j=numbers.length-1;
        while(i<j){
            if(target<numbers[i]+numbers[j])
                j--;
            else
                i++;
            if(target==numbers[i]+numbers[j])
                return new int[]{i,j};
        }
        //i>j说明没有找到两个元素相加等于target
        return new int[]{-1,-1};
    }

    public boolean judgeSquareSum(int target){
        //i和j一定处于[1,target-1]之间
        //int i=1,j=target-1;
        int i=1,j= (int) Math.sqrt(target);
        int sum;
        while(i<j){
            //在java中，^表示异或，平方要使用pow函数或者自身*自身
            sum= (int) (Math.pow(i,2)+Math.pow(j,2));
            if(target==sum)
                return true;
            if(target>sum)
                i++;
            else
                j--;

        }
        return false;
    }
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                //删除左边或者右边，有一个成功即可
                return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public String findLongestWord(String s, List<String> d) {
        String longest="";
        for(String sub:d){
            int i=0,j=0;
            while(j<s.length()&&i<sub.length()){
                if(s.charAt(j)==sub.charAt(i)){
                    j++;
                    i++;
                }else{
                    j++;
                }
            }
            if(i==sub.length())
                longest=longest.length()<sub.length()?sub:longest;
        }
        return longest;
    }
}
