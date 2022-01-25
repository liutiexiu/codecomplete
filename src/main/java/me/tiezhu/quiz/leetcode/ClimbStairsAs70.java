package me.tiezhu.quiz.leetcode;

/**
 * @author liushuai
 * Created on 2022/1/25
 */
public class ClimbStairsAs70 {

    // leetcode递归不行还
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int[] results = new int[n];
        results[0] = 1;
        results[1] = 2;
        for (int i = 2; i < n; i++) {
            results[i] = results[i - 1] + results[i - 2];
        }
        return results[n - 1];
    }
}
