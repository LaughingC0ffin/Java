package ubung8.expr2;

public class Var implements Expr {

    private final String name;

    public Var(String name){
        this.name=name;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
        return name;
    }
}
