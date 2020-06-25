package ubung8.expr2;

public class Minus extends BinExpr {
  public Minus(Expr op1, Expr op2) {
    super(op1, '-', op2);
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {return visitor.visit(this);}
}
