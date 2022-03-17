package me.tiezhu.game.puzzle.common;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * @author liushuai
 * Created on 2022/2/21
 */
public class Coordinate2DUtils {

    public static Coordinate2D[] copy(Coordinate2D[] input) {
        if (input == null || input.length == 0) {
            return input;
        }
        Coordinate2D[] result = new Coordinate2D[input.length];
        for (int i = 0;i < input.length; i++) {
            result[i] = input[i].clone();
        }
        return result;
    }

    /**
     * 按照偏移量平移
     * @param deltaX
     * @param deltaY
     */
    public static void move(Coordinate2D[] cos, int deltaX, int deltaY) {
        for (Coordinate2D co : cos) {
            co.move(deltaX, deltaY);
        }
    }

    /**
     * 判断两组坐标是否可以通过平移直接得到
     * @param cos1
     * @param cos2
     */
    public static boolean isSame(Coordinate2D[] cos1, Coordinate2D[] cos2) {
        if (cos1.length != cos2.length) {
            return false;
        }
        if (cos1.length == 1) {
            return true;
        }

        cos1 = sortCoordinates(cos1);
        cos2 = sortCoordinates(cos2);

        int deltaX = cos1[0].getX() - cos2[0].getX();
        int deltaY = cos1[0].getY() - cos2[0].getY();
        for (int i = 1;i < cos1.length;i++) {
            if (deltaX != cos1[i].getX() - cos2[i].getX()) {
                return false;
            }
            if (deltaY != cos1[i].getY() - cos2[i].getY()) {
                return false;
            }
        }
        return true;
    }

    public static void rotate_90(Coordinate2D[] cos) {
        for (Coordinate2D co : cos) {
            int x = co.getX();
            int y = co.getY();
            // 通过(0,0)旋转
            co.setX(y);
            co.setY(-x);
        }
    }

    public static void rotateMinus_90(Coordinate2D[] cos) {
        for (Coordinate2D co : cos) {
            int x = co.getX();
            int y = co.getY();
            // 通过(0,0)旋转
            co.setX(-y);
            co.setY(x);
        }
    }

    /**
     * 上下翻转
     * 与之相关的，左右翻转就是先上下翻再平移
     */
    public static void upsideDown(Coordinate2D[] cos) {
        int maxX = maxInCoordinate(cos, Coordinate2D::getX);
        for (Coordinate2D co : cos) {
            co.setX(maxX - co.getX());
        }
    }

    /**
     * 使坐标按照x，y从小到大排列
     * @param cos
     * @return
     */
    public static Coordinate2D[] sortCoordinates(Coordinate2D[] cos) {
        return Arrays.stream(cos)
                .sorted(Comparator.comparing(Coordinate2D::getX)
                        .thenComparing(Coordinate2D::getY))
                .toArray(Coordinate2D[]::new);
    }

    /**
     * 把第一个坐标位置挪到原点
     * @return
     */
    public static Coordinate2D[] resetCoordinates(Coordinate2D[] cos) {
        Coordinate2D[] result = copy(cos);
        move(result, -cos[0].getX(), -cos[0].getY());
        return result;
    }

    /**
     * 把所有坐标变成正数
     */
    public static void positiveCoordinates(Coordinate2D[] cos) {
        int minX = minInCoordinate(cos, Coordinate2D::getX);
        int minY = minInCoordinate(cos, Coordinate2D::getY);
        int deltaX = minX < 0 ? -minX : 0;
        int deltaY = minY < 0 ? -minY : 0;
        move(cos, deltaX, deltaY);
    }

    public static int maxInCoordinate(Coordinate2D[] cos, Function<Coordinate2D, Integer> valueGetter) {
        return Arrays.stream(cos).map(valueGetter).max(Integer::compareTo).get();
    }

    public static int minInCoordinate(Coordinate2D[] cos, Function<Coordinate2D, Integer> valueGetter) {
        return Arrays.stream(cos).map(valueGetter).min(Integer::compareTo).get();
    }

    public static void print(Coordinate2D[] cos) {
        positiveCoordinates(cos);

        int xLength = maxInCoordinate(cos, Coordinate2D::getX) + 1;
        int yLength = maxInCoordinate(cos, Coordinate2D::getY) + 1;

        int[][] matrix = new int[xLength][yLength];
        for (Coordinate2D co : cos) {
            matrix[co.getX()][co.getY()] = 1;
        }
        for (int i = 0;i < xLength; i++) {
            for (int j = 0;j < yLength; j++) {
                if (matrix[i][j] == 0) {
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println();
        }
        System.out.println();}
}
