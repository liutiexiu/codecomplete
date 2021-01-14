package me.tiezhu.leetcode.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 *
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairsAs336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        // 最直观的方法，600ms+，只有0.4%的人用了这个方法。。。O(n*n*m)?
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0;i < words.length;i++) {
            for (int j = i + 1;j < words.length;j++) {
                if (isPalindrome(words[i], words[j])) {
                    list.add(pair(i, j));
                }

                if (isPalindrome(words[j], words[i])) {
                    list.add(pair(j, i));
                }
            }
        }

        // 100ms方法
        // 如果一个串的左边或者右边已经自成回文，那么只需要找到剩下部分的回文是否存在即可O(n*m*m)?

        return list;
    }

    boolean isPalindrome(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0) {
            return false;
        }

        int length1 = s1.length();
        int length2 = s2.length();

        int totalLength = length1 + length2;
        for (int i = 0;i <= totalLength / 2;i++) {
            if (findChar(s1, s2, length1, length2, i) != findChar(s1, s2, length1, length2, totalLength - i - 1)) {
                return false;
            }
        }

        return true;
    }

    static char findChar(String s1, String s2, int length1, int length2, int index) {
        if (index >= length1) {
            return s2.charAt(index - length1);
        } else {
            return s1.charAt(index);
        }
    }

    List<Integer> pair(int i, int j) {
        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        return list;
    }

    public static void main(String[] args) {
        String[] words1 = {"bat", "tab", "cat"};
        System.out.println(new PalindromePairsAs336().palindromePairs(words1));

        String[] words2 = {"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println(new PalindromePairsAs336().palindromePairs(words2));

        String[] words3 = {"a", ""};
        System.out.println(new PalindromePairsAs336().palindromePairs(words3));
    }
}
