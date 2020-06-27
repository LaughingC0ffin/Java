package ubung4.logistics.quantities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NeedCollector {

    //intNeeded und floatNeeded vom Typ Map<IntUnit, Integer>
    private Map<IntUnit, Integer> intNeeded =  new HashMap<>();
    private Map<FloatUnit, Float> floatNeeded= new HashMap<>();



    /*
    void add(int amount, IntUnit unit) - f¨ugt den Bedarf am Versorgungsgut mit der angegebenen
Einheit und der angegebenen Menge zum bislang ermittelten Gesamtbedarf hinzu.
 void add(float amount, FloatUnit unit) - ¨aquivalent f¨ur Versorgungsg¨uter, deren Mengenabgaben
mit Fließkommazahlen angegeben werden.
 int getNeed(IntUnit unit) - gibt den aktuell gespeicherten Gesamtbedarf des Versorgungsguts
mit der ¨ubergebenen Einheit zur¨uck.
 float getNeed(FloatUnit unit) - ¨aquivalent f¨ur Versorgungsg¨uter, deren Mengenabgaben
mit Fließkommazahlen angegeben werden.
 void show() - druckt den Gesamtbedarf aller bekannter Versorgungsg¨uter auf der Konsole aus.
     */

    public void add(int amount, IntUnit unit){
        Integer tmp = intNeeded.getOrDefault(unit,0);
        intNeeded.put(unit,tmp+amount);
    }

    public void add(float amount, FloatUnit unit){
        Float tmp=floatNeeded.getOrDefault(unit,0f);
        floatNeeded.put(unit,tmp+amount);
    }

    public int getNeed(IntUnit unit){
        return intNeeded.getOrDefault(unit,0);
    }

    public float getNeed(FloatUnit unit){
        return floatNeeded.getOrDefault(unit,0f);
    }

    public void show(){

        System.out.println("float need: ");
        Set<FloatUnit> keyset = floatNeeded.keySet();
        for(FloatUnit unit : keyset){
            System.out.println(unit+ "  " + floatNeeded.get(unit));
        }

        System.out.println("int need: ");
        Set<IntUnit> keyset2 = intNeeded.keySet();
        for(IntUnit unit : keyset2){
            System.out.println(unit+ "  " + intNeeded.get(unit));
        }

    }

}
