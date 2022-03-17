package me.tiezhu.game.puzzle;

import java.util.Arrays;
import java.util.List;

import me.tiezhu.game.puzzle.chess.CalenderPuzzleChess;
import me.tiezhu.game.puzzle.common.Coordinate2D;
import me.tiezhu.game.puzzle.fragment.FragmentRotatable2D;

/**
 * @author liushuai
 * Created on 2022/2/10
 */
public class PuzzlePerDayMain {

    public static void main(String[] args) {
        Coordinate2D[] cos = new Coordinate2D[] {
                new Coordinate2D(0, 0), new Coordinate2D(0, 1),
                new Coordinate2D(0, 2), new Coordinate2D(1, 2)
        };
        FragmentRotatable2D fragment = new FragmentRotatable2D(cos);
        fragment.getAllAngles().stream().forEach(FragmentRotatable2D::printCurrentSimple);
    }

    private static void testChess() {
        List<String> configs = Arrays.asList(new String[]{"0 0 Jan", "1 2 31", "2 4 Dec"});
        CalenderPuzzleChess chess = new CalenderPuzzleChess(configs);
        chess.initChess();
        printSimple(chess);
    }

    private static void printSimple(CalenderPuzzleChess chess) {
        chess.getAllChessBlocks().stream().forEach(
                b -> System.out.printf("(%s,%s) %s %s\n", b.getX(), b.getY(), b.getDateValue(), b.getBlockMaskType()));
    }
}
