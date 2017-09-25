package me.tiezhu.leetcode.quiz;

public class HouseRobberCircleAs213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int max1 = robHelper(nums, 0, nums.length - 2);
        int max2 = robHelper(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    public int robHelper(int[] nums, int from, int to) {
        if (from == to) {
            return nums[from];
        }

        int[] dp = new int[nums.length];
        dp[from] = nums[from];
        dp[from + 1] = Math.max(nums[from + 1], dp[from]);

        for (int k = from + 2; k <= to; k++) {
            dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k]);
        }

        return dp[to];
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {9,100,3,4,5,6};
        System.out.println(new HouseRobberCircleAs213().rob(nums1));
        System.out.println(new HouseRobberCircleAs213().rob(nums2));
    }
}
