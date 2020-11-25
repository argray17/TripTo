package com.redhawkride.model.moneyhandling;

import com.redhawkride.model.Student;
import java.util.Date;

public class BankTransaction {
  private Student student;
  private String bankAccountNumber, bankRoutingNumber;
  private Date dateOfTransaction;
  private Money amountOfTransaction, initialBalance, updatedBalance;
  private boolean isCredit, successfulProcess;
}
