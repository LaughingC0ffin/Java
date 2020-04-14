package chess;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Piece> pieces = new ArrayList<>();
    private final Piece[][] field = new Piece[8][8];

    public List<Piece> getPieces() {
        return pieces;
    }

    void add(Piece piece) {
        if (piece.board != this)
            throw new IllegalArgumentException("wrong board");
        if (pieceAt(piece.row, piece.col) != null)
            throw new IllegalArgumentException("Field " + piece.row + "/" + piece.col + " is occupied");
        pieces.add(piece);
        field[piece.row - 1][piece.col - 1] = piece;
    }

    public Piece pieceAt(int row, int col) {
        return field[row - 1][col - 1];
    }

    public void printTo(PrintStream out) {
        out.println("    1   2   3   4   5   6   7   8");
        out.println("  +---+---+---+---+---+---+---+---+");
        for (int row = 1; row <= 8; row++) {
            out.print(row + " ");
            for (int col = 1; col <= 8; col++) {
                final Piece p = pieceAt(row, col);
                final char c = p == null ? ' ' : p.charRep();
                out.print("| " + c + " ");
            }
            out.println("|");
            out.println("  +---+---+---+---+---+---+---+---+");
        }
    }

    public void check(PrintStream out) {
        for (Piece p1 : pieces) {
            out.println(p1);
            for (Piece p2 : pieces)
                if (p1 != p2)
                    if (p1.canCapture(p2))
                        out.println("   can capture " + p2);
                    else
                        out.println("   cannot capture " + p2);
        }
    }
}
