package Übung1;


import java.util.InputMismatchException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;

import static java.lang.System.out;

public class ubung1 {

    public static int[] fib ( int n ) {
        if(n<=0) {
            throw new InputMismatchException();
        }
        if(n==1) return new int[]{0};
         int [] fibs = new int[n];
         fibs[0]= 0;
        fibs[1]=1;
        for (int i =2;i<n;i++) {
            fibs[i] = fibs[i-1] + fibs[i-2];
        }
        return fibs;
    }

    public static void main(String[] args) {
        int count,num1,num2;
        //neues Scanner Objekt

        out.println("How many numbers you want in the sequence:");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                if(i<=0) throw new InputMismatchException();
                out.println(Arrays.toString(fib(i)));
                break;
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

