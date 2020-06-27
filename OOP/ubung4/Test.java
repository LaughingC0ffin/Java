package ubung4.logistics;

import ubung4.logistics.materials.LiterDiesel;

public class Test {

    public static void main(String[] args) {
        Tank Leo2 = new Tank("Leo2",2,1000,2);
        Truck Dingo = new Truck("Dingo",100,1);
        Manager manager = new Manager();
        manager.addVehicle(Leo2);
        manager.addVehicle(Dingo);
        manager.showNeed(LiterDiesel.INSTANCE);
    }
}
