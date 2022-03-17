package me.tiezhu.game.puzzle.common.exception;

/**
 * @author liushuai
 * Created on 2022/2/10
 */
public class OutOfChessException extends RuntimeException {
    public OutOfChessException() {
    }

    public OutOfChessException(String message) {
        super(message);
    }
}
