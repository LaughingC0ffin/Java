package ubung8.expr2;

public class Neg implements Expr {
  public final Expr op;

  public Neg(Expr op) {
    this.op = op;
  }

  @Override
  public <T> T accept(Visitor<T> visitor) { return visitor.visit(this); }

  @Override
  public String toString() { return "(-" + op + ")"; }
}
