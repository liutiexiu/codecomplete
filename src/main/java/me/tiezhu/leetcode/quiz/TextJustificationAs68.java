package me.tiezhu.leetcode.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given an array of words and a length L, format the text such that each lin has exactly
 * L characters and is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach;that is, pack as many words as you can
 * in each line. Pad extra space ' ' when necessary so that each line has exactly L characters.
 * <p>
 * Extra spaces between words should be distributed as evenly s possible. It the number of
 * spaces on a line do not divide evenly between words, the empty slots on the left will be
 * assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left justified and no extra space is inserted
 * between words.
 * <p>
 * For example,
 * words:["This", "is", "an", "example", "of", "text", "justification."]
 * L:16
 * <p>
 * value:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 */
public class TextJustificationAs68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        int index = 0;

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(maxWidth));

        for (int i = 0; i < words.length; i++) {
            Line line = lines.get(index);
            if (line.paddingCount < line.wordList.size() + words[i].length()) {
                lines.add(new Line(maxWidth));
                line = lines.get(++index);
            }

            line.wordList.add(words[i]);
            line.paddingCount -= words[i].length();
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < lines.size() - 1; i++) {
            Line line = lines.get(i);
            int paddingRemain = line.paddingCount;
            Stack<String> stack = new Stack<>();
            for (int j = line.wordList.size() - 1; j >= 0; j--) {
                stack.push(line.wordList.get(j));
                int paddingCount = j == 0 ? 0 : paddingRemain / j;
                for (int k = 0; k < paddingCount; k++) {
                    stack.push(" ");
                }
                paddingRemain -= paddingCount;
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            result.add(sb.toString());
        }

        StringBuilder sb = new StringBuilder();
        Line lastLine = lines.get(lines.size() - 1);
        for (String w : lastLine.wordList) {
            sb.append(w).append(" ");
        }
        int remain = maxWidth - sb.length();
        while(remain-- > 0) {
            sb.append(" ");
        }
        result.add(sb.toString());

        return result;
    }

    public static class Line {
        public List<String> wordList = new ArrayList<>();
        public int paddingCount;

        public Line(int length) {
            paddingCount = length;
        }

        @Override
        public String toString() {
            return wordList.toString();
        }
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        print(new TextJustificationAs68().fullJustify(words, 16));
    }

    public static void print(List<String> list) {
        if (list == null) {
            return;
        }

        System.out.println("[");
        for (String line : list) {
            System.out.println("  \"" + line + "\"");
        }
        System.out.println("]");
    }
}
