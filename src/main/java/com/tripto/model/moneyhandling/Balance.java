package com.tripto.model.moneyhandling;

public class Balance {
  private Money currentBalance;

  public Balance() {}

  public Balance(Money currentBalance) {
    this.currentBalance = currentBalance;
  }

  public void creditBalance() {}

  public void debitBalance() {}

  public Money getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(Money currentBalance) {
    this.currentBalance = currentBalance;
  }
}
