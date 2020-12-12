package com.tripto.model.moneyhandling;

import com.tripto.model.Account;
import java.util.Date;

public class Transaction {
  private Account studentAccount;
  private Money beginBalance, endBalance, amountOfTransaction;
  private String studentBankAccountNumber, studentBankRoutingNumber, transactionID;
  private Date timeOfTransaction;
  private boolean transactionApproved;

  public Transaction() {}

  public Transaction(
      Account studentAccount,
      Money beginBalance,
      Money endBalance,
      Money amountOfTransaction,
      String studentBankAccountNumber,
      String studentBankRoutingNumber,
      String transactionID,
      Date timeOfTransaction,
      boolean transactionApproved) {
    this.studentAccount = studentAccount;
    this.beginBalance = beginBalance;
    this.endBalance = endBalance;
    this.amountOfTransaction = amountOfTransaction;
    this.studentBankAccountNumber = studentBankAccountNumber;
    this.studentBankRoutingNumber = studentBankRoutingNumber;
    this.transactionID = transactionID;
    this.timeOfTransaction = timeOfTransaction;
    this.transactionApproved = transactionApproved;
  }

  public Account getStudentAccount() {
    return studentAccount;
  }

  public void setStudentAccount(Account studentAccount) {
    this.studentAccount = studentAccount;
  }

  public Money getBeginBalance() {
    return beginBalance;
  }

  public void setBeginBalance(Money beginBalance) {
    this.beginBalance = beginBalance;
  }

  public Money getEndBalance() {
    return endBalance;
  }

  public void setEndBalance(Money endBalance) {
    this.endBalance = endBalance;
  }

  public Money getAmountOfTransaction() {
    return amountOfTransaction;
  }

  public void setAmountOfTransaction(Money amountOfTransaction) {
    this.amountOfTransaction = amountOfTransaction;
  }

  public String getStudentBankAccountNumber() {
    return studentBankAccountNumber;
  }

  public void setStudentBankAccountNumber(String studentBankAccountNumber) {
    this.studentBankAccountNumber = studentBankAccountNumber;
  }

  public String getStudentBankRoutingNumber() {
    return studentBankRoutingNumber;
  }

  public void setStudentBankRoutingNumber(String studentBankRoutingNumber) {
    this.studentBankRoutingNumber = studentBankRoutingNumber;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }

  public Date getTimeOfTransaction() {
    return timeOfTransaction;
  }

  public void setTimeOfTransaction(Date timeOfTransaction) {
    this.timeOfTransaction = timeOfTransaction;
  }

  public boolean isTransactionApproved() {
    return transactionApproved;
  }

  public void setTransactionApproved(boolean transactionApproved) {
    this.transactionApproved = transactionApproved;
  }
}
