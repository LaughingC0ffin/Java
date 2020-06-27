package ubung4.logistics;

import ubung4.logistics.materials.BulletBelts;
import ubung4.logistics.materials.LiterDiesel;
import ubung4.logistics.materials.RocketPods;
import ubung4.logistics.quantities.NeedCollector;
import ubung4.logistics.storage.FloatStorage;
import ubung4.logistics.storage.IntStorage;

public class Helicopter extends Vehicle {

    IntStorage mun ;
    FloatStorage diesel;
    IntStorage rocket;

    public Helicopter(String name,int mun, int diesel,int rocket) {
        super(name, 3, 3);
        this.mun = new IntStorage(mun, BulletBelts.INSTANCE, 2);
        this.diesel=new FloatStorage(diesel, LiterDiesel.INSTANCE, 500);
        this.rocket=new IntStorage(0, RocketPods.INSTANCE,rocket);
    }

    @Override
    void reportNeeds(NeedCollector collector) {
        grease.reportNeed(collector);
        oil.reportNeed(collector);
        mun.reportNeed(collector);
        diesel.reportNeed(collector);
        rocket.reportNeed(collector);
    }

    @Override
    void fillUpAll() {
        mun.fillUp();
        diesel.fillUp();
        rocket.fillUp();
        oil.fillUp();
        grease.fillUp();
    }

    @Override
    void consumeAll(int intensityRate) {
        oil.consume(1);
        grease.consume(1);
        mun.consume(intensityRate);
        diesel.consume(intensityRate*200);
        rocket.consume(intensityRate);

    }
}
