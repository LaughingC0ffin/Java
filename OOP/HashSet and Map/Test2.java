package Bsp;

import java.util.HashSet;
import java.util.Set;

public class Test2 {

    public static void main(String[] args) {
        System.out.println("John".hashCode());
        System.out.println("Anna".hashCode());
        System.out.println("Andy".hashCode());


        Set<String> mySet = new HashSet<>();
        mySet.add("hello");
        System.out.println(mySet.contains(1));
    }


}

