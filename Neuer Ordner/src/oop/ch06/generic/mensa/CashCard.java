package oop.ch06.generic.mensa;

import oop.ch06.generic.secured.AuthorizationException;
import oop.ch06.generic.secured.SecuredContent;

public class CashCard extends MensaCard {
  private final SecuredContent<Integer> balance;

  public CashCard(String key, Color color, int password) {
    super(key, color);
    balance = new SecuredContent<>(password, 0);
    if (color != Color.blue && color != Color.green)
      throw new IllegalArgumentException("Invalid CashCard color " + color);
  }

  public int getBalance() {
    return balance.getContent();
  }

  void deposit(VendingMachine client, int cents)
          throws AuthorizationException {
    if (cents <= 0)
      throw new IllegalArgumentException("Non-positive deposit");
    final int newBalance = getBalance() + cents;
    balance.setContent(client, newBalance);
  }

  void charge(CashPoint client, int cents) throws AuthorizationException {
    if (cents < 0)
      throw new IllegalArgumentException("Negative charge");
    balance.setContent(client, getBalance() - cents);
  }

  @Override
  public String toString() {
    return super.toString() + " with " + balance.getContent() + " cents";
  }

  @Override
  public void pass(CashPoint cashPoint) throws AuthorizationException, RejectedException {
    cashPoint.charge(this);
  }
}
