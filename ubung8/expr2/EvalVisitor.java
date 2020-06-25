package ubung8.expr2;

import java.util.Map;

public class EvalVisitor implements Visitor<Double> {

  private Map<Var,Double> map;

  EvalVisitor(Map<Var, Double> assignment){
    this.map= assignment;
  }

  public EvalVisitor() {
    this.map=null;
  }

  public Double  visit(Const e) {
    return e.value;
  }

  public Double  visit(Plus e) {
    return e.op1.accept(this) + e.op2.accept(this);
  }

  public Double  visit(Minus e) {
    return e.op1.accept(this) - e.op2.accept(this);
  }

  public Double  visit(Mult e) {
    return e.op1.accept(this) * e.op2.accept(this);
  }

  public Double  visit(Neg e) {
    return -e.op.accept(this);
  }

  public Double visit(Cosinus e){
    return Math.cos(e.op.accept(this));
  }

  public Double visit(Sinus e){
    return Math.sin(e.op.accept(this));
  }

  public Double visit(Var e){
    if(map==null) throw new IllegalArgumentException("keine Map für Var");
    return Math.pow(map.get(e),e.power);
  }

}
