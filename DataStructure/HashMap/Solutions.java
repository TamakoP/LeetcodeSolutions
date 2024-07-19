package HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
}
