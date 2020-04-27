import java.util.List;

import static java.lang.Math.abs;

public class Knight extends Piece {

    public Knight(Color color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean canCapture(Piece other, List<Piece> pieces) {
        if (color == other.color) return false;
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
