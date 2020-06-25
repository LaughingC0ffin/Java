package Übung;

import java.util.Scanner;


public class MyScanner {

    public static void rechnung(){
        int count;
        int num1 = 0;
        int num2 = 1;
        Scanner eingabewert = new Scanner(System.in);

        System.out.print("How many numbers you want in the sequence?: ");
        count = eingabewert.nextInt();

            if (count > -1) {
                System.out.println("You want " + count + " numbers in the sequence.");
                System.out.print("Fibonacci Series of " + count + " numbers: ");
                for (int zähler = 1; zähler <= count; zähler++) {
                    if (num1 == num2) {
                        System.out.print(num1 + " ");
                        num1 = num1 + num2;
                    } else if (num1 < num2) {
                        System.out.print(num1 + " ");
                        num1 = num1 + num2;
                    } else if (num1 > num2) {
                        System.out.print(num2 + " ");
                        num2 = num1 + num2;
                    }
                }
            } else {
                System.out.println("You must enter a natural number.For example 0,1,2,3,4,...");
                rechnung();
            }
    }

    public static void main ( String[] args ) {
        rechnung();
    }
}
