package ubung8.expr2;

public class Test {
  public static void main(String[] args) {
    Expr e = new Plus(new Neg(new Minus(new Const(1), new Const(2))), new Const(12));
    System.out.println(e + " = " + e.accept(new EvalVisitor()));
    //System.out.println(e + " = " + e.accept(new LukasiewiczVisitor()));


    Expr z = new Mult(new Var("x",2),new Plus(new Mult(new Var("y",2),new Const(0)),new Const(1)));
    System.out.println(z + " = " + z.accept(new SimplifyVisitor()));

    System.out.println();

    Expr der = new Sinus(new Var("x",2));
    System.out.println(der + " = " + der.accept(new DerivativeVisitor("x")));

    Expr der1 = new Cosinus(new Var("x",2));
    System.out.println(der1 + " = " + der1.accept(new DerivativeVisitor("x")));

  }
}
