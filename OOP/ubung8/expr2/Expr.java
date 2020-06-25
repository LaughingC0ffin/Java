package ubung8.expr2;

public interface Expr {
  <T> T accept(Visitor<T> visitor);
}

