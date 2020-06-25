package ubung8.expr2;

public class LukasiewiczVisitor implements Visitor<String> {
  @Override
  public String visit(Const e) {
    return String.valueOf(e.value);
  }

  @Override
  public String visit(Plus e) {
    return "+ " + e.op1.accept(this) + " " + e.op2.accept(this);
  }

  @Override
  public String visit(Minus e) {
    return "- " + e.op1.accept(this) + " " + e.op2.accept(this);
  }

  @Override
  public String visit(Mult e) {
    return "* " + e.op1.accept(this) + " " + e.op2.accept(this);
  }

  @Override
  public String visit(Neg e) {
    return "~ " + e.op.accept(this);
  }

  @Override
  public String visit(Cosinus e) {
    return null;
  }

  @Override
  public String visit(Sinus e) {
    return null;
  }

  @Override
  public String visit(Var var) {
    return null;
  }
}
