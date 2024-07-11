package LinkLists;



/**
 * 链表节点类
 */
class ListNode<E>{
    E e;
    ListNode next;
    ListNode(E x){
        e = x;
    }
    ListNode(E x, ListNode n){
        this.e =x;
        this.next=n;
    }
    ListNode(){
        this.next=null;
        this.e=null;
    }

    @Override
    public String toString(){
        return e.toString();
    }

}

/**
 *
 * @param <E>
 */
public class LinkedList<E> {
    /** 虚拟头节点,索引视为-1 */
    private ListNode  dummyHead;
    private int size;
    public LinkedList() {
        dummyHead = new ListNode(null);
        size=0;
    }

    public ListNode getHead(){
        return this.dummyHead.next;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
   /**
    * @date 2024/7/9 下午3:29
    * @description 在指定位置插入元素
    * @Param index
    * @Param e
    * @return void
    */
    public void add(int index,E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException();
        }
        ListNode pre=dummyHead;
        int i=0;
        while(i<index){
            pre=pre.next;
            i++;
        }
        ListNode tmp=new ListNode(e,pre.next);
        pre.next=tmp;
        size++;


    }

    /**
     * @date 2024/7/9 下午3:45
     * @Description 添加首元素
     * @Param e
     * @return void
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * @date 2024/7/9 下午3:47
     * @Description 添加末尾元素
     * @Param e
     * @return void
     */

    public void addLast(E e){
        add(size,e);
    }

    /**
     * @date 2024/7/9 下午3:53
     * @Description 获取指定索引的值
     * @Param index
     * @return E
     */
    public E get(int index){
        if(index<0||index>size){
            throw new IllegalArgumentException("get failed!index error");
        }
        ListNode cur=dummyHead;
        int i=0;
        while(i<index){
            cur=cur.next;
            i++;
        }
        return (E) cur.e;
    }

    /**
     * @date 2024/7/9 下午3:54
     * @Description 获取链表首部的元素
     * @Param
     * @return E
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * @date 2024/7/9 下午3:59
     * @Description This is description of method
     * @param
     * @return E
     */
    public E getLast(){
        return get(size);
    }

    /**
     * @date 2024/7/9 下午4:04
     * @Description 修改指定位置的元素
     * @Param index e
     * @return void
     */

    public void set(int index,E e){
        if(index<0||index>size){
            throw new IllegalArgumentException("set failed!index error");

        }
        ListNode cur=dummyHead;
        int i=0;
        while(i<index){
            cur=cur.next;
            i++;
        }
        cur.next.e=e;
    }

    /**
     * @date 2024/7/9 下午4:15
     * @Description 判断元素是否在链表里
     * @Param e
     * @return boolean
     */
    public boolean contains(E e){
        ListNode cur=dummyHead.next;
        while(cur!=null){
            if(cur.e.equals(e)){
                return true;
            }
            cur=cur.next;
        }
        return  false;
    }


    /**
     * @date 2024/7/9 下午4:25
     * @Description 删除指定位置元素，以找到的第一个为准
     * @Param index
     * @return E
     */
    public E remove(int index){
        if(index<0||index>size){
            throw new IllegalArgumentException("remove failed!index error");
        }
        ListNode pre=dummyHead;
        int i=0;
        while(i<index){
            pre=pre.next;
            i++;
        }
        E delE= (E) pre.next.e;
        pre.next=pre.next.next;
        size--;
        return delE;
    }


    /**
     * @date 2024/7/9 下午4:40
     * @Description 删除指定元素，以第一次出现为准
     * @Param e
     * @return void
     */
    public void remove(E e){
        if(!contains(e)){
            throw  new IllegalArgumentException("remove failed.e does not exist");
        }
        ListNode pre=dummyHead;
        while(pre.next!=null){
            if(pre.next.e.equals(e)){
                break;
            }
            pre=pre.next;
        }
        if(pre.next!=null){
            pre.next=pre.next.next;
            pre.next.next=null;
            size--;
        }
    }

    /**
     * @date 2024/7/9 下午4:41
     * @Description 删除所有元素e
     * @Param e
     * @return void
     */
    public void removeAll(E e){
        if(!contains(e)){
            throw  new IllegalArgumentException("remove failed.e does not exist");
        }
        ListNode pre=dummyHead;
        while(pre.next!=null){
            if(pre.next.e.equals(e)){
                pre.next=pre.next.next;
                pre.next.next=null;
                size--;
                continue;
            }
            pre=pre.next;
        }

    }
     /**
      * @date 2024/7/9 下午4:52
      * @Description 删除链表首部
      * @Param
      * @return E
      */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * @date 2024/7/9 下午4:54
     * @Description 删除链表尾部
     * @Param
     * @return E
     */
    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("LinkedList: size: %d\n",size));
        result.append("head ");
        ListNode cur = dummyHead.next;
        while (cur != null){
            result.append(cur+ "->");
            cur = cur.next;
        }
        result.append("NULL");
        return result.toString();
    }


}
