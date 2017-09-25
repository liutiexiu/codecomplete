package me.tiezhu.leetcode.common;

import me.tiezhu.leetcode.utils.Logging;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        Logging.println("new TreeNode:" + x);
        val = x;
    }
}
