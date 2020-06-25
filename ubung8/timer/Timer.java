package ubung8.timer;


public class Timer {

    private int time = 0;
    private long endTime=0;
    private State state ;

    public Timer() {
        this.state=setSec;
    }

    @Override
    public String toString() {
        return "" + time + "  " + state.toString() + " endtime: " + endTime;
    }

    public void tick() throws InterruptedException {
    }

    public void up() {
        state.up();
    }

    public void down() {
        state.down();
    }

    public void mode() {
        state.mode();
    }

    public void start() throws InterruptedException {
        state.start();
    }

    public boolean isBeeping() {
        return state.isBeeping();
    }

    public boolean isRunning() {
        return state.isRunning();
    }


    private final State setSec =new State() {

        @Override
        public void start() throws InterruptedException {
            state = countdown;
            countdown.tick();
        }

        @Override
        public void up(){
            endTime+=1;
        }

        @Override
        public void down(){
            endTime-=1;
            if(endTime<0) endTime=0;
        }

        @Override
        public void mode(){
            state=setMin;
        }

        public String toString(){
            return "Setsec";
        }
    };

    private final State setMin=new State(){

        @Override
        public void up(){
            endTime+=60;
        }

        @Override
        public void down(){
            endTime-=60;
            if(endTime<0) endTime=0;
        }

        @Override
        public void start() throws InterruptedException {
            state=countdown;
            countdown.tick();
        }

        @Override
        public void mode(){
            state=setMin;
        }

        public String toString(){
            return "Setmin";
        }
    };

    private final State countdown = new State(){


        @Override
        public void tick() throws InterruptedException {

            while(state==countdown&&time<=endTime){
                Thread.sleep(997);
                time+=1;
                System.out.println(time +" seconds");
                if(time==endTime){
                    state=ring;
                    state.tick();
                }
            }

        }

        @Override
        public void start(){
            state=setSec;
        }

        @Override
        public boolean isRunning(){
            return true;
        }

        public String toString(){
            return "countdown";
        }
    };

    private final State ring =new State(){

        private int ticker = 0;

        @Override
        public boolean isBeeping(){
            return true;
        }

        @Override
        public boolean isRunning(){
            return true;
        }

        @Override
        public void tick() throws InterruptedException {
            while(ticker<5){
                    Thread.sleep(1000);
                    ticker+=1;
                    System.out.println("RING");
                }
            state = setSec;
            ticker=0;
        }

        public String toString(){
            return "ring";
        }
    };

    private abstract class State{
        public void tick() throws InterruptedException {}

        public void up(){}

        public void down(){}

        public void mode(){}

        public void start() throws InterruptedException {}

        public boolean isBeeping(){return false;}

        public boolean isRunning(){
            return false;
        }
    };

}
