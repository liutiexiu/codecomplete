package me.tiezhu.leetcode.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 *
 * Example:
 * Input: [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 *
 */
public class FindDupInArrayAs442 {
    public List<Integer> findDuplicates(int[] nums) {
        return findDuplicates1(nums);
    }

    // 这个方法不对啊，值域是有限制的
    public List<Integer> findDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        for (int value : nums) {
            int index = value > nums.length ? value - 1000000001 : abs(value) - 1;
            nums[index] = nums[index] > 0 ? -nums[index] : 1000000000 + abs(nums[index]);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] > nums.length) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public int abs(int v) {
        return v >= 0 ? v : -v;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,3,2,7,8,2,3,1};
        int[] nums2 = {10,2,5,10,9,1,1,4,3,7};
        System.out.println(new FindDupInArrayAs442().findDuplicates(nums1));
        System.out.println(new FindDupInArrayAs442().findDuplicates(nums2));
    }
}
