package me.tiezhu.game.puzzle.fragment;

import static me.tiezhu.game.puzzle.common.Coordinate2DUtils.copy;
import static me.tiezhu.game.puzzle.common.Coordinate2DUtils.move;
import static me.tiezhu.game.puzzle.common.Coordinate2DUtils.print;
import static me.tiezhu.game.puzzle.common.Coordinate2DUtils.rotateMinus_90;
import static me.tiezhu.game.puzzle.common.Coordinate2DUtils.rotate_90;
import static me.tiezhu.game.puzzle.common.Coordinate2DUtils.sortCoordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.tiezhu.game.puzzle.common.Coordinate2D;
import me.tiezhu.game.puzzle.common.Coordinate2DUtils;

/**
 * 二维可旋转的碎片
 * 0,0 0,1 0,2 0,3
 * 1,0 1,1 1,2 1,3
 * 2,0 2,1 2,2 2,3
 * @author liushuai
 * Created on 2022/2/11
 */
public class FragmentRotatable2D {

    private final Coordinate2D[] initValues;

    private Coordinate2D[] currentValues;
    private int rotateAngle;
    private boolean upsideDown;

    public FragmentRotatable2D(Coordinate2D[] initValues) {
        this.initValues = initValues;
        // TODO 检查是否连续
        // 正则化
        this.currentValues = sortCoordinates(initValues);
        // 确保有坐标经过原点
        if (!Arrays.stream(currentValues)
                .filter(co -> co.getX() == 0 && co.getY() == 0)
                .findAny().isPresent()) {
            move(currentValues, -currentValues[0].getX(), -currentValues[1].getY());
        }
    }

    /**
     * 上下翻转
     * 与之相关的，左右翻转就是先上下翻再平移
     */
    public void upsideDown() {
        upsideDown = !upsideDown;
        Coordinate2DUtils.upsideDown(currentValues);
    }

    /**
     * 顺时针旋转angel度，取值为0|90|180|270
     * @param angel
     */
    public void rotate(int angel) {
        switch(angel) {
            case 0:
            case 360:
                break;
            case 90:
                rotate90();
                break;
            case 180:
                rotate90();
                rotate90();
                break;
            case 270:
                rotateMinus90();
                break;
            default:
                throw new IllegalArgumentException("bad rotate angel: " + angel);
        }
        // regular
        rotateAngle %= 360;
    }

    /**
     * 列出所有角度，包括翻转
     * 只列出不重样的
     * @return
     */
    public List<FragmentRotatable2D> getAllAngles() {
        List<Coordinate2D[]> list = new ArrayList<>();
        Coordinate2D[] c = copy(currentValues);
        addIfNoSame(list, copy(c));
        rotate_90(c);
        addIfNoSame(list, copy(c));
        rotate_90(c);
        addIfNoSame(list, copy(c));
        rotate_90(c);
        addIfNoSame(list, copy(c));
        Coordinate2DUtils.upsideDown(c);
        addIfNoSame(list, copy(c));
        rotate_90(c);
        addIfNoSame(list, copy(c));
        rotate_90(c);
        addIfNoSame(list, copy(c));
        rotate_90(c);
        addIfNoSame(list, copy(c));

        return list.stream().map(FragmentRotatable2D::new).collect(Collectors.toList());
    }

    private void addIfNoSame(List<Coordinate2D[]> list, Coordinate2D[] cos) {
        for (Coordinate2D[] c : list) {
            if (Coordinate2DUtils.isSame(c, cos)) {
                return;
            }
        }
        list.add(cos);
    }

    public Coordinate2D[] getCurrentValues() {
        // TODO immutable
        return currentValues;
    }

    public int getRotateAngle() {
        return rotateAngle;
    }

    public boolean isUpsideDown() {
        return upsideDown;
    }

    private void rotate90() {
        rotate_90(currentValues);
        rotateAngle += 90;
        rotateAngle %= 360;
    }

    private void rotateMinus90() {
        rotateMinus_90(currentValues);
        rotateAngle += 270;
        rotateAngle %= 360;
    }


    public void printCurrentSimple() {
        System.out.println("===");
        print(currentValues);
        System.out.println("===");
    }
}
