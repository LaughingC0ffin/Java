package ubung8.expr2;

public class Plus extends BinExpr {
  public Plus(Expr op1, Expr op2) { super(op1, '+', op2); }

  @Override
  public <T> T accept(Visitor<T> visitor) { return visitor.visit(this); }
}
