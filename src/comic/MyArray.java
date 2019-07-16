package comic;

/**
 * @description: 数组的增删改查
 * @author: zhukai
 * @date: 2019/7/15 9:08
 */
public class MyArray {

    private int[] array;
    private int size;

    public MyArray(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    /**
     * 数组插入元素
     *
     * @param element 插入的元素
     * @param index   插入的位置
     * @throws Exception
     */
    public void insert(int element, int index) throws Exception {
        // 判断访问下标是否超出范围
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出数组实际元素范围");
        }
        // 元素数量达到数组容量上限，数组需要扩容
        if (size >= array.length) {
            expand();
        }
        // 从右向左循环，逐个元素向右挪一位
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        // 插入新元素
        array[index] = element;
        size++;
    }

    /**
     * 数组删除元素
     *
     * @param index 删除的位置
     * @return 删除的元素
     * @throws Exception
     */
    public int remove(int index) throws Exception {
        // 判断访问下标是否超出范围
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("超出数组实际元素范围");
        }
        int deleteElement = array[index];
        // 从左向右循环，逐个元素向左挪一位
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return deleteElement;
    }

    /**
     * 数组扩容
     */
    public void expand() {
        int[] newArray = new int[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * 输出数组
     */
    public void output() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        MyArray myArray = new MyArray(5);
        // 数组插入
        myArray.insert(1, 0);
        myArray.insert(3, 1);
        myArray.insert(5, 2);
        myArray.insert(7, 3);
        myArray.insert(9, 4);
        myArray.insert(11, 5);
        // 数组删除
        myArray.remove(3);
        myArray.output();
    }
}
