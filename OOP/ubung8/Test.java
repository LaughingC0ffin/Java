package ubung8;

import ubung8.timer.Timer;

public class Test {

    public static void main(String[] args) throws InterruptedException {
/*
        long startNanos = System.nanoTime();
        long startMillis = System.currentTimeMillis();
        System.out.println("Anfang");
        //10 sekunden
       // System.out.println(startNanos*Math.pow(10,-9));

        long timeInNanos = System.nanoTime() - startNanos;
        long timeInMillis = System.currentTimeMillis() - startMillis;
        System.out.println("time taken in nanos: " + timeInNanos);
        System.out.println("time taken in mills: " + timeInMillis);
        System.out.println("time in secs " + timeInMillis/1000);



        long start = System.currentTimeMillis();
        //long timeIn = System.currentTimeMillis() - start;
        while(System.currentTimeMillis()<start+1000){

          // timeIn = System.currentTimeMillis() - start;
            Thread.sleep(5000);

            //System.out.println("time in secs " + timeIn/1000);

        }*/


        Timer timer = new Timer();
        //timer.mode();
        System.out.println(timer.toString());
        timer.mode();
        timer.up();

        System.out.println(timer.toString());
        timer.start();/*
 */


    }
}
