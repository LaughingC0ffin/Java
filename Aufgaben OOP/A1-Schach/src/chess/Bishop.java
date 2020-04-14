package chess;
import static java.lang.Integer.signum;
//import static java.lang.Math.abs;

public class Bishop extends Piece {
    public Bishop(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }
    @Override
    public boolean canCapture(Piece other) {
        if (board != other.board || color == other.color) return false;
        //signum von 0 ist halt 0
        final int dr = signum(other.row - row);
        final int dc = signum(other.col - col);
        if(dr == 0 || dc  == 0 ) return false;
        //System.out.println(dc + "  " + dr);
        int r = row + dr;
        int c = col + dc;
        while (r != other.row || c != other.col) {
            if (board.pieceAt(r, c) != null) return false;
            r += dr;
            c += dc;
        }
        return true;
    }
    @Override
    public char charRep() {
        return color == Color.white ? 'b' : 'B';
    }
    @Override
    public String toString() {
        return color + " bishop at (" + row + ", " + col + ")";
    }
}