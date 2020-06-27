package ubung4.logistics;

import ubung4.logistics.quantities.FloatUnit;
import ubung4.logistics.quantities.IntUnit;
import ubung4.logistics.quantities.NeedCollector;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    /*
    void addVehicle(Vehicle v) - f¨ugt das angegebene Fahrzeug hinzu.
 void fillUpVehicles() - f¨ullt die Versorgungsgutbest¨ande aller gelisteter Fahrzeuge bis zur Maximalmenge
auf.
 void logTick(int intensityRate) - reduziert die Versorgungsgutbest¨ande aller gelisteter
Fahrzeuge mit der angegebenen Intensit¨atsrate.3
 void showOverallNeed() - druckt den gesamten Bedarf an den jeweiligen Versorgungsg¨utern auf
die Konsole.
 void showNeed(IntUnit unit) - druckt f¨ur jedes gelistete Fahrzeug dessen Bedarf an dem Versorgungsgut,
dessen Einheit als Parameter ¨ubergebenen wurde, auf der Konsole aus. Fahrzeuge, die daran
keinen Bedarf haben, sollen nicht gedruckt werden.
 void showNeed(FloatUnit unit) - ¨aquivalent.
     */

    public List<Vehicle> fahrzeuge = new ArrayList<Vehicle>();

    public void addVehicle(Vehicle v){
        fahrzeuge.add(v);
    }

    public void logTick(int intensityRate){
        for(Vehicle vehicle : fahrzeuge){
            vehicle.consumeAll(intensityRate);
        }
    }

    public void showOverallNeed(){
        NeedCollector collector = new NeedCollector();
        for(Vehicle vehicle : fahrzeuge){
            vehicle.reportNeeds(collector);
        }
        collector.show();
    }

    public void showNeed(IntUnit unit){

        System.out.println("Vehicles needing " + unit.toString());
        NeedCollector collector = new NeedCollector();
        for(Vehicle vehicle : fahrzeuge){
            int tmp = 0;


            tmp= collector.getNeed(unit);

            vehicle.reportNeeds(collector);
            float tmp2= collector.getNeed(unit) - tmp;

            System.out.println( vehicle.name+"  " + tmp2 + " " + unit.toString());
        }


    }

    public void showNeed(FloatUnit unit){
        System.out.println("Vehicles needing " + unit.toString());
        NeedCollector collector = new NeedCollector();
        for(Vehicle vehicle : fahrzeuge){
            float tmp = 0;
            tmp= collector.getNeed(unit);
            vehicle.reportNeeds(collector);
            float tmp2= collector.getNeed(unit) - tmp;
            System.out.println( vehicle.name+"  " + tmp2 + " " + unit.toString());
        }

    }

}
