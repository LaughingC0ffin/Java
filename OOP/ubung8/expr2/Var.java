package ubung8.expr2;

public class Var implements Expr {

    public final String name;
    public int power;

    public Var(String name, int power){
        this.name=name;
        this.power=power;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
        if(power>1)return name+"^"+power;
        else return name+"";
    }
}
