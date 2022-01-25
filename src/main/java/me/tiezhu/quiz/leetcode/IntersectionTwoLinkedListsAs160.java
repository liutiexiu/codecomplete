package me.tiezhu.quiz.leetcode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 * A:      a1 → a2
 *               ↘
 *                c1 → c2 → c3
 *               ↗
 * B: b1 → b2 → b3
 *
 * begin to intersect at node c1.
 *
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionTwoLinkedListsAs160 {

    // 想出的一般解法，各遍历一次数处节点数，切掉长的那个的开头，然后并行查
    // 下边这个是牛逼解法，并行跑，跑到最后时跳到对方的头接着跑，这样步长始终一样，第一个相等的点要么是交点，要么是NULL
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
