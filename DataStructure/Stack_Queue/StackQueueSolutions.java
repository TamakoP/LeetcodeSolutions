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
}
