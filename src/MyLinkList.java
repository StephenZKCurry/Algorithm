/**
 * @description: 链表的增删改查
 * @author: zhukai
 * @date: 2019/7/15 9:35
 */
public class MyLinkList {

    // 链表头节点
    private Node head;
    // 链表尾节点
    private Node last;
    // 链表长度
    private int size;

    public MyLinkList() {
        size = 0;
    }

    /**
     * 链表插入元素
     *
     * @param data  插入元素
     * @param index 插入位置
     * @throws Exception
     */
    public void insert(int data, int index) throws Exception {
        // 判断访问下标是否超出范围
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertNode = new Node(data);
        if (size == 0) {
            // 空链表
            head = insertNode;
            last = insertNode;
        } else if (index == 0) {
            // 插入头部
            insertNode.next = head;
            head = insertNode;
        } else if (index == size) {
            // 插入尾部
            last.next = insertNode;
            last = insertNode;
        } else {
            // 插入中间
            Node preNode = get(index - 1);
            Node postNode = get(index);
            preNode.next = insertNode;
            insertNode.next = postNode;
        }
        size++;
    }

    /**
     * 链表删除元素
     *
     * @param index 删除的位置
     * @return
     * @throws Exception
     */
    public Node remove(int index) throws Exception {
        // 判断访问下标是否超出范围
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node deleteNode = null;
        if (index == 0) {
            // 删除头节点
            deleteNode = head;
            head = head.next;
        } else if (index == size - 1) {
            // 删除尾节点
            deleteNode = last;
            last = get(index - 1);
            last.next = null;
        } else {
            // 删除中间节点
            deleteNode = get(index);
            Node preNode = get(index - 1);
            Node postNode = get(index + 1);
            preNode.next = postNode;
        }
        size--;
        return deleteNode;
    }

    /**
     * 获取链表节点
     *
     * @return
     */
    public Node get(int index) throws Exception {
        // 判断访问下标是否超出范围
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 输出链表
     */
    public void output() throws Exception {
//        for (int i = 0; i < size; i++) {
//            System.out.println(get(i).data);
//        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /**
     * 链表节点
     */
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        MyLinkList myLinkList = new MyLinkList();
        // 链表插入
        myLinkList.insert(1, 0);
        myLinkList.insert(3, 1);
        myLinkList.insert(5, 2);
        myLinkList.insert(7, 3);
        myLinkList.insert(9, 4);
        // 链表删除
        myLinkList.remove(1);
        myLinkList.output();
    }
}
