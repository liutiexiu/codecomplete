package me.tiezhu.leetcode.quiz;

import me.tiezhu.leetcode.common.TreeNode;
import me.tiezhu.leetcode.common.algorithm.TreeAlgorithm;

import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/maximum-binary-tree/description/
 *
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 *
 * Construct the maximum tree by the given array and output the root node of this tree.
 */
public class MaxBinTreeAs654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        } else if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        int maxIndex = 0;
        for (int i = 1;i < nums.length;i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(nums[maxIndex]);
        // 修改bug，结束的边界值搞错
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, maxIndex));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, maxIndex + 1, nums.length));

        return root;
    }

    public static void main(String[] args) {
        TreeAlgorithm treeAlgorithm = new TreeAlgorithm();
        MaxBinTreeAs654 quiz654 = new MaxBinTreeAs654();
        int [] v = new int[]{3,2,1,6,0,5};

        TreeNode root = quiz654.constructMaximumBinaryTree(v);
        treeAlgorithm.fillBinaryTree(root);
        treeAlgorithm.printFullBinaryTree(root);
    }
}
