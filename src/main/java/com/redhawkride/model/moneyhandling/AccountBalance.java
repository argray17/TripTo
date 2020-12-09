package com.redhawkride.model.moneyhandling;

import java.util.ArrayList;

public class AccountBalance {
  private Money currentBalance;
  private ArrayList<BankTransaction> transactionHistory;

  public AccountBalance(Money currentBalance) {
    this.currentBalance = currentBalance;
  }

  public Money getCurrentBalance() {
    return currentBalance;
  }

  public void addToBalance(Money amount) {
    currentBalance = new Money(currentBalance.getAmount().add(amount.getAmount()));
  }

  public void addTransaction(BankTransaction transaction) {
    transactionHistory.add(transaction);
  }
}
