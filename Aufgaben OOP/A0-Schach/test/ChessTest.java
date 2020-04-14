import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChessTest {
    // Das Zeilenende wird auf Windows-Systemen anders codiert als auf Nicht-Windows-Systemen.
    // Die folgende Zeile ermittelt die aktuelle Codierung.
    // EOL = "End of Line"
    private static final String EOL = System.getProperty("line.separator", "\n");
    private static final String HE = "    1   2   3   4   5   6   7   8" + EOL;
    private static final String LI = "  +---+---+---+---+---+---+---+---+" + EOL;
    private static final String EMP = " |   |   |   |   |   |   |   |   |" + EOL;
    private static final String WK1 = " | n |   |   |   |   |   |   |   |" + EOL;
    private static final String WQ3 = " |   |   | q |   |   |   |   |   |" + EOL;
    private static final String CO1 = " |   |   | n |   | N | Q |   |   |" + EOL;
    private static final String INIT_BOARD = board(EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP);
    private static final String WK61_BOARD = board(EMP, EMP, EMP, EMP, EMP, WK1, EMP, EMP);
    private static final String APP1_BOARD = board(EMP, EMP, EMP, EMP, WQ3, CO1, EMP, EMP);
    private static final String CHECK_TEXT1 = "white queen at (4, 2)" + EOL +
                                              "   cannot capture black queen at (8, 6)" + EOL +
                                              "   cannot capture white knight at (6, 4)" + EOL +
                                              "black queen at (8, 6)" + EOL +
                                              "   cannot capture white queen at (4, 2)" + EOL +
                                              "   can capture white knight at (6, 4)" + EOL +
                                              "white knight at (6, 4)" + EOL +
                                              "   cannot capture white queen at (4, 2)" + EOL +
                                              "   cannot capture black queen at (8, 6)" + EOL;
    private static final String CHECK_TEXT2 = "white knight at (6, 3)" + EOL +
                                              "   cannot capture black knight at (6, 5)" + EOL +
                                              "   cannot capture black queen at (6, 6)" + EOL +
                                              "   cannot capture white queen at (5, 3)" + EOL +
                                              "black knight at (6, 5)" + EOL +
                                              "   cannot capture white knight at (6, 3)" + EOL +
                                              "   cannot capture black queen at (6, 6)" + EOL +
                                              "   can capture white queen at (5, 3)" + EOL +
                                              "black queen at (6, 6)" + EOL +
                                              "   cannot capture white knight at (6, 3)" + EOL +
                                              "   cannot capture black knight at (6, 5)" + EOL +
                                              "   cannot capture white queen at (5, 3)" + EOL +
                                              "white queen at (5, 3)" + EOL +
                                              "   cannot capture white knight at (6, 3)" + EOL +
                                              "   cannot capture black knight at (6, 5)" + EOL +
                                              "   cannot capture black queen at (6, 6)" + EOL;

    private static String board(String... s) {
        if (s.length != 8)
            throw new IllegalArgumentException("Expected 8, but got " + s.length + " arguments");
        StringBuilder sb = new StringBuilder();
        sb.append(HE).append(LI);
        for (int i = 1; i <= 8; i++)
            sb.append(i).append(s[i - 1]).append(LI);
        return sb.toString();
    }

    private List<Piece> pieces;
    private PrintStream printStream;
    private ByteArrayOutputStream printed;

    @Before
    public void setup() {
        pieces = new ArrayList<>();
        printed = new ByteArrayOutputStream();
        printStream = new PrintStream(printed, true);
    }

    @After
    public void tearDown() {
        printStream.close();
    }

    @Test
    public void testEmpty() {
        ChessApp.printBoard(pieces, printStream);
        assertEquals(INIT_BOARD, printed.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPos1() {
        pieces.add(new Knight(Color.white, 0, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPos2() {
        pieces.add(new Queen(Color.white, 0, 4));
    }

    @Test
    public void testWhiteKnight() {
        final Knight knight = new Knight(Color.white, 6, 1);
        pieces.add(knight);
        checkPieces(1);
        assertEquals(knight, ChessApp.pieceAt(pieces, 6, 1));
        ChessApp.printBoard(pieces, printStream);
        assertEquals(WK61_BOARD, printed.toString());
    }

    @Test
    public void test3Pieces() {
        final Queen queen1 = new Queen(Color.white, 4, 2);
        final Queen queen2 = new Queen(Color.black, 8, 6);
        final Knight knight = new Knight(Color.white, 6, 4);
        pieces.add(queen1);
        pieces.add(queen2);
        assertTrue(queen1.canCapture(queen2, pieces));
        assertTrue(queen2.canCapture(queen1, pieces));
        pieces.add(knight);
        assertFalse(queen1.canCapture(queen2, pieces));
        assertFalse(queen1.canCapture(knight, pieces));
        assertFalse(queen2.canCapture(queen1, pieces));
        assertTrue(queen2.canCapture(knight, pieces));
        assertFalse(knight.canCapture(queen1, pieces));
        assertFalse(knight.canCapture(queen2, pieces));
        checkPieces(3);
        assertEquals(queen1, ChessApp.pieceAt(pieces, 4, 2));
        assertEquals(queen2, ChessApp.pieceAt(pieces, 8, 6));
        assertEquals(knight, ChessApp.pieceAt(pieces, 6, 4));
        printed.reset();
        ChessApp.check(pieces, printStream);
        assertEquals(CHECK_TEXT1, printed.toString());
    }

    @Test
    public void test4Pieces() {
        final Knight knight1 = new Knight(Color.white, 6, 3);
        final Knight knight2 = new Knight(Color.black, 6, 5);
        final Queen queen1 = new Queen(Color.black, 6, 6);
        final Queen queen2 = new Queen(Color.white, 5, 3);
        pieces.add(knight1);
        pieces.add(knight2);
        pieces.add(queen1);
        pieces.add(queen2);
        checkPieces(4);
        assertEquals(knight1, ChessApp.pieceAt(pieces, 6, 3));
        assertEquals(knight2, ChessApp.pieceAt(pieces, 6, 5));
        assertEquals(queen1, ChessApp.pieceAt(pieces, 6, 6));
        assertEquals(queen2, ChessApp.pieceAt(pieces, 5, 3));
        printed.reset();
        ChessApp.printBoard(pieces, printStream);
        assertEquals(APP1_BOARD, printed.toString());
        assertFalse(knight1.canCapture(knight2, pieces));
        assertFalse(knight1.canCapture(queen1, pieces));
        assertFalse(knight1.canCapture(queen2, pieces));
        assertFalse(knight2.canCapture(knight1, pieces));
        assertFalse(knight2.canCapture(queen1, pieces));
        assertTrue(knight2.canCapture(queen2, pieces));
        assertFalse(queen1.canCapture(knight1, pieces));
        assertFalse(queen1.canCapture(knight2, pieces));
        assertFalse(queen1.canCapture(queen2, pieces));
        assertFalse(queen2.canCapture(knight1, pieces));
        assertFalse(queen2.canCapture(knight2, pieces));
        assertFalse(queen2.canCapture(queen1, pieces));
        printed.reset();
        ChessApp.check(pieces, printStream);
        assertEquals(CHECK_TEXT2, printed.toString());
    }

    private void checkPieces(int expectedNumPieces) {
        int numPieces = 0;
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                if (ChessApp.pieceAt(this.pieces, row, col) != null)
                    numPieces++;
        assertEquals(expectedNumPieces, numPieces);
    }
}