package me.tiezhu.quiz.leetcode;

import java.util.Arrays;

/**
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 *
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 *
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LISAs300 {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 从一个看懂了的动态规划描述写出来的
        int[] seq = new int[nums.length + 1]; // 下标表示LIS的长度，值表示形成该LIS的last value的最小值
        seq[0] = 0; // max length of seq

        for (int i = 0;i < nums.length;i++) {
            int j = 1;
            while(j <= seq[0]) {
                if (nums[i] < seq[j]) { // 发现更小的值
                    seq[j] = nums[i];
                    break;
                }
                j++;
            }
            System.out.printf("i=%d, j=%d, after while %s%n", i, j, Arrays.toString(seq));
            if (j > seq[0]) { // 找到更长的序列
                seq[0] = j;
                seq[j] = nums[i];
                System.out.printf("i=%d, j=%d, n>s %s%n", i, j, Arrays.toString(seq));
            }
        }

        return seq[0];
    }

    // 错误解，只考虑了比之前大就收的情况
    public int lis1(int[] nums, int currentValue, int nextIndex, int count) {
        if (nextIndex == nums.length - 1) {
            return currentValue < nums[nextIndex] ? count + 1 : count;
        }

        for (int i = nextIndex;i < nums.length;i++) {
            if (currentValue < nums[i]) {
                return lis1(nums, nums[i], i + 1, count + 1);
            } else if (currentValue > nums[i]) {
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,99,100,2,3,4,5,10};
        int[] nums2 = {10,9,2,5,3,11};
        int[] nums3 = {5,6,7,1};
        System.out.println(new LISAs300().lengthOfLIS(nums1));
        System.out.println(new LISAs300().lengthOfLIS(nums2));
        System.out.println(new LISAs300().lengthOfLIS(nums3));
    }
}
