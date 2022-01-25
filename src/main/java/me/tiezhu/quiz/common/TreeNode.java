package me.tiezhu.quiz.common;

import me.tiezhu.quiz.utils.Logging;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        Logging.println("new TreeNode:" + x);
        val = x;
    }
}
