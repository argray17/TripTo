package com.tripto.model;

import com.tripto.model.moneyhandling.Balance;
import com.tripto.model.moneyhandling.Transaction;
import com.tripto.model.userdataencryption.BankAccountNumber;
import com.tripto.model.userdataencryption.BankRoutingNumber;
import com.tripto.model.userdataencryption.Password;
import java.util.ArrayList;

public class Account {
  private Student student;
  private Balance balance;
  private Password password;
  private BankAccountNumber bankAccountNumber;
  private BankRoutingNumber bankRoutingNumber;
  private ArrayList<Transaction> transactionHistory;
  private ArrayList<Trip> tripHistory;

  public Account() {}

  public Account(
      Student student,
      Balance balance,
      Password password,
      BankAccountNumber bankAccountNumber,
      BankRoutingNumber bankRoutingNumber,
      ArrayList<Transaction> transactionHistory,
      ArrayList<Trip> tripHistory) {
    this.student = student;
    this.balance = balance;
    this.password = password;
    this.bankAccountNumber = bankAccountNumber;
    this.bankRoutingNumber = bankRoutingNumber;
    this.transactionHistory = transactionHistory;
    this.tripHistory = tripHistory;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Balance getBalance() {
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }

  public Password getPassword() {
    return password;
  }

  public void setPassword(Password password) {
    this.password = password;
  }

  public BankAccountNumber getBankAccountNumber() {
    return bankAccountNumber;
  }

  public void setBankAccountNumber(BankAccountNumber bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  public BankRoutingNumber getBankRoutingNumber() {
    return bankRoutingNumber;
  }

  public void setBankRoutingNumber(BankRoutingNumber bankRoutingNumber) {
    this.bankRoutingNumber = bankRoutingNumber;
  }

  public ArrayList<Transaction> getTransactionHistory() {
    return transactionHistory;
  }

  public void setTransactionHistory(ArrayList<Transaction> transactionHistory) {
    this.transactionHistory = transactionHistory;
  }

  public ArrayList<Trip> getTripHistory() {
    return tripHistory;
  }

  public void setTripHistory(ArrayList<Trip> tripHistory) {
    this.tripHistory = tripHistory;
  }
}
