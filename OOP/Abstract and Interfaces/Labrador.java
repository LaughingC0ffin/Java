package Übung3.sontiges;

public class Labrador extends HundAbtract{

    public String colour;

    public Labrador(String colour) {

        // initialisierung mit konkreten werten
        //noch erweiterbar

        super(60, 5,"labrador");
        this.canSwim = true;
        this.colour = colour;
    }

    @Override
    public void bellen(String gebell) {
        System.out.println("Whooof");
    }

    @Override
    public String toString(){
        return "labrador";
    }
}
