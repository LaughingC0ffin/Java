package ubung4.logistics.materials;

import ubung4.logistics.quantities.IntUnit;

public class Grease implements IntUnit {

    private Grease() {}

    public final static Grease INSTANCE=new Grease();

    @Override
    public String toString(){
        return "units of grease";
    }

}
