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

    public static void check(List<Piece> Pieces, PrintStream out) {
        for (Piece P1 : Pieces) {
            out.println(P1);
            for (Piece P2 : Pieces)
                if (P1 != P2)
                    if (P1.canCapture(P2, Pieces))
                        out.println("   can capture " + P2);
                    else
                        out.println("   cannot capture " + P2);
        }
    }

    public static void printBoard(List<Piece> Pieces, PrintStream out) {
        out.println("    1   2   3   4   5   6   7   8");
        out.println("  +---+---+---+---+---+---+---+---+");
        for (int Row = 1; Row <= 8; Row++) {
            out.print(Row + " ");
            for (int Col = 1; Col <= 8; Col++) {
                final Piece p = pieceAt(Pieces, Row, Col);
                final char c = p == null ? ' ' : p.charRep();
                out.print("| " + c + " ");
            }
            out.println("|");
            out.println("  +---+---+---+---+---+---+---+---+");
        }
    }

    public static Piece pieceAt(List<Piece> Pieces,int row , int col)  {
        for (Piece p : Pieces)
            if (p.row == row && p.col == col)
                return p;
        return null;
    }
}
