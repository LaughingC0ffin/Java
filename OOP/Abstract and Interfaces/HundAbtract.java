package Übung3.sontiges;

public abstract class HundAbtract {

    //mit Konstruktur
    //möglichkeit default deklarationen machen

    public int height;
    public int blase;
    public boolean canSwim;
    public String name;

    public HundAbtract(int height,int blase,String name){
        this.height=height;
        this.blase=blase;
        this.name=name;

    }

    @Override
    public String toString(){
        return "Hund";
    }


    public abstract void bellen(String gebell);


}
