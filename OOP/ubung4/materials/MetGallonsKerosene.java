package ubung4.logistics.materials;

import ubung4.logistics.quantities.FloatUnit;

public class MetGallonsKerosene implements FloatUnit {

    private MetGallonsKerosene() {}

    public final static MetGallonsKerosene INSTANCE=new MetGallonsKerosene();

    @Override
    public String toString(){
        return "met gallons of kerosene";
    }
}
