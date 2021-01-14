package me.tiezhu.leetcode.quiz;

public class EditDistanceAs72 {
    public int minDistance(String word1, String word2) {
        System.out.println(String.format("[%s] to [%s]", word1, word2));

        if (word1 == null) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null) {
            return word1 == null ? 0 : word1.length();
        }

        int m = word1.length();
        int n = word2.length();
        int[][] cache = new int[m + 1][n + 1];

        for (int i = 0;i <= m;i++) {
            cache[i][0] = i;
        }
        for (int j = 0;j <= n;j++) {
            cache[0][j] = j;
        }

        for (int i = 1;i <= word1.length();i++) {
            for (int j = 1;j <= word2.length();j++) {
//                int add = cache[i][j - 1] + 1;
//                int delete = cache[i - 1][j] + 1;
//                int replace = cache[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
//                cache[i][j] = Math.min(replace, Math.min(add, delete));

                // 以上是标准DP的写法，下边这么写可以少算一些数
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1];
                } else {
                    cache[i][j] = 1 + Math.min(Math.min(cache[i][j - 1], cache[i - 1][j]), cache[i - 1][j - 1]);
                }
            }
        }

        return cache[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new EditDistanceAs72().minDistance("abc", "abcdef"));
        System.out.println(new EditDistanceAs72().minDistance("def", "abcdef"));
        System.out.println(new EditDistanceAs72().minDistance("abc", "defg"));
    }
}
