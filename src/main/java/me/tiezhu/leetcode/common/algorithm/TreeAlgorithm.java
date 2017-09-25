package me.tiezhu.leetcode.common.algorithm;

import me.tiezhu.leetcode.common.TreeNode;
import me.tiezhu.leetcode.utils.Logging;
import me.tiezhu.leetcode.utils.SomeMath;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreeAlgorithm {

    /**
     * 构建一个n层的完全二叉树，val从0开始，按照层序遍历递增
     *
     * @param levelCount
     * @return
     */
    public TreeNode buildFullBinaryTree(int levelCount) {
        Validate.isTrue(levelCount > 0);

        int index = 0;
        TreeNode root = new TreeNode(index++);
        Queue<TreeNode> levelCache = new LinkedList<>(Arrays.asList(root));
        int currentLevel = 0;
        int countInThisLevel = 0;

        while (currentLevel < levelCount - 1) {
            TreeNode current = levelCache.poll();
            countInThisLevel++;

            current.left = new TreeNode(index++);
            current.right = new TreeNode(index++);
            levelCache.offer(current.left);
            levelCache.offer(current.right);

            if (countInThisLevel >= (currentLevel == 0 ? 1 : (2 << (currentLevel - 1)))) {
                currentLevel++;
                countInThisLevel = 0;

                Logging.println("goto level " + currentLevel);
            }
        }

        Logging.printlnAlways("build full binary tree level count:%d, node count:%d", levelCount, index);
        return root;
    }

    public int countBinaryTreeLevel(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.max(countBinaryTreeLevel(root.left), countBinaryTreeLevel(root.right)) + 1;
    }

    /**
     *
     * 用值为-1的节点填满一个二叉树
     *
     * @param root
     */
    public void fillBinaryTree(TreeNode root) {
        fillBinaryTree(root, -1);
    }

    /**
     *
     * 用指定val填满一个二叉树
     *
     * @param root
     * @param val
     */
    public void fillBinaryTree(TreeNode root, int val) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        int levelCount = countBinaryTreeLevel(root);
        Logging.println("tree origin level count:%d", levelCount);

        fillBinaryTree(root, val, levelCount);
    }

    /**
     *
     * 用指定的val填满一个指定层数的二叉树
     *
     * @param root
     * @param val
     * @param depth
     */
    public void fillBinaryTree(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return;
        }

        if (root.left == null) {
            root.left = new TreeNode(val);
        }
        fillBinaryTree(root.left, val, depth - 1);

        if (root.right == null) {
            root.right = new TreeNode(val);
        }
        fillBinaryTree(root.right, val, depth - 1);
    }

    /**
     *
     * 打印一个完全二叉树
     * 注意，这个方法只能打印完全二叉树
     *
     * @param root
     */
    public void printFullBinaryTree(TreeNode root) {
        Validate.notNull(root);

        Queue<TreeNode> levelCache = new LinkedList<>(Arrays.asList(root));
        Queue<TreeNode> treeNodes = new LinkedList<>();

        // list nodes as list
        while(!levelCache.isEmpty()) {
            TreeNode currentNode = levelCache.poll();
            treeNodes.offer(currentNode);

            TreeNode left = currentNode.left;
            if (left != null) {
                levelCache.offer(left);
            }

            TreeNode right = currentNode.right;
            if (right != null) {
                levelCache.offer(right);
            }
        }

        int nodeCount = treeNodes.size();
        Validate.isTrue(
                SomeMath.powOf2(nodeCount + 1), "tree node count:%d, not a full binary tree", nodeCount);

        int levelCount = SomeMath.log2(nodeCount + 1);
        Validate.isTrue(levelCount == countBinaryTreeLevel(root), "bad tree");
        Logging.println("level count:%d", levelCount);

        int index = 1;
        StringBuilder struct = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (TreeNode node : treeNodes) {
            int lastNodeLevel = SomeMath.log2(index) + 1;
            int level = SomeMath.log2(index++) + 1;

            if (level > lastNodeLevel) {
                values.append(StringUtils.center("", 2 * (levelCount - level) - 1));
            } else {
                values.append(StringUtils.center("", 2 * (levelCount - level) + 1));
            }

            values.append("[" + node.val +"]");

            if (SomeMath.powOf2(index)) {
                Logging.printlnAlways(struct.toString());
                Logging.printlnAlways(values.toString());

                struct = new StringBuilder();
                values = new StringBuilder();
            }
        }
    }
}
