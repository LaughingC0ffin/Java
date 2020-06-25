package ubung7Gruppe.chess;

public abstract class Piece {
  public final Board board;
  public final Color color;
  public final int row;
  public final int col;

  public Piece(Color color, int row, int col, Board board) {
    this.board = board;
    if (row < 1 || row > 8 || col < 1 || col > 8)
      throw new IllegalArgumentException("Invalid pos " + row + "/" + col);
    this.color = color;
    this.row = row;
    this.col = col;
    board.add(this);
  }

  public abstract boolean canCapture(Piece other);

  public abstract char charRep();
}
