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
        l2.addLast(5);
        l2.addLast(6);
        l2.addLast(4);




        node=solutions.addTwoLinks(l1.getHead(),l2.getHead());
        while(node!=null){
            System.out.println(node.e);
            node=node.next;
        }


    }
}
