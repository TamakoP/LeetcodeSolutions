package dynamicStrategy;

import java.awt.desktop.SystemEventListener;
import java.util.Arrays;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/10/16 下午3:41
 * @Version: 1.0
 */
public class dsTest {
    public static void main(String[] args){
        int[] nums={1,7,5,11,5};
        dsSolutions dss=new dsSolutions();
        char c='A'+1;
        //valueOf将对应的数字以字符串的形式返回：输入66返回”66“，若输入c返回”B“
        String s=String.valueOf(c);
        String s1="2111";
        int i=Integer.valueOf(s1.substring(0,2));
        int[] temps={73,74,75,71,69,72,76,73};
        String s2="rad";
        String s3="apple";
        //System.out.println(i);
        System.out.println(dss.minDistance( s2,s3));
    }
}
