package intern17;
import java.util.*;
/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2025/3/27 上午9:51
 * @Version: 1.0
 */
public class Main {
    static class Element{

    }

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        Main obj=new Main();
        int num=in.nextInt();
        in.nextLine();
        String[] all=new String[num];
        int[] weights=new int[10];
        for (int i = 0; i < num; i++) {
            all[i]=in.nextLine();
            int length=all[i].length();
            for(int j=0;j<length;j++){
                //计算权重
                weights[all[i].charAt(j)-'A']+=(int)Math.pow(10,length-j);
            }
        }
        //排序，直接sort不可以，信息丢失
        int[] position=new int[10];
        for (int i = 0; i < weights.length; i++) {
            int cur=weights[weights.length-1];
            for (int j = weights.length-2;j >=0 ; j--) {
                if(weights[j]<cur){
                    int temp=weights[j];
                    weights[j]=weights[j-1];
                    weights[j-1]=temp;
                    position[j]=j-1;
                }
            }
        }







    }
}
