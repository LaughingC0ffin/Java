package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        String besitzer1 = "Gustav";
        String besitzer2 = "Peter";
        String besitzer3 = "Pascal";

        Hund hund1 = new Hund("Huski", 10);
        Hund hund2 = new Hund("Bulldoge", 20);
        Hund hund3 = new Hund("Dobermann", 30);

        HundHash hund4 = new HundHash("Huski", 10);
        HundHash hund5 = new HundHash("Bulldoge", 20);
        HundHash hund6 = new HundHash("Dobermann", 30);

        //equals noch machen

        HashSet<HundHash> hundeHash = new HashSet<>();

        hundeHash.add(hund4);
        hundeHash.add(hund4);
        hundeHash.add(hund4);
        System.out.println("Hunde HashSet mit HashFunktion size gleichen Hund Objekt: " + hundeHash.size());


        HundHash hund10 = new HundHash("Huski", 10);
        HundHash hund11 = new HundHash("Huski", 10);
        HundHash hund12 = new HundHash("Huski", 10);

        hundeHash.add(hund10);
        hundeHash.add(hund11);
        hundeHash.add(hund12);

        System.out.println("Hunde HashSet mit HashFunktion size 'gleicher Hund' : " + hundeHash.size());

        HashSet<Hund> hund = new HashSet<>();
        hund.add(hund1);
        hund.add(hund1);
        hund.add(hund1);

        System.out.println("Hunde HashSet ohne HashFunktion size gleiches objekt: " + hund.size());

        Hund hund7 = new Hund("Huski", 10);
        Hund hund8 = new Hund("Huski", 10);
        Hund hund9 = new Hund("Huski", 10);

        hund.add(hund7);
        hund.add(hund8);
        hund.add(hund9);

        System.out.println("Hunde HashSet ohne HashFunktion size 'verschiedene Hunde': " + hund.size());

        //Hund 4,5,6 ist mit Hashcode
        HashMap<String, HundHash> hundeHashMap = new HashMap<>();
        hundeHashMap.put(besitzer1, hund4);
        hundeHashMap.put(besitzer1, hund5);
        hundeHashMap.put(besitzer1, hund6);
        System.out.println("Hunde hashMap mit HashFunktion gleicher Besitzer " + hundeHashMap.size());
        //Eindeutigkeit der Keys
        //uneindeutigkeit der values

        hundeHashMap = new HashMap<>();
        hundeHashMap.put(besitzer1, hund4);
        hundeHashMap.put(besitzer2, hund4);
        hundeHashMap.put(besitzer3, hund4);
        System.out.println("Hunde hashMap mit HashFunktion " + hundeHashMap.size());
        System.out.println();

        //entrySet iterieren
        for (Entry<String, HundHash> entry : hundeHashMap.entrySet()) {
            String besitzer = entry.getKey();
            HundHash hunde1 = entry.getValue();
            System.out.println(besitzer+ " and " + hunde1);
        }
        System.out.println();

        //keySet iterieren
        for (String besitzer : hundeHashMap.keySet()) {
            HundHash hunde2 = hundeHashMap.get(besitzer);
            System.out.println(besitzer+" and "+hunde2);
        }
        System.out.println();

        //Iterator
        Set<Entry<String, HundHash>> entrySet = hundeHashMap.entrySet();
        Iterator<Entry<String, HundHash>> iterator= entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<String,HundHash> entry = iterator.next();
            String key = entry.getKey();
            HundHash value= entry.getValue();
            System.out.println( key+ " and " + value);
        }

        System.out.println(hund10==hund11);
        System.out.println(hund10.equals(hund11));
    }
}
