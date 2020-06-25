package Übung2;

import static java.lang.System.out;
//https://moodle.oncampus.de/modules/demo/ir018/P1DMET/01methoden/ueberlad.shtml

public class Rational2 {
    int nenner,zahler;

    Rational2( int zahler, int nenner){
        //kürzen und normalisieren
        this.zahler=zahler;
        this.nenner=nenner;
        this.kuerzen();
    }

    Rational2(int zahler){
        this.zahler = zahler;
    }




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
        if(nenner ==0)return;
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
        if(nenner == 0) return zahler+"";
        else return zahler + "/" + nenner;
    }

    public Rational2 add( Rational2 a) {
        int x = zahler  * a.getNenner() + nenner * a.getZaehler();
        int y = nenner * a.getNenner();

        Rational2 b = new Rational2(x,y);
        b.kuerzen();
        return b;
    }
    /*
        public Rational2 add( Rational2 a) {
        int x = zahler  * a.getNenner() + nenner * a.getZaehler();
        int y = nenner * a.getNenner();

        Rational2 b = new Rational2(x,y);
        b.kuerzen();
        return b;
    }
     */

    public Rational2 sub( Rational2 a) {
        int x = zahler  * a.getNenner() - nenner * a.getZaehler();
        int y = nenner * a.getNenner();

        Rational2 b = new Rational2(x,y);
        b.kuerzen();
        return b;
    }

    public Rational2 div( Rational2 a) {
        int x = zahler  *a.getNenner();
        int y = nenner * a.getZaehler();

        Rational2 b = new Rational2(x,y);
        b.kuerzen();
        return b;
    }

    public Rational2 mult( Rational2 a) {
        int x = zahler  * a.getZaehler();
        int y = nenner * a.getNenner();

        Rational2 b = new Rational2(x,y);
        b.kuerzen();
        return b;
    }


    public static void main(String[] args) {
        //Rational r = new Rational(1).add(new Rational(2).div(new Rational(4,3)));
        //out.println(r.toString());
        Rational2 r1 = new Rational2(3);
        r1.kuerzen();
        out.println(r1.toString());
        out.println(r1.nenner);

    }
}