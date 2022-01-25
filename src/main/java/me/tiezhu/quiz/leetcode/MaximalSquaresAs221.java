package me.tiezhu.quiz.leetcode;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1  0  0
 * 1 0 1* 1* 1
 * 1 1 1* 1* 1
 * 1 0 0  1  0
 *
 * Return 4.
 */
public class MaximalSquaresAs221 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int maxWidth = 0;
        int row = matrix.length;
        int column = matrix[0].length;

        int[][] cache = new int[row + 1][column + 1];

        // 这里注掉的是原来的普通版动态规划，这个新的是抄来的
        // 通过多加一圈，可以省去初始化边的代码。。
        // 下边的边界做点修改就行了

//        int[][] cache = new int[row][column];
//        for (int i = 0;i < row;i++) {
//            cache[i][0] = matrix[i][0] == '1' ? 1 : 0;
//        }
//        for (int j = 0;j < column;j++) {
//            cache[0][j] = matrix[0][j] == '1' ? 1 : 0;
//        }

        for (int i = 1;i <= row;i++) {
            for (int j = 1;j <= column;j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    cache[i][j] = min(cache[i - 1][j - 1], cache[i][j - 1], cache[i - 1][j]) + 1;
                    maxWidth = Math.max(cache[i][j], maxWidth);
                }
            }
        }

        return maxWidth * maxWidth;
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        char[][] input1 = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'},
        };
        System.out.println(new MaximalSquaresAs221().maximalSquare(input1));
    }
}
