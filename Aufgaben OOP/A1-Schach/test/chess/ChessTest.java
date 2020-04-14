package chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChessTest {
    static final String EOL = System.getProperty("line.separator", "\n");
    static final String HE = "    1   2   3   4   5   6   7   8" + EOL;
    static final String LI = "  +---+---+---+---+---+---+---+---+" + EOL;
    static final String EMP = " |   |   |   |   |   |   |   |   |" + EOL;
    static final String WK3 = " |   |   | n |   |   |   |   |   |" + EOL;
    static final String WQ3 = " |   |   | q |   |   |   |   |   |" + EOL;
    static final String CO1 = " |   |   | n |   | N | Q |   |   |" + EOL;
    static final String WQ1 = " | q |   |   |   |   |   |   |   |" + EOL;
    static final String BG5 = " |   |   |   |   | Q |   |   |   |" + EOL;
    static final String CO2 = " |   |   | n | b | q |   |   |   |" + EOL;
    static final String CO3 = " |   |   |   | b | B |   |   |   |" + EOL;
    static final String BK5 = " |   |   |   |   | N |   |   |   |" + EOL;
    static final String INIT_BOARD = board(EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP);
    static final String WK63_BOARD = board(EMP, EMP, EMP, EMP, EMP, WK3, EMP, EMP);
    static final String COM1_BOARD = board(EMP, EMP, EMP, EMP, WQ3, CO1, EMP, EMP);
    static final String COM2_BOARD = board(EMP, EMP, EMP, WQ1, EMP, WK3, EMP, BG5);
    static final String COM3_BOARD = board(EMP, EMP, CO2, CO3, BK5, EMP, EMP, EMP);

    static final List<Object> CMD1 = List.of("n", 6, 3, "N", 6, 5, "Q", 6, 6, "q", 5, 3);
    static final List<Object> CMD2 = List.of("n", 6, 3, "K", "b", 6, 3, "N", 6, 5, "N", 6, 0, "Q", 6, 6, "q", 5, 3);
    static final List<Object> CMD3 = List.of("b", 4, 4, "B", 4, 5, "q", 3, 5, "n", 3, 3, "N", 5, 5, "b", 3, 4);

    static String board(String... s) {
        if (s.length != 8)
            throw new IllegalArgumentException("Expected 8, but got " + s.length + " arguments");
        StringBuilder sb = new StringBuilder();
        sb.append(HE).append(LI);
        for (int i = 1; i <= 8; i++)
            sb.append(i).append(s[i - 1]).append(LI);
        return sb.toString();
    }

    static ByteArrayInputStream commands(List<Object> commands) {
        final String cmd = commands.stream()
                                   .map(Objects::toString)
                                   .collect(Collectors.joining(" "));
        return new ByteArrayInputStream(cmd.getBytes());
    }

    private Board board;
    private PrintStream printStream;
    private ByteArrayOutputStream printed;

    @Before
    public void setup() {
        board = new Board();
        printed = new ByteArrayOutputStream();
        printStream = new PrintStream(printed, true);
    }

    @After
    public void tearDown() {
        printStream.close();
    }

    @Test
    public void testEmpty() {
        board.printTo(printStream);
        assertEquals(INIT_BOARD, printed.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPos1() {
        new Knight(Color.white, 0, 4, board);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPos2() {
        new Queen(Color.white, 0, 4, board);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingTwice() {
        final Queen queen = new Queen(Color.white, 2, 3, board);
        board.add(queen);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOccupiedField() {
        new Knight(Color.white, 6, 4, board);
        new Queen(Color.white, 6, 4, board);
    }

    @Test
    public void testDifferentBoards() {
        final Queen queen1 = new Queen(Color.white, 6, 4, board);
        final Board other = new Board();
        final Queen queen2 = new Queen(Color.black, 6, 5, other);
        assertFalse(queen1.canCapture(queen2));
        assertFalse(queen2.canCapture(queen1));
    }

    @Test
    public void testSameBoard() {
        final Queen queen1 = new Queen(Color.white, 6, 4, board);
        final Queen queen2 = new Queen(Color.black, 6, 5, board);
        assertTrue(queen1.canCapture(queen2));
        assertTrue(queen2.canCapture(queen1));
    }

    @Test
    public void testWhiteKnight() {
        final Knight knight = new Knight(Color.white, 6, 3, board);
        checkPieces(1);
        assertEquals(knight, board.pieceAt(6, 3));
        board.printTo(printStream);
        assertEquals(WK63_BOARD, printed.toString());
    }

    @Test
    public void test3Pieces() {
        final Queen queen1 = new Queen(Color.white, 4, 1, board);
        final Queen queen2 = new Queen(Color.black, 8, 5, board);
        assertTrue(queen1.canCapture(queen2));
        assertTrue(queen2.canCapture(queen1));
        final Knight knight = new Knight(Color.white, 6, 3, board);
        assertFalse(queen1.canCapture(queen2));
        assertFalse(queen1.canCapture(knight));
        assertFalse(queen2.canCapture(queen1));
        assertTrue(queen2.canCapture(knight));
        assertFalse(knight.canCapture(queen1));
        assertFalse(knight.canCapture(queen2));
        checkPieces(3);
        assertEquals(queen1, board.pieceAt(4, 1));
        assertEquals(queen2, board.pieceAt(8, 5));
        assertEquals(knight, board.pieceAt(6, 3));
        board.printTo(printStream);
        assertEquals(COM2_BOARD, printed.toString());
    }

    @Test
    public void test4Bishops() {
        final Piece bishop1 = new Bishop(Color.white, 4, 1, board);
        final Piece bishop2 = new Bishop(Color.black, 8, 5, board);
        assertTrue(bishop1.canCapture(bishop2));
        assertTrue(bishop2.canCapture(bishop1));
        final Piece bishop3 = new Bishop(Color.white, 6, 3, board);
        final Piece bishop4 = new Bishop(Color.black, 4, 5, board);
        assertFalse(bishop1.canCapture(bishop1));
        assertFalse(bishop1.canCapture(bishop2));
        assertFalse(bishop1.canCapture(bishop3));
        assertFalse(bishop1.canCapture(bishop4));
        assertFalse(bishop2.canCapture(bishop1));
        assertFalse(bishop2.canCapture(bishop2));
        assertTrue(bishop2.canCapture(bishop3));
        assertFalse(bishop2.canCapture(bishop4));
        assertFalse(bishop3.canCapture(bishop1));
        assertTrue(bishop3.canCapture(bishop2));
        assertFalse(bishop3.canCapture(bishop3));
        assertTrue(bishop3.canCapture(bishop4));
        assertFalse(bishop4.canCapture(bishop1));
        assertFalse(bishop4.canCapture(bishop2));
        assertTrue(bishop4.canCapture(bishop3));
        assertFalse(bishop4.canCapture(bishop4));
        checkPieces(4);
        assertEquals(bishop1, board.pieceAt(4, 1));
        assertEquals(bishop2, board.pieceAt(8, 5));
        assertEquals(bishop3, board.pieceAt(6, 3));
        assertEquals(bishop4, board.pieceAt(4, 5));
    }

    @Test
    public void test4Pieces() {
        final Knight knight1 = new Knight(Color.white, 6, 3, board);
        final Knight knight2 = new Knight(Color.black, 6, 5, board);
        final Queen queen1 = new Queen(Color.black, 6, 6, board);
        final Queen queen2 = new Queen(Color.white, 5, 3, board);
        checkPieces(4);
        assertEquals(knight1, board.pieceAt(6, 3));
        assertEquals(knight2, board.pieceAt(6, 5));
        assertEquals(queen1, board.pieceAt(6, 6));
        assertEquals(queen2, board.pieceAt(5, 3));
        checkCom1Board();
    }

    @Test
    public void testApp1() {
        final ChessApp app = new ChessApp(commands(CMD1), printStream, board);
        app.playChess();
        checkCom1Board();
    }

    @Test
    public void testApp2() {
        final ChessApp app = new ChessApp(commands(CMD2), printStream, board);
        app.playChess();
        checkCom1Board();
    }

    private void checkCom1Board() {
        printed.reset();
        board.printTo(printStream);
        assertEquals(COM1_BOARD, printed.toString());
        checkPieces(4);
        final Piece knight1 = board.pieceAt(6, 3);
        final Piece knight2 = board.pieceAt(6, 5);
        final Piece queen1 = board.pieceAt(6, 6);
        final Piece queen2 = board.pieceAt(5, 3);
        assertFalse(knight1.canCapture(knight2));
        assertFalse(knight1.canCapture(queen1));
        assertFalse(knight1.canCapture(queen2));
        assertFalse(knight2.canCapture(knight1));
        assertFalse(knight2.canCapture(queen1));
        assertTrue(knight2.canCapture(queen2));
        assertFalse(queen1.canCapture(knight1));
        assertFalse(queen1.canCapture(knight2));
        assertFalse(queen1.canCapture(queen2));
        assertFalse(queen2.canCapture(knight1));
        assertFalse(queen2.canCapture(knight2));
        assertFalse(queen2.canCapture(queen1));
    }

    @Test
    public void testApp3() {
        final ChessApp app = new ChessApp(commands(CMD3), printStream, board);
        app.playChess();
        checkPieces(6);
        final Piece knight1 = board.pieceAt(3, 3);
        final Piece bishop1 = board.pieceAt(3, 4);
        final Piece queen1 = board.pieceAt(3, 5);
        final Piece bishop2 = board.pieceAt(4, 4);
        final Piece bishop3 = board.pieceAt(4, 5);
        final Piece knight2 = board.pieceAt(5, 5);

        assertFalse(knight1.canCapture(knight1));
        assertFalse(knight1.canCapture(bishop1));
        assertFalse(knight1.canCapture(queen1));
        assertFalse(knight1.canCapture(bishop2));
        assertTrue(knight1.canCapture(bishop3));
        assertFalse(knight1.canCapture(knight2));

        assertFalse(bishop1.canCapture(knight1));
        assertFalse(bishop1.canCapture(bishop1));
        assertFalse(bishop1.canCapture(queen1));
        assertFalse(bishop1.canCapture(bishop2));
        assertTrue(bishop1.canCapture(bishop3));
        assertFalse(bishop1.canCapture(knight2));

        assertFalse(queen1.canCapture(knight1));
        assertFalse(queen1.canCapture(bishop1));
        assertFalse(queen1.canCapture(queen1));
        assertFalse(queen1.canCapture(bishop2));
        assertTrue(queen1.canCapture(bishop3));
        assertFalse(queen1.canCapture(knight2));

        assertFalse(bishop2.canCapture(knight1));
        assertFalse(bishop2.canCapture(bishop1));
        assertFalse(bishop2.canCapture(queen1));
        assertFalse(bishop2.canCapture(bishop2));
        assertFalse(bishop2.canCapture(bishop3));
        assertTrue(bishop2.canCapture(knight2));

        assertFalse(bishop3.canCapture(knight1));
        assertTrue(bishop3.canCapture(bishop1));
        assertFalse(bishop3.canCapture(queen1));
        assertFalse(bishop3.canCapture(bishop2));
        assertFalse(bishop3.canCapture(bishop3));
        assertFalse(bishop3.canCapture(knight2));

        assertFalse(knight2.canCapture(knight1));
        assertTrue(knight2.canCapture(bishop1));
        assertFalse(knight2.canCapture(queen1));
        assertFalse(knight2.canCapture(bishop2));
        assertFalse(knight2.canCapture(bishop3));
        assertFalse(knight2.canCapture(knight2));

        printed.reset();
        board.printTo(printStream);
        assertEquals(COM3_BOARD, printed.toString());
    }

    @Test
    public void testHelp() {
        final ChessApp app = new ChessApp(commands(List.of("h")), printStream, board);
        app.playChess();
        assertTrue(printed.toString().contains("white bishop"));
        assertTrue(printed.toString().contains("black bishop"));
    }

    private void checkPieces(int expectedPieces) {
        int pieces = 0;
        for (int row = 1; row < 9; row++)
            for (int col = 1; col < 9; col++)
                if (board.pieceAt(row, col) != null)
                    pieces++;
        assertEquals(expectedPieces, pieces);
    }
}