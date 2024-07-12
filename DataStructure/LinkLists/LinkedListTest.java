package LinkLists;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/7/11 下午12:31
 * @Version: 1.0
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedListsSolutions solutions = new LinkedListsSolutions();
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        ListNode<Integer> node=new ListNode<>();

        l1.addLast(7);
        l1.addLast(2);
        l1.addLast(4);
        l1.addLast(3);
        l1.addLast(7);
        /*ListNode node1=solutions.reversedLinkedList(l1.getHead());
        while(node1!=null){
            System.out.println(node1.e);
            node1=node1.next;
        }*/
        ListNode head=l1.getHead();
        //System.out.println(head.toString());
       solutions.isPalindrome(head);


    }
}
