package ubung8.expr2;

public class Cosinus implements Expr {

    public final Expr op;

    public Cosinus(Expr op) {
        this.op = op;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }


    public String toString(){
        return "Cosinus("+op.toString()+")";
    }



}
