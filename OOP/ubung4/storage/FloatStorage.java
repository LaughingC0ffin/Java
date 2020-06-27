package ubung4.logistics.storage;

import ubung4.logistics.quantities.FloatUnit;
import ubung4.logistics.quantities.NeedCollector;

public class FloatStorage implements FloatUnit {

    private float stored;
    private FloatUnit unit;
    private final float max;

    public FloatStorage(int stored, FloatUnit unit, int max){
        this.max=max;
        this.stored=stored;
        this.unit=unit;
    }

    public float getStored() {
        return stored;
    }

    public FloatUnit getUnit() {
        return unit;
    }

    public float getMax() {
        return max;
    }

    /*
    U¨ berschreiben Sie die toString()-Methode der Klasse so, dass ein mit
new IntStorage(2, Oil.INSTANCE, 3)
storage with 2 of 3 units of oil
     */

    @Override
    public String toString(){
        return "storage with " + stored+" of " + max + unit.toString();
    }

    public float consume(int amount){
        if(stored-amount<0) {
            stored=0;
            return stored;
        }
        else{
            stored=stored-amount;
            return amount;
        }
    }

    public void fill(int amount){
        if(amount+stored> max){
            stored=max;
        }
        else {
            stored=stored+amount;
        }
    }

    public void fillUp(){
        stored=max;
    }

    public void reportNeed(NeedCollector collector){
        float tmp= max-stored;
        collector.add( tmp, unit);
    }
}
