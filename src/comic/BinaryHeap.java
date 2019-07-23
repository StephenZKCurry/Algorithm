package comic;

import java.util.Arrays;

/**
 * @description: 二叉堆的实现
 * @author: zhukai
 * @date: 2019/7/22 16:24
 */
public class BinaryHeap {


    /**
     * 创建二叉堆
     *
     * @param array
     */
    public void createBinartHeap(int[] array) {
        // 从最后一个非叶子节点开始遍历，进行下沉
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i);
        }
    }

    /**
     * 节点的下沉调整
     *
     * @param array 待调整的堆数组
     * @param index 要下沉的节点
     */
    public void downAdjust(int[] array, int index) {
        int temp = array[index];
        int minChildIndex = 2 * index + 1;
        if (2 * index + 2 < array.length) {
            // 节点有右孩子
            if (array[2 * index + 1] > array[2 * index + 2]) {
                minChildIndex = 2 * index + 2;
            }
        }
        if (temp > array[minChildIndex]) {
            array[index] = array[minChildIndex];
            array[minChildIndex] = temp;
        }
    }

    /**
     * 节点的上浮调整
     *
     * @param array
     * @param index
     */
    public void upAdjust(int[] array, int index) {

    }

    public static void main(String[] args) {
        BinaryHeap binaryHeap = new BinaryHeap();
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        binaryHeap.createBinartHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
