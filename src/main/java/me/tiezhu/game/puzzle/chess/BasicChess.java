package me.tiezhu.game.puzzle.chess;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import me.tiezhu.game.puzzle.common.ICoordinate;
import me.tiezhu.game.puzzle.common.exception.OutOfChessException;

/**
 * 基本的棋盘
 * 每个位置有set/unset两种状态
 * 填充puzzle的逻辑只需要跟这个interface里的接口做交互
 *
 * @author liushuai
 * Created on 2022/2/10
 */
public interface BasicChess<C extends ICoordinate> {

    /**
     * 本棋盘内所有格子
     * @return
     */
    List<C> getAllChessBlocks();

    /**
     * 标记一个坐标已经填上
     * @param co
     *
     * @throws OutOfChessException
     */
    default void setBlock(C co) {
        setBlock(Collections.singleton(co));
    }

    /**
     * 批量标记
     * @param cos
     *
     * @throws OutOfChessException
     */
    void setBlock(Collection<C> cos);

    /**
     * 去掉一个坐标的标记
     * @param co
     *
     * @throws OutOfChessException
     */
    default void unSetBlock(C co) {
        unSetBlock(Collections.singleton(co));
    }

    /**
     * 批量解除标记
     * @param cos
     *
     * @throws OutOfChessException
     */
    void unSetBlock(Collection<C> cos);

    default boolean isSet(C co) {
        return getBlockStatus(co) == BlockStatus.SET;
    }

    default boolean isInChess(C co) {
        return getBlockStatus(co) != BlockStatus.OUT;
    }

    /**
     * 获取坐标的具体状态
     * @param co
     * @return
     */
    BlockStatus getBlockStatus(C co);

    enum BlockStatus {
        OUT,
        UNSET,
        SET
    }
}
