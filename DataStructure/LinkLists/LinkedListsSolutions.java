package LinkLists;


import java.util.Stack;

/**
 * @Author: Tamako
 * @Description: 链表相关题解
 * @Date: 2024/7/9 下午5:07
 * @Version: 1.0
 */
public class LinkedListsSolutions {


    /**
     * @date 2024/7/9 下午5:11
     * @Description 寻找两个链表的交叉节点
     * @Param headA headB
     * @return LinkLists.ListNode
     */
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        ListNode l1=headA,l2=headB;

        while(l1!=l2){
            l1=(l1==null)?headB:l1.next;
            l2=(l2==null)?headA:l2.next;
        }
        return l1;

    }
    /**
     * @Description 链表反转，使用头插法
     * @Param headNode
     * @return LinkLists.ListNode
     */
    public ListNode reversedLinkedList(ListNode headNode){
        //创建一个虚拟头节点
        ListNode dummyHead=new ListNode<>();
        while(headNode!=null){
            ListNode next=headNode.next;
            headNode.next=dummyHead.next;
            dummyHead.next=headNode;
            headNode=next;
        }
        return dummyHead.next;
    }
    /**
     * @Description 归并两个有序的链表，采用递归
     * @Param [l1, l2]
     * @return LinkLists.ListNode 返回表头
     */
    public ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.e<l2.e){
            l1.next=mergeTwoLists(l1.next,l2);
             return l1;
        }else{
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
    /**
     * @Description 删除有序链表的重复节点：从最后一个节点开始往前，找到上一个不一样的节点，直接改变next的指向。空间复杂度为 O(1)
     * @Param [head]
     * @return LinkLists.ListNode
     */
    public ListNode removeDuplicates(ListNode<Integer> head){
        if(head==null||head.next==null) return head;
        head.next=removeDuplicates(head.next);
        if(head.e.equals(head.next.e)){
            return head.next;
        }else{
            return head;
        }

    }
    /**
     * @Description 交换链表中的相邻节点
     * @Param [head]
     * @return LinkLists.ListNode
     */
    public ListNode swapPairNodes(ListNode head){
        //创建虚拟节点node
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null) {
            ListNode l1 = pre.next, l2 = pre.next.next;
            ListNode next = l2.next;
            l1.next = next;
            l2.next = l1;
            pre.next = l2;
            pre = l1;
        }
        return node.next;
    }
    /**
     * @Description 链表求和：从后往前一一匹配相加（使用栈先进后出），新链表采用头插法
     * @Param [hA, hB]
     * @return LinkLists.ListNode
     */
    public ListNode addTwoLinks(ListNode hA,ListNode hB){
        Stack<Integer> sa=buildStack(hA),sb=buildStack(hB);
        //创建虚拟头节点
        LinkedList ll=new LinkedList<>();
        while(!sa.empty()&&!sb.empty()){
           ll.addFirst((sa.pop()+ sb.pop())%10);

        }
        if(sa.empty()){
            while(!sb.empty()){
                ll.addFirst(sb.pop());

            }
        }
        if(sb.empty()){
            while(!sa.empty()){
                ll.addFirst(sa.pop());
            }
        }
        return ll.getHead();

    }
    private Stack<Integer> buildStack(ListNode<Integer> head){
        Stack<Integer> stack=new Stack<>();
        while(head!=null){
            stack.push(head.e);
            head=head.next;
        }
        return stack;
    }
    /**
     * @Description 回文链表判断：使用反转链表的方式，若是回文，反转之后仍不变
     * @Param [head]
     * @return boolean
     */
    public boolean isPalindrome(ListNode<Integer> head) {

        if(head==null||head.next==null)
            return true;
        ListNode newHead=head;
        ListNode reHead=reversedLinkedList(head);
        //System.out.println(head.next.e);
        while(newHead!=null){
            if(reHead.e.equals(newHead.e)){
                newHead=newHead.next;
                reHead=reHead.next;
                System.out.println(newHead.toString());
                System.out.println(reHead.toString());
            }
            else{
                return false;
            }

        }
        return  true;
    }
}
