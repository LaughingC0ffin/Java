package solitaire;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SolitaireBoardTest {
    private static final String HE = "  012345678\n";
    private static final String LI = " +---------+\n";
    private static final String EM = "|         |\n";
    private static final String MI = "|    #    |\n";
    private static final String B3 = "|   ###   |\n";
    private static final String H3 = "|   # #   |\n";
    private static final String B9 = "|#########|\n";
    private static final String H9 = "|#### ####|\n";

    static final String PARTIAL_BOARD = board(EM, EM, EM, EM, EM, MI, MI, EM, EM);
    static final String SOLUTION_BOARD = board(EM, EM, EM, EM, MI, EM, EM, EM, EM);
    static final String INITIAL_BOARD = board(B3, B3, B3, B9, H9, B9, B3, B3, B3);
    static final String STEP1_BOARD = board(B3, B3, H3, H9, B9, B9, B3, B3, B3);
    static final String STEP2_BOARD = board(B3, B3, H3, B9, H9, H9, B3, B3, B3);

    static final List<Integer> SOLUTION_MOVES = List.of(
            2, 4, 4, 4, 0, 4, 2, 4, 3, 2, 3, 4, 1, 3, 3, 3, 2, 5, 2, 3, 0, 5, 2, 5, 3, 0, 3, 2, 3, 3, 1, 3, 0, 3, 2,
            3, 3, 5, 1, 5, 3, 7, 3, 5, 3, 4, 3, 6, 5, 0, 3, 0, 5, 1, 3, 1, 3, 1, 3, 3, 4, 3, 4, 1, 5, 3, 5, 1, 5, 1,
            3, 1, 3, 0, 3, 2, 3, 2, 3, 4, 4, 4, 2, 4, 2, 3, 2, 5, 1, 5, 3, 5, 3, 5, 3, 7, 3, 8, 3, 6, 4, 6, 4, 4, 4,
            8, 4, 6, 7, 3, 5, 3, 6, 5, 6, 3, 4, 4, 6, 4, 5, 3, 7, 3, 8, 3, 6, 3, 8, 5, 6, 5, 5, 5, 7, 5, 5, 7, 5, 5,
            3, 6, 5, 6, 5, 5, 5, 7, 5, 8, 5, 6, 6, 3, 6, 5, 7, 5, 5, 5, 5, 6, 5, 4, 8, 4, 6, 4, 6, 4, 4, 4);

    private static String board(String... s) {
        if (s.length != 9)
            throw new IllegalArgumentException("Expected 9, but got " + s.length + " arguments");
        StringBuilder sb = new StringBuilder();
        sb.append(HE).append(LI);
        for (int i = 0; i < 9; i++)
            sb.append(i).append(s[i]);
        sb.append(LI);
        return sb.toString();
    }

    static void checkOccupied(SolitaireBoard board, int expectedOccupied) {
        int sumInvalid = 0;
        int sumOccupied = 0;
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                switch (board.getField(row, col)) {
                    case invalid:
                        sumInvalid++;
                        break;
                    case occupied:
                        sumOccupied++;
                        break;
                    case empty:
                        break;
                }
        assertEquals(36, sumInvalid);
        assertEquals(expectedOccupied, sumOccupied);
    }

    private SolitaireBoard board;

    @Before
    public void setup() {
        board = new SolitaireBoard();
    }

    @Test
    public void testInitialBoard() {
        checkOccupied(board, 44);
        assertEquals(FieldState.empty, board.getField(4, 4));
    }

    @Test
    public void testPrint() {
        assertEquals(INITIAL_BOARD, board.toString());
    }

    @Test
    public void testNotFinal() {
        assertFalse(board.isFinal());
    }

    @Test
    public void testStartFieldMiddle() {
        assertFalse(board.isValidStart(4, 4));
    }

    @Test
    public void testStartField1() {
        assertTrue(board.isValidStart(2, 4));
    }

    @Test
    public void testStartField2() {
        board.move(2, 4, 4, 4);
        assertTrue(board.isValidStart(5, 4));
    }

    @Test
    public void testStartFieldInvalid1() {
        assertFalse(board.isValidStart(1, 6));
    }

    @Test
    public void testStartFieldNonMovable2() {
        assertFalse(board.isValidStart(5, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMove1() {
        board.move(2, 4, 0, 4);
    }

    @Test
    public void testIsInvalidMove1() {
        assertFalse(board.isValidMove(2, 4, 0, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMove2() {
        board.move(2, 4, 5, 4);
    }

    @Test
    public void testIsInvalidMove2() {
        assertFalse(board.isValidMove(2, 4, 5, 4));
    }

    @Test
    public void testValidMove1() {
        board.move(2, 4, 4, 4);
        checkOccupied(board, 43);
        assertEquals(FieldState.empty, board.getField(2, 4));
        assertEquals(FieldState.empty, board.getField(3, 4));
        assertEquals(STEP1_BOARD, board.toString());
        assertFalse(board.isFinal());
    }

    @Test
    public void testIsValidMove1() {
        assertTrue(board.isValidMove(2, 4, 4, 4));
    }

    @Test
    public void testValidMove2() {
        board.move(2, 4, 4, 4);
        board.move(5, 4, 3, 4);
        checkOccupied(board, 42);
        assertEquals(FieldState.empty, board.getField(2, 4));
        assertEquals(FieldState.empty, board.getField(5, 4));
        assertEquals(FieldState.empty, board.getField(4, 4));
        assertEquals(STEP2_BOARD, board.toString());
        assertFalse(board.isFinal());
    }

    @Test
    public void testIsValidMove2() {
        board.move(2, 4, 4, 4);
        assertTrue(board.isValidMove(5, 4, 3, 4));
    }

    @Test
    public void testSolution() {
        for (Iterator<Integer> it = SOLUTION_MOVES.iterator(); it.hasNext(); ) {
            final int row1 = it.next();
            final int col1 = it.next();
            final int row2 = it.next();
            final int col2 = it.next();
            board.move(row1, col1, row2, col2);
        }
        checkOccupied(board, 1);
        assertEquals(FieldState.occupied, board.getField(4, 4));
        assertEquals(SOLUTION_BOARD, board.toString());
        assertTrue(board.isFinal());
    }
}
