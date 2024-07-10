package LinkLists;


import java.util.List;

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
}
