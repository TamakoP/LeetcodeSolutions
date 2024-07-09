package LinkLists;


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
     * @param headA headB
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
}
