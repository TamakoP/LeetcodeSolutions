package yanfa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/3/21 下午1:54
 * @Version: 1.0
 */
public class solution {
    //每轮兑换只能向老板借一个空瓶,公式3+2n=原始空瓶子数
    public int bubblecan(int kong){
        if(kong==2||kong==3)
            return 1;
        int temp=kong-3;
        if(temp%2==0)
            return temp/2+1;
       return temp/2+2;
    }
    public void deduplicate(int[] array){
        Arrays.sort(array);
        int first=array[0];
        System.out.println(first);
        for(int i=1;i<array.length;i++){
            if(array[i]==first){
                continue;
            }
            first=array[i];
            System.out.println(array[i]);

        }
    }
    public Long hexToDec(String origin){
        origin=origin.substring(2);

        Map<Character,Integer> map=new HashMap<>();
        //初始化
        for (int i = 0; i <6 ; i++) {
            map.put((char) ('A'+i),i+10);
        }
        Long res= 0L;
        int length=origin.length();
        for(int i=0;i<length;i++){
            char temp=origin.charAt(i);
            int factor=0;
            if(temp>=65)
                factor= map.get(temp);
            else
                factor=temp-'0';
            res += (long)Math.pow(16,length-1-i)*factor;
        }
        return res;

    }


}
