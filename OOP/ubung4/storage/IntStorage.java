package ubung4.logistics.storage;

import ubung4.logistics.quantities.IntUnit;
import ubung4.logistics.quantities.NeedCollector;

public class IntStorage implements IntUnit{

    private int stored;
    private IntUnit unit;
    private final int max;

    public IntStorage(int stored, IntUnit unit, int max){
        this.max=max;
        this.stored=stored;
        this.unit=unit;
    }

    public int getStored() {
        return stored;
    }

    public IntUnit getUnit() {
        return unit;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString(){
        return "storage with " + stored+" of " + max + unit.toString();
    }

    /*
    Damit ein Beh¨alter geleert und bef¨ullt werden kann, erweitern Sie ihre Implementierung um die Methoden:
 int consume(int amount) - verbraucht die angegebene Menge des enthaltenen Versorgungsguts
und gibt den tats¨achlich verbrauchten Wert zur¨uck (falls zu wenig davon enthalten ist.)
 void fill(int amount) - f¨ullt den Beh¨alter um den ¨ubergebenen Wert wieder auf, aber nur bis
zur maximalen F¨ullmenge.
 void fillUp() - f¨ullt den Beh¨alter bis zur maximalen F¨ullmenge auf.
     */

    public int consume(int amount){
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
        int tmp= max-stored;
        collector.add(tmp,unit);
    }
}
