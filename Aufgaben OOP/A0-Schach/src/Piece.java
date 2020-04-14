import java.util.List;

public abstract class Piece {
    public final Color color;
    public final int row;
    public final int col;

    public Piece(Color color, int row, int col) {
        if (row < 1 || row > 8 || col < 1 || col > 8)
            throw new IllegalArgumentException("Invalid pos " + row + "/" + col);
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public abstract boolean canCapture(Piece other, List<Piece> pieces);

    public abstract char charRep();
    public abstract String toString();
}
