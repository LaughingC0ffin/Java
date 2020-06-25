package oop.ch06.generic.mensa;

import oop.ch06.generic.secured.AuthorizationException;
import oop.ch06.generic.secured.SecurityClient;

public class CashPoint implements SecurityClient {
    private final int password;
    public final String name;
    private final AccountManagement accountMgmt;
    private int counter;
    private int cents;

    public CashPoint(String name, int password, AccountManagement accountMgmt) {
        this.name = name;
        this.password = password;
        this.accountMgmt = accountMgmt;
    }

    public int challengeResponse(int challenge) {
        return challenge ^ password;
    }

    @Override
    public String toString() {
        return "Cash point " + name + " (" + getCounter() + " meals, " + getCents() + " cents charged)";
    }

    public int getCounter() {
        return counter;
    }

    public int getCents() {
        return cents;
    }

    private int getPrice(Color color) {
        switch (color) {
            case green:
                return 267;
            case blue:
                return 357;
            case white:
                return 495;
            default:
                return 0;
        }
    }

    void count(MensaCard card) {
        counter++;
    }

    void charge(CashCard cashCard) throws AuthorizationException, RejectedException {
        final int price = getPrice(cashCard.color);
        if (cashCard.getBalance() < price)
            throw new RejectedException("insufficient payment");
        cashCard.charge(this, price);
        count(cashCard);
        cents += price;
    }

    void charge(AccountCard accountCard) throws RejectedException, AuthorizationException {
        final int price = getPrice(accountCard.color);
        System.out.println("Charging " + price + " cents on account " + accountCard.getAccount());
        accountMgmt.pay(accountCard, price, this);
        cents += price;
    }
}
