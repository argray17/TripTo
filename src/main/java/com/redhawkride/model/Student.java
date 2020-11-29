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

  String getStudentID(){
    return studentID;
  }

  String getPassword(){
    return password;
  }

  String getFirstName(){
    return firstName;
  }

  String getLastName(){
    return lastName;
  }

  String getPhoneNumber(){
    return phoneNumber;
  }

  String getAddress(){
    return address;
  }

  String getBankAccountNumber(){
    return bankAccountNumber;
  }

  String getBankRoutingNumber(){
    return bankRoutingNumber;
  }

  int getDriverStatus(){
    return driverStatus;
  }

  ArrayList<Trip> gettripHistory(){
    return tripHistory;
  }

  public void setStudentID(String ID){
    this.studentID = ID;
  }

  public void setPassword(String key){
    this.password = key;
  }

  public void setFirstName(String fname){
    this.firstName = fname;
  }

  public void setLastName(String lname){
    this.lastName = lname;
  }

  public void setPhoneNumber(String pNumber){
    this.phoneNumber = pNumber;
  }

  public void setAddress(String addy){
    this.address = addy;
  }

  public void setBankAccountNumber(String actNumber){
    this.bankAccountNumber = actNumber;
  }

  public void setBankRoutingNumber(String routNumber){
    this.bankRoutingNumber = routNumber;
  }

  public void setRideHistory(ArrayList<Trip> historyList){
    this.tripHistory = historyList;
  }

  public void setIsAvailable(int avaibleFlag){
    this.driverStatus = avaibleFlag;
  }

  public void addTrip(Trip x){
    tripHistory.add(x); //insert trip into trip history array
  }
}
