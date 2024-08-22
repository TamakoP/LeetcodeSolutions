package DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/22 上午11:30
 * @Version: 1.0
 */
public class dpTest {
    public static void main(String[] args){
        List<String> d=new ArrayList<>(Arrays.asList("ale","applea","monkey","plea"));
        dpSolution dps=new dpSolution();
        System.out.println(dps.findLongestWord( "abpcplea",d));
    }
}
