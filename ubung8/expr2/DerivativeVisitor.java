package ubung8.expr2;

public class DerivativeVisitor implements Visitor<Expr> {

    private final String varia;

    public DerivativeVisitor(String varia) {
        this.varia = varia;
    }

    @Override
    public Expr visit(Const e) {
        return new Const(0);
    }

    @Override
    public Expr visit(Plus e) {
        return new Plus(e.op1.accept(this),e.op2.accept(this));
    }

    @Override
    public Expr visit(Minus e) {
        return new Minus(e.op1.accept(this),e.op2.accept(this));
    }

    @Override
    public Expr visit(Mult e) {
        return new Plus(new Mult(e.op1.accept(this),e.op2),new Mult( e.op1 ,e.op2.accept(this)));
    }

    @Override
    public Expr visit(Neg e) {
        return new Neg(e.op.accept(this));
    }

    @Override
    public Expr visit(Cosinus e) {
        return new Mult( new Neg(new Sinus(e.op)),e.op.accept(this));
    }

    @Override
    public Expr visit(Sinus e) {
        return new Mult(new Cosinus(e.op),e.op.accept(this));
    }

    @Override
    public Expr visit(Var var) {
        if(var.name.equals(varia)) {
            if (var.power == 1) return new Const(0);
            return new Mult(new Const(var.power), new Var(var.name, var.power - 1));
        }else{
            return new Const(0);
        }
    }
}
