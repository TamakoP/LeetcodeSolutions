package math;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/2/13 上午10:34
 * @Version: 1.0
 */
public class mathSolutions {
    public String toHex(int num) {
        char[] map= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb=new StringBuilder();
        if (num == 0){
            return "0";
        }
        //记录每次取余
        while(num!=0){
            sb.append(map[num%16]);
            num=num/16;
        }
        return sb.reverse().toString();

    }
}
