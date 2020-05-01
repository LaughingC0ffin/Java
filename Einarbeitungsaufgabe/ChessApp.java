import java.io.PrintStream;
import java.util.List;

public class ChessApp {

    public static void main(String[] args) {
        List<Piece> board = List.of(new Queen(Color.black, 1, 4),
                                    new Queen(Color.white, 8, 4),
                                    new Knight(Color.white, 3, 3),
                                    new Knight(Color.black, 6, 4));
        printBoard(board, System.out);
        check(board, System.out);
    }

    public static void check(List<Piece> pieces, PrintStream out) {
        for (Piece p1 : pieces) {
            out.println(p1);
            for (Piece p2 : pieces) {
                if (p1 != p2)
                    if (p1.canCapture(p2, pieces))
                        out.println("   can capture " + p2);
                    else
                        out.println("   cannot capture " + p2);
            }
        }
    }

    public static void printBoard(List<Piece> pieces, PrintStream out) {
        out.println("    1   2   3   4   5   6   7   8");
        out.println("  +---+---+---+---+---+---+---+---+");
        for (int row = 1; row <= 8; row++) {
            out.print(row + " ");
            for (int col = 1; col <= 8; col++) {
                final Piece p = pieceAt(pieces, row, col);
                final char c = p == null ? ' ' : p.charRep();
                out.print("| " + c + " ");
            }
            out.println("|");
            out.println("  +---+---+---+---+---+---+---+---+");
        }
    }

    public static Piece pieceAt(List<Piece> pieces, int row , int col)  {
        for (Piece p : pieces)
            if (p.row == row && p.col == col)
                return p;
        return null;
    }
}
