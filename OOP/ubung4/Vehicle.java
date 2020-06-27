package ubung4.logistics;

import ubung4.logistics.materials.Grease;
import ubung4.logistics.materials.Oil;
import ubung4.logistics.quantities.NeedCollector;
import ubung4.logistics.storage.IntStorage;

public abstract class Vehicle {

    public String name;
    public IntStorage oil;
    public IntStorage grease;

    public Vehicle(String name, int oil, int grease) {
        this.name = name;
        this.oil =new IntStorage(0,Oil.INSTANCE,oil);
        this.grease = new IntStorage(0, Grease.INSTANCE,grease);
    }

    abstract void reportNeeds(NeedCollector collector);

    abstract void fillUpAll();

    abstract void consumeAll(int intensityRate);
}
