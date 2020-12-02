package com.redhawkride.model.moneyhandling;

import com.redhawkride.model.Student;
import java.util.Date;

public class BankTransaction {
  private Student student;
  private String bankAccountNumber, bankRoutingNumber;
  private Date dateOfTransaction;
  private Money amountOfTransaction, initialBalance, updatedBalance;

  public BankTransaction(Student student) {
    this.student = student;
    this.bankAccountNumber = student.getBankAccountNumber();
    this.bankRoutingNumber = student.getBankRoutingNumber();
    this.dateOfTransaction = new Date();
    this.amountOfTransaction = student.getAccountBalance().getCurrentBalance();
    this.initialBalance = student.getAccountBalance().getCurrentBalance();
    this.updatedBalance =
        new Money(this.initialBalance.getAmount().add(this.amountOfTransaction.getAmount()));
  }

  public BankTransaction(Student student, Money amountOfTransaction) {
    this.student = student;
    this.bankAccountNumber = student.getBankAccountNumber();
    this.bankRoutingNumber = student.getBankRoutingNumber();
    this.dateOfTransaction = new Date();
    this.amountOfTransaction = amountOfTransaction;
    this.initialBalance = student.getAccountBalance().getCurrentBalance();
    this.updatedBalance =
        new Money(this.initialBalance.getAmount().add(this.amountOfTransaction.getAmount()));
  }

  public void process() {
    student.getAccountBalance().addTransaction(this);
    student.getAccountBalance().addToBalance(amountOfTransaction);
  }
}
