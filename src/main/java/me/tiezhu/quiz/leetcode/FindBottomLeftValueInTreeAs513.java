package me.tiezhu.quiz.leetcode;

import me.tiezhu.quiz.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/description/
 *
 * Given a binary tree, find the leftmost value in the last row of the tree
 *
 */
public class FindBottomLeftValueInTreeAs513 {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }

        final TreeNode padding = new TreeNode(0);

        Queue<TreeNode> list = new LinkedList<>(Arrays.asList(padding, root));

        int levelLeft = 0;
        while (!list.isEmpty()) {
            TreeNode current = list.poll();
            // 修复bug，笔误一处
            final boolean levelFirst = current == padding;
            if (levelFirst) {
                // 修复bug，边界条件没考虑到最后一个是分隔符
                if (list.isEmpty()) {
                    break;
                }

                current = list.poll();
                levelLeft = current.val;
                list.offer(padding);
            }

            if (current.left != null) {
                list.offer(current.left);
            }
            if (current.right != null) {
                list.offer(current.right);
            }
        }

        return levelLeft;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);

        System.out.println(new FindBottomLeftValueInTreeAs513().findBottomLeftValue(root));
    }
}
