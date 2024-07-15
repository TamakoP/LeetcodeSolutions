package Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: Tamako
 * @Description: 用栈实现队列
 * @Date: 2024/7/15 下午2:27
 * @Version: 1.0
 */
class MyQueue {
    private Stack<Integer> s1=new Stack<>();
    private Stack<Integer> s2=new Stack<>();
    /**
     * @Description 出队：为保证先进先出，出的是栈底元素，所以每遇到一次出队操作，必须将当前s1中的所有元素pop出来
     * @Param []
     * @return int
     */
    public int pop(){
       in2out();
       return s2.pop();
    }
    /**
     * @Description 入队：直接push
     * @Param [e]
     * @return void
     */
    public void push(int e){
        s1.push(e);
    }

    private void in2out(){
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
    }

    public int peek(){
        in2out();
        return  s2.peek();
    }
}

class MyStack{
    private Queue<Integer> queue;
    public MyStack(){
        queue=new LinkedList<>();
    }
    public void push(int e){
        queue.add(e);
        int curS=queue.size();
        while(curS-->1){
            queue.add(queue.poll());
        }

    }

    public int pop(){
        return queue.remove();
    }

    public int top(){
        return queue.peek();
    }
}