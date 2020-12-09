package com.redhawkride.model;

import com.redhawkride.model.locationhandling.Location;
import com.redhawkride.model.moneyhandling.AccountBalance;
import com.redhawkride.model.moneyhandling.Money;
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
  private AccountBalance accountBalance;
  private ArrayList<Trip> tripHistory;
  private boolean isAvailable = false;

  public Student() {}
  ;

  public Student(
      String studentID,
      String password,
      String firstName,
      String lastName,
      String phoneNumber,
      String address,
      String bankAccountNumber,
      String bankRoutingNumber,
      AccountBalance accountBalance) {
    this.studentID = studentID;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.bankAccountNumber = bankAccountNumber;
    this.bankRoutingNumber = bankRoutingNumber;
    this.accountBalance = accountBalance;
  }

  public String getStudentID() {
    return studentID;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public String getBankAccountNumber() {
    return bankAccountNumber;
  }

  public String getBankRoutingNumber() {
    return bankRoutingNumber;
  }

  ArrayList<Trip> getTripHistory() {
    return tripHistory;
  }

  public void setStudentID(String ID) {
    this.studentID = ID;
  }

  public void setPassword(String key) {
    this.password = key;
  }

  public void setFirstName(String fname) {
    this.firstName = fname;
  }

  public void setLastName(String lname) {
    this.lastName = lname;
  }

  public void setPhoneNumber(String pNumber) {
    this.phoneNumber = pNumber;
  }

  public void setAddress(String addy) {
    this.address = addy;
  }

  public void setBankAccountNumber(String actNumber) {
    this.bankAccountNumber = actNumber;
  }

  public void setBankRoutingNumber(String routNumber) {
    this.bankRoutingNumber = routNumber;
  }

  public void setRideHistory(ArrayList<Trip> historyList) {
    this.tripHistory = historyList;
  }

  public void addTrip(Trip x) {
    tripHistory.add(x); // insert trip into trip history array
  }

  public void alertDriver(Trip trip) {
    Student rider = trip.getRider();
    String driverMsg = "Pick up for " + rider.getFirstName();
  }

  public void alertRider(Trip trip) {
    Student driver = trip.getDriver();
    String riderMsg = "Pick up by " + driver.getFirstName();
  }

  public void beginTracking() {}

  public boolean closerThan(Student student) {
    return true;
  }

  public void credit(Money amount) {}

  public void debit(Money amount) {}

  public AccountBalance getAccountBalance() {
    return accountBalance;
  }

  public Location getCurrentLocation() {
    return null;
  }

  public void setIsAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
}
