package Übung2;

import scala.Int;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;
import static java.lang.System.out;

public class Polynomial {
    private int [] array;
    final int degree;

    public Polynomial(int [] iwas){
        this.array=iwas;
        //falsch
        //vielleicht funktion für programmieren, der man ein array übergibt
        //=>> Bennesch
        this.degree=iwas.length-1;
    }

    public Polynomial(){
        this.array=new int[]{0,1};
        this.degree= 1;
    }
    public static Polynomial constant(int v){
        return new Polynomial(new int[] {v});
    }
    public int[] getArray()
    {
        return array;
    }

    public int getDegree() {
        return degree;
    }





    ///////UNDONE
    @Override
    public String toString() {
        String out = "";
    //Bennesch
       for(int a=array.length-1;a>=0;a--){
           if(array[a]==0) out+="";
           else if(a==0 && array[a]<0) out+= array[a];
           else if(array[a]<0)out+= array[a]+"x^"+a;
           else if(a==0) out+= "+"+array[a];
           else out+= "+"+array[a]+"x^"+a;
       }
        return out;
    }



    public static Polynomial add(Polynomial a,Polynomial b){
        //Kurzfassung von if
        int[] neu = a.array.length>b.array.length ? a.array : b.array;
        //nur den Teil den beide Arrays überschneiden
        //also bis zum Ende des kleineren
        for(int x = 0 ;x<Math.min(a.array.length,b.array.length);x++){
            neu[x]=a.array[x]+b.array[x];
        }
        return new Polynomial(neu);
    }

    public static Polynomial sub(Polynomial a,Polynomial b){

        int[] neu = a.array.length>b.array.length ? a.array : b.array;

        for(int x = 0 ;x<Math.min(a.array.length,b.array.length);x++){
            neu[x]=a.array[x]-b.array[x];
        }
        return new Polynomial(neu);
    }
//siehe Skizze
    public static Polynomial mul(Polynomial a,Polynomial b){
        //-2 auf -1 geändert
        int[] neu =new int[a.array.length+b.array.length-1];
        for(int x = 0 ;x<a.array.length;x++){
            for(int y= 0;y<b.array.length;y++){
                neu[x+y]+=a.array[x]*b.array[y];
            }
        }
        return new Polynomial(neu);
    }
/*
    //dies wäre ein Beispiel für überladene Funktionen

    public static Polynomial mul(int[] a,int[] b){
        int[] neu =new int[a.length+b.length-2];
        for(int x = 0 ;x<a.length;x++){
            for(int y= 0;y<b.length;y++){
                neu[x+y]+=a[x]*b[y];
            }
        }
        return new Polynomial(neu);
    }

    public static Polynomial mul(int[] a,int b){
        int[] neu =new int[a.length];
        for(int x = 0 ;x<a.length;x++){
                neu[x]+=a[x]*b;
            }
        return new Polynomial(neu);
    }

 */

    public static Polynomial exp(Polynomial a,int ex){
        if(ex == 0)return constant(1);
        else if(ex == 1) return a;
        Polynomial zwisch = new Polynomial(a.array);
        for(int x = 2;x<=ex;x++){
            zwisch = mul(a,zwisch);
        }
        return zwisch;
    }

    public int apply(int v){
        int out = 0;

        for(int x = array.length-1;x>=0;x--){
            out=array[x]+out*v;
        }
        return out;
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial(new int[]{-4, -1, 3, 0});
        out.println(Arrays.toString(p.array));
        //out.println(p.degree);
        out.println(p.toString());
        Polynomial quadr = new Polynomial(new int[]{0,0,1});

        out.println(quadr.apply(2));
        out.println(exp(quadr,3).toString());

    }

}
