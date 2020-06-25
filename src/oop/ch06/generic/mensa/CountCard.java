package oop.ch06.generic.mensa;

public class CountCard extends MensaCard {

    public CountCard(String key, Color color) {
        super(key, color);
        if (color != Color.red && color != Color.gray)
            throw new IllegalArgumentException("Invalid CountCard color " + color);
    }

    @Override
    public void pass(CashPoint cashPoint) {
        cashPoint.count(this);
    }
}
