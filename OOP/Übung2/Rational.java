package Übung2;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;
import static java.lang.System.out;

//https://moodle.oncampus.de/modules/demo/ir018/P1DMET/01methoden/ueberlad.shtml

public class Rational {
int nenner,zahler;

    Rational( int zahler, int nenner){
        //kürzen und normalisieren
        this.zahler=zahler;
        this.nenner=nenner;

        this.kuerzen();
    }





    Rational(int zahler){
        this.zahler = zahler;
        //geschumelt
        this.nenner = 1;
    }




    //getter Funktionen
    public int getZaehler()
    {
        return zahler;
    }

    public int getNenner()
    {
        return nenner;
    }

    static int ggt(int zahler, int nenner) {
        if(nenner == 0)
        {
            return zahler;
        }
        return ggt(nenner, zahler % nenner);
    }

    void kuerzen() {
        int g = ggt(zahler, nenner);
        if (nenner < 0) {
            nenner = -nenner;
            zahler = -zahler;
        }

        if (g > 1){
            zahler /= g;
            nenner /= g;
        }
    }

    @Override
    public String toString() {
        if(nenner == 1) return zahler+"";
        else return zahler + "/" + nenner;
    }
//die Operationen rufe ich auf den Objekten auf
    //also Objekt.funktion()
    //es wäre auch möglich die Funktionen folgendermaßen zu schreiben
    // add(Rational a, Rational b)
    //im Hinblick auf überladung könnte man es auch so schreiben
    //add(inta, int b, int c,int d)     zwei "Brüche"
    //add(int a , int b,int c)          ein Bruch und eine Zahl
    //ich habe etwas geschumelt mit meinem this.nenner=1 und brauche keine Überladung
public Rational add( Rational a)
{
    int x = zahler  * a.getNenner() + nenner * a.getZaehler();
    int y = nenner * a.getNenner();

    Rational b = new Rational(x,y);
    b.kuerzen();
    return b;
}

    public Rational sub( Rational a)
    {
        int x = zahler  * a.getNenner() - nenner * a.getZaehler();
        int y = nenner * a.getNenner();

        Rational b = new Rational(x,y);
        b.kuerzen();
        return b;
    }

    public Rational div( Rational a) {
        int x = zahler  *a.getNenner();
        int y = nenner * a.getZaehler();

        Rational b = new Rational(x,y);
        b.kuerzen();
        return b;
    }

    public Rational mult( Rational a)
    {
        int x = zahler  * a.getZaehler();
        int y = nenner * a.getNenner();

        Rational b = new Rational(x,y);
        b.kuerzen();
        return b;
    }

    public static void main(String[] args) {
        //hier sieht man sehr gut das die Funktionen auf den Objekten aufgerufen werden
        //hätte man halt auch mit static funktionen lösen können die man unanbhängig vom Objekt aufruft
        Rational r = new Rational(1).add(new Rational(2).div(new Rational(4,3)));
        Rational r2 = r.div(new Rational(3,4));
        out.println(r.toString());
    }


}
