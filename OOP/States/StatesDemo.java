package Beispiel;

public class StatesDemo {

    public static void main(String[] args) {
        States states = new States();
        System.out.println(states.changingState);
        states.changingState.changeState();
        System.out.println(states.changingState);
        states.changingState.changeState();
        System.out.println(states.changingState);
    }
}

