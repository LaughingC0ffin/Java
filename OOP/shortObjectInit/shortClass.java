package Bsp.shortObjectInit;

public class shortClass {

    public static void main(String[] args) {

        Inter inter = new Inter() {
            @Override
            public boolean giveBool() {
                return false;
            }

            @Override
            public String giveString() {
                return "hi";
            }

            @Override
            public int giveInt() {
                return 0;
            }
        };
    }
}
