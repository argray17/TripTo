package com.redhawkride.model;

import com.redhawkride.model.moneyhandling.AccountBalance;
import java.util.ArrayList;

public class Student {
  private String studentID,
      password,
      firstName,
      lastName,
      phoneNumber,
      address,
      bankAccountNumber,
      bankRoutingNumber;
  private int driverStatus,
      riderStatus; // 0 - inactive, 1 - waiting for ride/driver available, 2 - ride in progress
  private AccountBalance accountBalance;
  private ArrayList<Trip> tripHistory;
}
