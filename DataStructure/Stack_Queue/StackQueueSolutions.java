package Stack_Queue;

import java.util.Stack;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/7/16 下午3:55
 * @Version: 1.0
 */
public class StackQueueSolutions {
    /**
     * @Description 括号匹配：如遇右括号进栈，遇左括号则出栈顶元素与之匹配，不成功直接false，成功则pop下一个元素继续匹配，直至栈为空
     * @Param [s]
     * @return boolean
     */
    public boolean isValid(String s){
        Stack<Character> stack=new Stack<>();
        for(char c :s.toCharArray()){
            if(c=='('||c=='{'||c=='['){
                stack.push(c);
            }else{
                //若栈空，则说明无可匹配的右括号
                if(stack.isEmpty())
                    return false;
                char c1=stack.pop();
                boolean flag1=c==')'&&c1=='(';
                boolean flag2=c==']'&&c1=='[';
                boolean flag3=c=='}'&&c1=='{';
                if(flag1||flag2||flag3)
                    return false;
            }
        }
        //若全部匹配成功，则栈为空，否则栈中还有元素
        return stack.isEmpty();
    }
    /**
     * @Description 数组中元素与下一个比它大的元素之间的距离:将数组中的元素依次入栈，将下一个压栈的元素宇当前栈顶元素相比较，若>，则距离为1
     * @Param [temperatures]
     * @return int[]
     */
    /*Input: [73, 74, 75, 71, 69, 72, 76, 73]
      Output: [1, 1, 4, 2, 1, 1, 0, 0]
     */
    public int[] dailyTemperatures(int[] temperatures){
        //stack存放对应元素的距离其最近并且大于其元素的下标
        Stack<Integer> stack=new Stack<>();
        int[] distance=new int[temperatures.length];
        for(int i=0;i<temperatures.length;i++){
            while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                  int j=stack.pop();
                  distance[j]=i-j;
            }
            //数组首元素的stack值设置为0，压栈操作放在比较之后能确保比较时stack的栈顶是上一个元素的下标
            stack.push(i);
        }
        return distance;
    }
}
