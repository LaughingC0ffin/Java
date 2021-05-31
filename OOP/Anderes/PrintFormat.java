package Beispiel.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintFormat {

    public static void main(String[] args) {
        float floatVar = 0.8f;
        int intVar = 8;
        String stringVar = "test";
        System.out.format("The value of " + "the float variable is " +
                          "%f, while the value of the " + "integer variable is %d, " +
                          "and the string is %s", floatVar, intVar, stringVar);

        /*
    s formats strings.
    d formats decimal integers.
    f formats floating-point numbers.
    t formats date/time values.
         */
        System.out.printf(" this is a String %s, and this is a float %f","string",0.8f);
        System.out.println();
        System.out.println(floatVar);
        System.out.println(intVar);
        System.out.println(stringVar);
    }
}
