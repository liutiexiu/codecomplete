package me.tiezhu.leetcode.quiz;

public class HouseRobberAs198 {
    // it's my dynamic programing
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int skipLast = 0;
        int withLast = nums[0];
        int withCurrent = 0;
        int max = withLast;
        for (int i = 1;i < nums.length;i++) {
            withLast = Math.max(skipLast, withLast);
            withCurrent = skipLast + nums[i];
            if (withLast > max) {
                max = withLast;
            }
            if (withCurrent > max) {
                max = withCurrent;
            }
            skipLast = withLast;
            withLast = withCurrent;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,1,0,0,5,6};
        int[] nums2 = {9,100,3,4,5,6};
        System.out.println(new HouseRobberAs198().rob(nums1));
        System.out.println(new HouseRobberAs198().rob(nums2));
    }
}
