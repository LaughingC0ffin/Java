package oop.ch06.generic.mensa;

import oop.ch03.cards.NumberedCard;
import oop.ch06.generic.secured.AuthorizationException;

public abstract class MensaCard extends NumberedCard {
    public final Color color;

    public MensaCard(String key, Color color) {
        super(key);
        this.color = color;
    }

    @Override
    public String toString() {
        return color + " card " + number + " (" + key + ")";
    }

    public abstract void pass(CashPoint cashPoint) throws RejectedException, AuthorizationException;
}