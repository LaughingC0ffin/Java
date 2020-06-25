package ubung7Gruppe.chess;

import ubung7Gruppe.iterator.Array2dIterator;
import ubung7Gruppe.iterator.SkipNull;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable<Piece> {
 // private final List<Piece> pieces = new ArrayList<>();
  private final Piece[][] field = new Piece[8][8];

  //public List<Piece> getPieces() {
  //  return pieces;
  //}

  void add(Piece piece) {
    if (piece.board != this)
      throw new IllegalArgumentException("wrong board");
    if (pieceAt(piece.row, piece.col) != null)
      throw new IllegalArgumentException("Field " + piece.row + "/" + piece.col + " is occupied");
   // pieces.add(piece);
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
    public void check2(PrintStream out) {

      SkipNull iterator1= new SkipNull<Piece>(iterator());
      while(iterator1.hasNext()){
          Piece tmp1 = (Piece) iterator1.next();
          out.println(tmp1);
          SkipNull iterator2= new SkipNull<Piece>(iterator());
          while(iterator2.hasNext()){
              Piece tmp2= (Piece) iterator2.next();
              if(tmp1!=tmp2){
                  if (tmp1.canCapture(tmp2))
                      out.println("   can capture " + tmp2);
                  else
                      out.println("   cannot capture " + tmp2);
              }
          }
      }

    }


  public void check(PrintStream out) {

    Iterator<Piece> iterator1 = iterator();

    while(iterator1.hasNext()){
      Piece tmp1=iterator1.next();
      if(tmp1!=null){
        out.println(tmp1);
        Iterator<Piece> iterator2 = iterator();
        while(iterator2.hasNext()){
          Piece tmp2=iterator2.next();
          if(tmp2!=null && tmp1!=tmp2){
            if (tmp1.canCapture(tmp2))
              out.println("   can capture " + tmp2);
            else
              out.println("   cannot capture " + tmp2);
          }
        }
      }
    }
  }


  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */


  @Override
  public Iterator<Piece> iterator() {
    return new Array2dIterator<Piece>(field);
  }

}
