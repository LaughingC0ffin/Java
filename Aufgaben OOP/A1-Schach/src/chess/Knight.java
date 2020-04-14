package chess;
//springer
import static java.lang.Math.abs;

public class Knight extends Piece {

    public Knight(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public boolean canCapture(Piece other) {
        if (board != other.board || color == other.color) return false;
        final int dr = abs(row - other.row);
        final int dc = abs(col - other.col);
        return dr == 2 && dc == 1 || dr == 1 && dc == 2;
    }

    @Override
    public char charRep() {
        return color == Color.white ? 'n' : 'N';
    }

    @Override
    public String toString() {
        return color + " knight at (" + row + ", " + col + ")";
    }
}
