package ubung4.logistics;

import ubung4.logistics.materials.BulletBelts;
import ubung4.logistics.materials.LiterDiesel;
import ubung4.logistics.materials.ShellBatches;
import ubung4.logistics.quantities.NeedCollector;
import ubung4.logistics.storage.FloatStorage;
import ubung4.logistics.storage.IntStorage;

public class Tank extends Vehicle {

    IntStorage mun ;
    FloatStorage diesel;
    IntStorage grenade;

    public Tank(String name,int mun, int diesel,int grenade) {
        super(name, 3, 3);
        this.mun = new IntStorage(mun, BulletBelts.INSTANCE, 10);
        this.diesel=new FloatStorage(diesel, LiterDiesel.INSTANCE, 1200);
        this.grenade=new IntStorage(0, ShellBatches.INSTANCE,grenade);
    }

    @Override
    void reportNeeds(NeedCollector collector) {
        grease.reportNeed(collector);
        oil.reportNeed(collector);
        mun.reportNeed(collector);
        diesel.reportNeed(collector);
        grenade.reportNeed(collector);
    }

    @Override
    void fillUpAll() {
        oil.fillUp();
        grease.fillUp();
        mun.fillUp();
        diesel.fillUp();
        grenade.fillUp();
    }

    @Override
    void consumeAll(int intensityRate) {
        oil.consume(1);
        grease.consume(1);
        mun.consume(intensityRate*2);
        diesel.consume(intensityRate*180);
        grenade.consume(intensityRate*2);
    }
}
