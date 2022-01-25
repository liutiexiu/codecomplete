package me.tiezhu.quiz.leetcode;

/**
 * @author liushuai
 * Created on 2021/5/18
 */
public class ReverseLinkedListAs0 {

    public static void main(String[] args) {
        printList(buildLinkedList(new int[]{}));
        printList(reverseLinkedList(buildLinkedList(new int[]{1})));
        printList(reverseLinkedList(buildLinkedList(new int[]{1,2})));
        printList(reverseLinkedList(buildLinkedList(new int[]{1,2,3,4,5})));
    }

    private static ListNode reverseLinkedList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            head = cur.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }

        return pre;
    }

    private static ListNode buildLinkedList(int[] input) {
        if (input == null || input.length == 0) {
            return null;
        }
        ListNode root = new ListNode(input[0]);
        ListNode cur = root;
        for (int i = 1;i < input.length;i++) {
            ListNode next = new ListNode(input[i]);
            cur.next = next;
            cur = next;
        }

        return root;
    }

    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        } else {
            System.out.print("[");
            System.out.print(head.val);
        }

        ListNode cur = head.next;

        while(cur != null) {
            System.out.print("," + cur.val);
            cur = cur.next;
        }

        System.out.println("]");
    }

    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int n) {
            this.val = n;
        }
    }
}
