package me.tiezhu.leetcode.quiz;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing only 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Return 6.
 */
public class MaximalRectangleAs85 {
    // 这个答案不对
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        Count[][] count = new Count[matrix.length][matrix[0].length];

        count[0][0] = matrix[0][0] == '1' ? new Count(1, 1) : new Count(0, 0);
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                Count last = count[0][i - 1];
                count[0][i] = new Count(last.x + 1, 1);
            } else {
                count[0][i] = new Count(0, 0);
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == '1') {
                Count last = count[i - 1][0];
                count[i][0] = new Count(1, last.y + 1);
            } else {
                count[i][0] = new Count(0, 0);
            }
        }

        int max = 0;
        for (int i = 1;i < matrix.length;i++) {
            for (int j = 1;j < matrix[0].length;j++) {
                if (matrix[i][j] == '1') {
                    Count lastX = count[i][j - 1];
                    Count lastY = count[i - 1][j];

                    if (lastX.isZero && !lastY.isZero) {
                        count[i][j] = new Count(1, lastY.y + 1);
                    } else if (!lastX.isZero && lastY.isZero) {
                        count[i][j] = new Count(lastX.x + 1, 1);
                    } else if (!lastX.isZero && !lastY.isZero) {
                        if (!count[i - 1][j - 1].isZero) {
                            count[i][j] = new Count(Math.min(lastX.x, lastX.y) + 1, Math.min(lastX.y, lastY.y) + 1);
                        } else {

                        }
                    }

                    int c = count[i][j].x * count[i][j].y;
                    if (c > max) {
                        max = c;
                    }
                } else {
                    count[i][j] = new Count(0,0);
                }
            }
        }

        for (Count[] c : count) {
            for (Count cc : c) {
                System.out.print(cc + ",");
            }
            System.out.println();
        }

        return max;
    }

    public static void main(String[] args) {
        MaximalRectangleAs85 rectangle = new MaximalRectangleAs85();
        char[][] matrix1 = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(rectangle.maximalRectangle(matrix1));
    }

    public class Count {
        int x;
        int y;
        final boolean isZero;

        public Count(int x, int y) {
            this.x = x;
            this.y = y;
            this.isZero = (x == 0 && y == 0);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
