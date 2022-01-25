package me.tiezhu.test.leetcode.common;

import me.tiezhu.quiz.common.TreeNode;
import me.tiezhu.quiz.common.algorithm.TreeAlgorithm;
import me.tiezhu.quiz.utils.Logging;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeBasicTest {

    TreeAlgorithm treeAlgorithm = new TreeAlgorithm();

    @Before
    public void before() {
        Logging.enableLog();
    }

    @After
    public void after() {
        Logging.disableLog();
    }

    @Test
    public void testPrintFullBinaryTree() {
        TreeNode treeLevel1 = treeAlgorithm.buildFullBinaryTree(1);
        treeAlgorithm.printFullBinaryTree(treeLevel1);
        System.out.println(treeAlgorithm.countBinaryTreeLevel(treeLevel1));

        TreeNode treeLevel2 = treeAlgorithm.buildFullBinaryTree(2);
        treeAlgorithm.printFullBinaryTree(treeLevel2);
        System.out.println(treeAlgorithm.countBinaryTreeLevel(treeLevel2));

        TreeNode treeLevel3 = treeAlgorithm.buildFullBinaryTree(3);
        treeAlgorithm.printFullBinaryTree(treeLevel3);
        System.out.println(treeAlgorithm.countBinaryTreeLevel(treeLevel3));
    }

    @Test
    public void testFillBinaryTree() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        root.left = node1;
        node1.right = node2;

        treeAlgorithm.fillBinaryTree(root);
        treeAlgorithm.printFullBinaryTree(root);
    }
}
