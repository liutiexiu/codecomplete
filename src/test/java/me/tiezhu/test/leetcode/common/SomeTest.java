package me.tiezhu.test.leetcode.common;

import me.tiezhu.quiz.utils.SomeMath;
import org.junit.Test;

public class SomeTest {

    @Test
    public void testMath() throws Exception {
        System.out.println(Math.log(10)/ Math.log(2));
        System.out.println(Math.log(9)/ Math.log(3));
        System.out.println((int)(Math.log(10)/ Math.log(2)));

        System.out.println(SomeMath.log2(1));
        System.out.println(SomeMath.log2(2));
        System.out.println(SomeMath.log2(3));
        System.out.println(SomeMath.log2(4));
        System.out.println(SomeMath.log2(5));
    }
}
