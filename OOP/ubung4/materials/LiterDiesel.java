package ubung4.logistics.materials;

import ubung4.logistics.quantities.FloatUnit;

public class LiterDiesel implements FloatUnit {

    private LiterDiesel() {}

    public final static LiterDiesel INSTANCE=new LiterDiesel();

    @Override
    public String toString(){
        return "liters of diesel";
    }

}
