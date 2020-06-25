package ubung7Gruppe.chess;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class Queen extends Piece {

  public Queen(Color color, int row, int col, Board board) {
    super(color, row, col, board);
  }

  @Override
  public boolean canCapture(Piece other) {
    if (board != other.board || color == other.color) return false;
    if (other.row != row &&
        other.col != col &&
        abs(other.row - row) != abs(other.col - col))
      return false;
    final int dr = signum(other.row - row);
    final int dc = signum(other.col - col);
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
    return color == Color.white ? 'q' : 'Q';
  }

  @Override
  public String toString() {
    return color + " queen at (" + row + ", " + col + ")";
  }
}
