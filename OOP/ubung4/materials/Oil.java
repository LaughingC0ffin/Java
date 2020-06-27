package ubung4.logistics.materials;

import ubung4.logistics.quantities.IntUnit;

public class Oil implements IntUnit {

    private Oil() {}

    public final static Oil INSTANCE=new Oil();

    @Override
    public String toString(){
        return "units of oil";
    }
}
