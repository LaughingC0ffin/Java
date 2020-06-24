package ubung8.expr2;

public class SimplifyVisitor implements Visitor<Expr> {
    @Override
    public Expr visit(Const e) {
        return e;
    }

    @Override
    public Expr visit(Plus e) {
        if(e.op1 instanceof Const && ((Const) e.op1).value==0){
            return e.op2.accept(this);
        }
        else if(e.op2 instanceof Const && ((Const) e.op2).value==0){
            return e.op1.accept(this);
        }
        else if(e.op1.accept(this) instanceof Const && ((Const) e.op1.accept(this)).value==0){
            return e.op2.accept(this);
        }
        else if(e.op2.accept(this) instanceof Const && ((Const) e.op2.accept(this)).value==0){
            return e.op2.accept(this);
        }
        else{
            System.out.println("Plus");
            return new Plus(e.op1.accept(this),e.op2.accept(this));
        }
    }

    @Override
    public Expr visit(Minus e) {
        if(e.op1 instanceof Const && ((Const) e.op1).value==0){
            return e.op2.accept(this);
        }
        else if(e.op2 instanceof Const && ((Const) e.op2).value==0){
            return e.op1.accept(this);
        }
        else if(e.op1.accept(this) instanceof Const && ((Const) e.op1.accept(this)).value==0){
            return e.op2.accept(this);
        }
        else if(e.op2.accept(this) instanceof Const && ((Const) e.op2.accept(this)).value==0){
            return e.op2.accept(this);
        }
        else{
            return new Minus(e.op1.accept(this),e.op2.accept(this));
        }
    }

    @Override
    public Expr visit(Mult e) {

        if(e.op1 instanceof Const ){
            if(((Const) e.op1).value==1){
                return e.op2.accept(this);
            }
            else if(((Const) e.op1).value==0){
                return new Const(0);
            } else{
            return new Mult(e.op1.accept(this),e.op2.accept(this));
        }

        }else if(e.op2 instanceof Const ){

            if(((Const) e.op2).value==1){
                return e.op1.accept(this);
            }
            else if(((Const) e.op2).value==0){
                return new Const(0);
            }
            return e.op1.accept(this);
        }
        else if(e.op1.accept(this) instanceof Const && ((Const) e.op1.accept(this)).value==0){
            return new Const(0);
        }
        else if(e.op2.accept(this) instanceof Const && ((Const) e.op2.accept(this)).value==0){
            return new Const(0);
        }
        else if(e.op1.accept(this) instanceof Const && ((Const) e.op1.accept(this)).value==1){
            return e.op2.accept(this);
        }
        else if(e.op2.accept(this) instanceof Const && ((Const) e.op2.accept(this)).value==1){
            return e.op1.accept(this);
        }
        else{
            return new Mult(e.op1.accept(this),e.op2.accept(this));
        }
    }

    @Override
    public Expr visit(Neg e) {
        return e.op.accept(this);
    }

    @Override
    public Expr visit(Cosinus e) {
        return  e.op.accept(this);
    }

    @Override
    public Expr visit(Sinus e) {
        return new Sinus(e.op.accept(this));
    }

    @Override
    public Expr visit(Var var) {
        return var;
    }
}
