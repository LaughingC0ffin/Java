package Übung1;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;

public class ubung1 {

    private static Scanner scanner;

    //wo ist der unterschied zwischen static und kein static
    static void foo(int x, int y, String z) {
        out.println(x + " " + y + " " + z);
    }

    /*
    c) Berechnen Sie nun anhand der eingegebenen Zahl des Benutzers die entsprechende Anzahl an Fibonacci Zahlen
und geben Sie diese auf der Konsole aus.
     */
    public static int[] fib ( int n ) {
         int [] fibs = new int[n];
         fibs[0]= 0;
        fibs[1]=1;
        for (int i =2;i<n;i++) {
            fibs[i] = fibs[i-1] + fibs[i-2];
        }
        return fibs;
    }
    //Konstruktor
    ubung1(InputStream in, PrintStream out) {

    }

    public static void main(String[] args) {
        /*
        a) Legen Sie in der Klasse MyScanner eine main-Methode an und erzeugen Sie sich die drei Variablen count,
        num1 und num2 jeweils vom Typ int.
         */
        int count,num1,num2;
        //neues Scanner Objekt
        Scanner scanner = new Scanner(System.in);
        out.println("How many numbers you want in the sequence:");
        while (true) {
            try {
                /*
                b) Erzeugen Sie in der main-Methode auf der Konsole die Abfrage an den Benutzer, wie viele Fibonacci Zahlen
                er berechnen möchte und ermöglichen Sie ihm die Eingabe einer Zahl.
                */
                int i = scanner.nextInt();
                if(i<=0) throw new InputMismatchException();
                //out.println(i);
                out.println(Arrays.toString(fib(i)));
            }
            catch (InputMismatchException ex) {
                out.println("Das war keine gültige Zahl (größer 0 und N)");
            }
            catch (Exception ex) {
                out.println(ex);
            }
        }
    }
}
