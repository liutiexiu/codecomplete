package me.tiezhu.leetcode.quiz;

import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 * Given an array of n integers where n > 1
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Solve it without division and in O(n).
 *
 * For example, given [1,2,3,4], return [24,12,8,6]
 */
public class ProductOfArrayExceptSelfAs238 {

    public int[] productExceptSelf(int[] nums) {
        // 搜的解法，把自己左右两边的提前算好，到自己这儿一乘就行了
        int[] result = new int[nums.length];

        result[nums.length - 1] = 1;
        for (int i = nums.length - 2;i >= 0;i--) {
            result[i] = nums[i + 1] * result[i + 1];
        }

        int leftProductTotal = 1;
        for (int i = 0;i < nums.length;i++) {
            result[i] = leftProductTotal * result[i];
            leftProductTotal = leftProductTotal * nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4};
        System.out.println(Arrays.toString(new ProductOfArrayExceptSelfAs238().productExceptSelf(nums)));
    }
}
