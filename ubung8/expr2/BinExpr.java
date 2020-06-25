package ubung8.expr2;

public abstract class BinExpr implements Expr {
  public final Expr op1, op2;
  private final char op;

  public BinExpr(Expr op1, char op, Expr op2) {
    this.op1 = op1;
    this.op2 = op2;
    this.op = op;
  }

  @Override
  public String toString() { return "(" + op1 + op + op2 + ")"; }
}
