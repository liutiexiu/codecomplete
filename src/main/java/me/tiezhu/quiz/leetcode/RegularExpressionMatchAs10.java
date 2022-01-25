package me.tiezhu.quiz.leetcode;

/**
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 */
public class RegularExpressionMatchAs10 {
    public boolean isMatch(String s, String p) {
        return false;
    }

    public static void main(String[] args) {
        RegularExpressionMatchAs10 r = new RegularExpressionMatchAs10();
        print(r.isMatch("aa","a"), false);
        print(r.isMatch("aa","aa"), true);
        print(r.isMatch("aaa","aa"), false);
        print(r.isMatch("aa", "a*"), true);
        print(r.isMatch("aa", ".*"), true);
        print(r.isMatch("ab", ".*"), true);
        print(r.isMatch("aab", "c*a*b"), true);
    }

    static void print(boolean result, boolean expected) {
        System.out.println(String.format("result:%s,expected:%s", result, expected));
    }
}
