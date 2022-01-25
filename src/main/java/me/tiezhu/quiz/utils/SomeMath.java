package me.tiezhu.quiz.utils;

public abstract class SomeMath {

    public static boolean powOf2(int num) {
        return num == 0 || (num > 0 && (num & (num - 1)) == 0);
    }

    public static int log2(int num) {
        return log(2, num);
    }

    public static int log(int base, int num) {
        return (int) (Math.log(num) / Math.log(base));
    }
}
