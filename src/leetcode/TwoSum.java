package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 两数之和
 * 给定一个整数数组nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例：
 * 给定 nums = [2, 7, 11, 15], target = 9,因为 nums[0] + nums[1] = 2 + 7 = 9,所以返回 [0, 1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/two-sum/
 * @author: zhukai
 * @date: 2019/7/15 15:50
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 5, 11};
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum2(nums, 10);
        twoSum.output(result);
    }

    /**
     * 方法一、暴力求解法，两次for循环
     * 时间复杂度:O(n2)
     * 空间复杂度:O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法二、双指针法
     * 时间复杂度:O(n)
     * 空间复杂度:O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        boolean hasFound = false; // 是否找到结果
        int[] result = new int[2];
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i];
        }
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                hasFound = true;
                break;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }

        if (hasFound) {
            // 找到原数组中的下标
            for (int k = 0; k < temp.length; k++) {
                if (temp[k] == nums[i]) {
                    result[0] = k;
                    // 退出循环，防止取到后面相等数的下标
                    break;
                }
            }
            for (int k = 0; k < temp.length; k++) {
                if (temp[k] == nums[j] && k != result[0]) {
                    result[1] = k;
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("No two sum solution");
        }
    }

    /**
     * 方法三、使用HashMap，空间换取时间
     * 时间复杂度:O(n)
     * 空间复杂度:O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        // 两次循环
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], i);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
//                result[0] = i;
//                result[1] = map.get(target - nums[i]);
//                return result;
//            }
//        }

        // 一次循环
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 输出数组
     *
     * @param array
     */
    public void output(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                sb.append(array[i]);
                sb.append(",");
            } else {
                sb.append(array[i]);
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
