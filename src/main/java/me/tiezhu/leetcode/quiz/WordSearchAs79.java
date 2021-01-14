package me.tiezhu.leetcode.quiz;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * For example,
 * <p>
 * Given board =
 * <p>
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearchAs79 {
    public boolean exist(char[][] board, String word) {
        // check input
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        if (word == null || word.length() == 0) {
            return false;
        }

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (helper(board, x, y, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean helper(char[][] board, int x, int y, String word, int index) {
        // System.out.println(String.format("x=%d,y=%d,i=%d, set=%s", x, y, index, set));
        if (word.length() == index) {
            return true;
        } else if (x < 0 || y < 0 || x == board.length || y == board[0].length) {
            return false;
        }

        if (board[x][y] != word.charAt(index)) {
            return false;
        }

        // 别人的重大优化，这里只需要把用过的值改掉，就不能重入了
        board[x][y] ^= 256;

        boolean result =
                helper(board, x + 1, y, word, index + 1) ||
                helper(board, x - 1, y, word, index + 1) ||
                helper(board, x, y + 1, word, index + 1) ||
                helper(board, x, y - 1, word, index + 1);

        board[x][y] ^= 256;

        return result;
    }

    public static void main(String[] args) {
        char[][] board1 = {{'a', 'b', 'c'}, {'f', 'e', 'd'}, {'a', 'g', 'e'}};
        String word1 = "abcdeg";
        System.out.println(new WordSearchAs79().exist(board1, word1));
    }
}
