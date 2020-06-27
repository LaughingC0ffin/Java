package ubung4.logistics.materials;

import ubung4.logistics.quantities.IntUnit;

public class RocketPods implements IntUnit {

    private RocketPods() {}

    public final static RocketPods INSTANCE=new RocketPods();

    @Override
    public String toString(){
        return "pods of 70mm rockets";
    }
}
