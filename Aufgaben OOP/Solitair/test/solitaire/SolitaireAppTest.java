package solitaire;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static solitaire.SolitaireBoardTest.INITIAL_BOARD;
import static solitaire.SolitaireBoardTest.PARTIAL_BOARD;
import static solitaire.SolitaireBoardTest.SOLUTION_BOARD;
import static solitaire.SolitaireBoardTest.SOLUTION_MOVES;
import static solitaire.SolitaireBoardTest.STEP1_BOARD;
import static solitaire.SolitaireBoardTest.STEP2_BOARD;
import static solitaire.SolitaireBoardTest.checkOccupied;

public class SolitaireAppTest {

    private SolitaireBoard board;
    private PrintStream printStream;
    private ByteArrayOutputStream printed;

    @Before
    public void setup() {
        board = new SolitaireBoard();
        printed = new ByteArrayOutputStream();
        printStream = new PrintStream(printed, true);
    }

    @After
    public void tearDown() {
        printStream.close();
    }

    @Test(timeout = 100)
    public void testAbort1() {
        new SolitaireApp(commands("a"), printStream, board).playSolitaire();
    }

    @Test(timeout = 100)
    public void testAbort2() {
        new SolitaireApp(commands("1"), printStream, board).playSolitaire();
        assertEquals(INITIAL_BOARD, board.toString());
    }

    @Test(timeout = 100)
    public void testAbort3() {
        new SolitaireApp(commands("2 4 4 4 a"), printStream, board).playSolitaire();
        final int count1 = count(printed.toString(), '\n');
        final SolitaireBoard board2 = new SolitaireBoard();
        printed.reset();
        new SolitaireApp(commands("2 4 4 4 a 0 4 2 4 3 2 3 4"), printStream, board2).playSolitaire();
        final int count2 = count(printed.toString(), '\n');
        Assert.assertEquals(count1, count1);
    }

    @Test(timeout = 100)
    public void testInvalidMove() {
        new SolitaireApp(commands("3 4 4 4"), printStream, board).playSolitaire();
        printed.reset();
        assertFalse(board.isFinal());
    }

    @Test(timeout = 100)
    public void testValidMove() {
        new SolitaireApp(commands("2 4 4 4"), printStream, board).playSolitaire();
        assertEquals(STEP1_BOARD, board.toString());
        assertFalse(board.isFinal());
    }

    @Test(timeout = 100)
    public void testValid2Moves() {
        new SolitaireApp(commands("2 4 4 4 5 4 3 4"), printStream, board).playSolitaire();
        assertEquals(STEP2_BOARD, board.toString());
        assertFalse(board.isFinal());
    }

    @Test(timeout = 500)
    public void testSolution() {
        new SolitaireApp(commands(SOLUTION_MOVES), printStream, board).playSolitaire();
        assertEquals(SOLUTION_BOARD, board.toString());
        checkOccupied(board, 1);
        assertEquals(FieldState.occupied, board.getField(4, 4));
        assertTrue(board.isFinal());
    }

    @Test(timeout = 500)
    public void testPartial() {
        new SolitaireApp(commands(SOLUTION_MOVES.subList(0, 4 * 42)), printStream, board).playSolitaire();
        assertEquals(PARTIAL_BOARD, board.toString());
        checkOccupied(board, 2);
        assertEquals(FieldState.occupied, board.getField(5, 4));
        assertEquals(FieldState.occupied, board.getField(6, 4));
        assertFalse(board.isFinal());
    }

    @Test(timeout = 500)
    public void testTerminated() {
        new SolitaireApp(commands(SOLUTION_MOVES), printStream, board).playSolitaire();
        checkOccupied(board, 1);
        assertEquals(FieldState.occupied, board.getField(4, 4));
        final int count1 = count(printed.toString(), '\n');
        final SolitaireBoard board2 = new SolitaireBoard();
        printed.reset();
        final List<Integer> cmds = new ArrayList<>(SOLUTION_MOVES);
        cmds.addAll(List.of(5, 4, 7, 4));
        new SolitaireApp(commands(cmds), printStream, board2).playSolitaire();
        final int count2 = count(printed.toString(), '\n');
        Assert.assertEquals(count1, count2);
    }

    static ByteArrayInputStream commands(List<Integer> commands) {
        final String cmd = commands.stream()
                                   .map(Objects::toString)
                                   .collect(Collectors.joining(" "));
        return new ByteArrayInputStream(cmd.getBytes());
    }

    static ByteArrayInputStream commands(String commands) {
        return new ByteArrayInputStream(commands.getBytes());
    }

    static int count(String str, char el) {
        int num = 0;
        for (char c : str.toCharArray())
            if (c == el) num++;
        return num;
    }
}
