package HashMap;

import java.util.*;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/7/19 下午1:44
 * @Version: 1.0
 */
public class Solutions {
    /**
     * @Description 两数之和
     * @Param [nums, target]
     * @return int[]
     */
    public int[] twoSum(int[] nums, int target){
        Map<Integer,Integer> map=new HashMap();
        int[] location=new int[2];
        Arrays.fill(location,-1);
        for(int i=0;i<nums.length;i++){
            map.put(i,nums[i]);
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            location[0]=entry.getKey();
            int minus=target-entry.getValue();
            for(int i=location[0]+1;i<nums.length;i++){
                if(minus==nums[i]){
                    location[1]=i;
                    return location;
                }
            }
        }
        return location;
    }

    /* *
     * @Description 判断是否有重复元素：HashSet只能存放不重复的元素
     * @Param
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() < nums.length;
    }


    /**
     * @Description 最长和谐序列
     * @Param [nums]
     * @return int
     */
    public int findLHS(int[] nums){
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int longest=0;
        for(int num:map.keySet()){
            if(map.containsKey(num+1)){
                int longer=map.get(num)+map.get(num+1);
                longest=longest<longer?longer:longest;
            }
        }
        return longest;

    }
    /**
     * @Description 最长连续递增序列
     * @Param [nums]
     * @return int
     */
    public int longestConsecutive(int[] nums){
        Map<Integer,Integer> map=new HashMap<>();
        //使用哈希表可以将数组中的重复元素避开
        for(int num: nums){
            map.put(num,1);
        }
        int ls=0;
        for(int num:map.keySet()){
            if(!map.containsKey(num-1)){
                int i=num,cs=1;
                while(map.containsKey(++i))
                    cs+=1;
                ls=Math.max(ls,cs);
            }

        }
        return ls;
    }


}
