package leetcode;

import java.util.*;

/**
 * @description: 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 链接:https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * @author: zhukai
 * @date: 2019/7/16 16:46
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        String s = "abba";
        System.out.println(l.lengthOfLongestSubstring4(s) + "");
    }

    /**
     * 方法一、暴力求解法，遍历获取所有的子串，再判断子串是否含有相同字符
     * 时间复杂度:O(n3)
     * 空间复杂度:O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String childStr = s.substring(i, j);
                if (!hasRepeatChar(childStr)) {
                    result = Math.max(childStr.length(), result);
                }
            }
        }

        return result;
    }

    /**
     * 方法二、滑动窗口法
     * 时间复杂度:O(n)，最差的情况下，每个字符会被访问两次
     * 空间复杂度:O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int result = 0;
        int i = 0;
        int j = 0;
//        List<Character> chars = new ArrayList<>();
        Set<Character> chars = new HashSet<>();
        while (i < s.length() && j < s.length()) {
            if (!chars.contains(s.charAt(j))) {
                result = Math.max(j - i + 1, result);
                chars.add(s.charAt(j));
                j++;
            } else {
                // 将初始索引右移一位
                chars.remove(s.charAt(i));
                i++;
            }
        }
        return result;
    }

    /**
     * 方法三、优化滑动窗口法，使用HashMap
     * 时间复杂度:O(n)，索引j会迭代n次
     * 空间复杂度:O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            result = Math.max(j - i + 1, result);
            map.put(s.charAt(j), j + 1); // j+1是为了跳过重复字符
        }
        return result;
    }

    /**
     * 方法三、优化滑动窗口法，使用字符集
     * 时间复杂度:O(n)，索引j会迭代n次
     * 空间复杂度:O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring4(String s) {
        int result = 0;
        int[] index = new int[128];
        for (int i = 0, j = 0; j < s.length(); j++) {
            i = Math.max(index[s.charAt(j)], i);
            result = Math.max(j - i + 1, result);
            index[s.charAt(j)] = j + 1;
        }
        return result;
    }

    /**
     * 字符串中是否含有重复字符
     *
     * @param s
     * @return
     */
    public boolean hasRepeatChar(String s) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (!chars.contains(s.charAt(i))) {
                chars.add(s.charAt(i));
            } else {
                return true;
            }
        }
        return false;
    }
}
