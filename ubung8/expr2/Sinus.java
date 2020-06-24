package ubung8.expr2;

public class Sinus implements Expr {

    public final Expr op;

    public Sinus(Expr op) {
        this.op = op;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
