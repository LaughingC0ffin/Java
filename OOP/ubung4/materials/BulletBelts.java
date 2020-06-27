package ubung4.logistics.materials;

import ubung4.logistics.quantities.IntUnit;

public class BulletBelts implements IntUnit {

    private BulletBelts() {}

    public final static BulletBelts INSTANCE=new BulletBelts();

    @Override
    public String toString(){
        return "belts of 7.62 bullets";
    }

}
