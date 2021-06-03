package Beispiel;

public class States {

    public State state1 = new State() {
        @Override
        public void changeState() {
            changingState = state2;
        }

        @Override
        public String toString() {
            return "state1";
        }
    };

    public State state2 = new State() {
        @Override
        public void changeState() {
            changingState = state1;
        }

        @Override
        public String toString() {
            return "state2";
        }
    };

    public State changingState = state1;
}
