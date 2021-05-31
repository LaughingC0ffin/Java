package Bsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import Bsp.Hund;

public class ConcurrentModEx {

    public static void main(String[] args) {

        HashMap<String, Hund> myHashMap = new HashMap<>();
        myHashMap.put("John", new Hund("Huski", 50));
        myHashMap.put("Anna", new Hund("Chiwauwa", 5));
        myHashMap.put("Andy", new Hund("Schäferhund", 60));
        myHashMap.put("Peter", new Hund("Huski", 50));
        myHashMap.put("Lisa", new Hund("Chiwauwa", 5));
        myHashMap.put("Dandy", new Hund("Schäferhund", 60));
        System.out.println(myHashMap);

        Set<String> set = myHashMap.keySet();

        Iterator<String> setIterator = set.iterator();

        while (setIterator.hasNext()) {
            String element = setIterator.next();
            Hund hund = myHashMap.get(element);
        }
        //ODER
        //implizites Aufrufen
        for (String element : set) {

        }

        //https://riptutorial.com/java/example/6684/removing-elements-using-an-iterator

        //remove something while iterating
        /*
        List<String> names = new ArrayList<>();
        names.add("name 1");
        names.add("name 2");
        names.add("");
        names.add("name 3");
        names.add("");
        System.out.println("Old Size : " + names.size());
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String el = it.next();
            if (el.equals("")) {
                it.remove();
            }
        }
        System.out.println("New Size : " + names.size());
         */
        System.out.println();
        System.out.println("Size before: " +myHashMap.size());
        //now modify the Set while iterating over it
        Iterator<String> setIter = set.iterator();
        while (setIter.hasNext()) {
            String element = setIter.next();
            System.out.println(element+ " " + myHashMap.get(element));
            //Anna and anna
            //if(element.equals("Anna")) myHashMap.put("Anna",new Hund("name",10));
            //if(element.equals("Anna")) myHashMap.put("anna",new Hund("name",10));
            if(element.equals("Anna")) myHashMap.put("Lisa",new Hund("name",10));
            //if(element.equals("Anna")) setIter.remove();
        }
        System.out.println("size after: " + myHashMap.size());

    }
}



