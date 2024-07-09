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
     * @Description 等等 还没思索完
     * @param headA headB
     * @return LinkLists.ListNode
     */
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        ListNode l1=headA,l2=headB;

        if (l1.next != null) {
            l1 = l1.next;
        }
        if (l2.next != null) {
            l2 = l2.next;
        }
        //此时l1和l2都指向链表的最后一个元素
        headA = l1;
        headB = l2;
        return l1;


    }
}
