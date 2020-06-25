package oop.ch06.generic.mensa;

import oop.ch06.generic.secured.AuthorizationException;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class AccountManagement {
    private final int password;
    public final String name;
    private final Random random = new Random();
    private final Map<String, Integer> accounts = new TreeMap<>();

    public AccountManagement(int password, String name) {
        this.password = password;
        this.name = name;
    }

    public void deposit(String account, int euros) {
        final int amount = accounts.getOrDefault(account, 0);
        accounts.put(account, amount + 100 * euros);
    }

    public void pay(AccountCard card, int price, CashPoint cashPoint) throws RejectedException, AuthorizationException {
        final int amount = accounts.getOrDefault(card.getAccount(), 0);
        final int challenge = nextChallenge();
        if (cashPoint.challengeResponse(challenge) != requiredResponse(challenge))
            throw new AuthorizationException(cashPoint + " is not authorized to access accounts on " + name);
        if (amount < price)
            throw new RejectedException(card + " bounced");
        accounts.put(card.getAccount(), amount - price);
    }

    private int nextChallenge() {
        return random.nextInt();
    }

    private int requiredResponse(int challenge) {
        return challenge ^ password;
    }

    @Override
    public String toString() {
        return "Account Management " + name + " " + accounts;
    }
}
