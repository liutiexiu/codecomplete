package me.tiezhu.game.puzzle.chess;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.sun.istack.internal.NotNull;

import me.tiezhu.game.puzzle.common.Coordinate2D;
import me.tiezhu.game.puzzle.common.exception.OutOfChessException;

/**
 * 一个基础的二维棋盘实现，长方形结构
 * 里边的block有三种状态，分别在BasicChess#BlockType中定义
 * @author liushuai
 * Created on 2022/2/10
 */
public abstract class AbstractChess2D<C extends Coordinate2D> implements BasicChess<C> {

    protected Chess2DBlock[][] chessSquare;

    protected abstract C toC(Chess2DBlock block);

    @Override
    public List<C> getAllChessBlocks() {
        return Arrays.stream(chessSquare)
                .flatMap(Arrays::stream)
                .filter(bl -> bl.blockStatus != BlockStatus.OUT)
                .map(this::toC)
                .collect(Collectors.toList());
    }

    @Override
    public void setBlock(Collection<C> cos) {
        cos.forEach(co -> setBlockType(co, bl -> {}, bl -> bl.blockStatus = BlockStatus.SET));
    }

    @Override
    public void unSetBlock(Collection<C> cos) {
        cos.forEach(co -> setBlockType(co, bl -> bl.blockStatus = BlockStatus.UNSET, bl -> {}));
    }

    private void setBlockType(C co, Consumer<Chess2DBlock> onTypeSet, Consumer<Chess2DBlock> onTypeUnSet) {
        Chess2DBlock block = chessSquare[co.getX()][co.getY()];
        switch(block.blockStatus) {
            case SET:
                onTypeSet.accept(block);
                break;
            case UNSET:
                onTypeUnSet.accept(block);
                break;
            case OUT:
                throw new OutOfChessException(co + " not in chess");
            default:
                throw new IllegalStateException(co + " bad state: " + block.blockStatus);
        }
    }

    @Override
    public BlockStatus getBlockStatus(C co) {
        return Optional
                .ofNullable(chessSquare[co.getX()][co.getY()])
                .map(Chess2DBlock::getBlockStatus)
                .orElse(null);
    }

    public static final class Chess2DBlock extends Coordinate2D {
        private BlockStatus blockStatus;

        public Chess2DBlock(int x, int y, @NotNull BlockStatus blockStatus) {
            super(x, y);
            this.blockStatus = blockStatus;
        }

        public BlockStatus getBlockStatus() {
            return blockStatus;
        }

        public void setBlockStatus(BlockStatus blockStatus) {
            this.blockStatus = blockStatus;
        }
    }
}
