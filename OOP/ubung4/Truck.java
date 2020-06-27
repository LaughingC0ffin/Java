package ubung4.logistics;

import ubung4.logistics.materials.BulletBelts;
import ubung4.logistics.materials.LiterDiesel;
import ubung4.logistics.quantities.NeedCollector;
import ubung4.logistics.storage.FloatStorage;
import ubung4.logistics.storage.IntStorage;

public class Truck extends Vehicle {

    FloatStorage diesel;
    IntStorage mun ;

    public Truck(String name, int diesel,int mun) {
        super(name, 3, 3);
        this.diesel=new FloatStorage(diesel, LiterDiesel.INSTANCE,180);
        this.mun = new IntStorage(mun, BulletBelts.INSTANCE,3);
    }

    @Override
    void reportNeeds(NeedCollector collector) {
        grease.reportNeed(collector);
        oil.reportNeed(collector);
        mun.reportNeed(collector);
        diesel.reportNeed(collector);
    }

    @Override
    void fillUpAll() {
        oil.fillUp();
        grease.fillUp();
        diesel.fillUp();
        mun.fillUp();
    }

    @Override
    void consumeAll(int intensityRate) {
        oil.consume(1);
        grease.consume(1);
        diesel.consume(intensityRate*25);
        mun.consume(intensityRate*2);
    }
}
