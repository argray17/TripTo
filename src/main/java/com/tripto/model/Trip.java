package com.tripto.model;

import com.tripto.model.moneyhandling.Money;
import java.util.Date;

public class Trip {
  private Student driver, rider;
  private String tripID;
  private Location beginLocation, endLocation;
  private Date beginTime, endTime;
  private Money estimatedTripCost, actualTripCost;
  private boolean tripEnded;

  public Trip() {}

  public Trip(
      Student driver,
      Student rider,
      String tripID,
      Location beginLocation,
      Location endLocation,
      Date beginTime,
      Date endTime,
      Money estimatedTripCost,
      Money actualTripCost,
      boolean tripEnded) {
    this.driver = driver;
    this.rider = rider;
    this.tripID = tripID;
    this.beginLocation = beginLocation;
    this.endLocation = endLocation;
    this.beginTime = beginTime;
    this.endTime = endTime;
    this.estimatedTripCost = estimatedTripCost;
    this.actualTripCost = actualTripCost;
    this.tripEnded = tripEnded;
  }

  public Student getDriver() {
    return driver;
  }

  public void setDriver(Student driver) {
    this.driver = driver;
  }

  public Student getRider() {
    return rider;
  }

  public void setRider(Student rider) {
    this.rider = rider;
  }

  public String getTripID() {
    return tripID;
  }

  public void setTripID(String tripID) {
    this.tripID = tripID;
  }

  public Location getBeginLocation() {
    return beginLocation;
  }

  public void setBeginLocation(Location beginLocation) {
    this.beginLocation = beginLocation;
  }

  public Location getEndLocation() {
    return endLocation;
  }

  public void setEndLocation(Location endLocation) {
    this.endLocation = endLocation;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Money getEstimatedTripCost() {
    return estimatedTripCost;
  }

  public void setEstimatedTripCost(Money estimatedTripCost) {
    this.estimatedTripCost = estimatedTripCost;
  }

  public Money getActualTripCost() {
    return actualTripCost;
  }

  public void setActualTripCost(Money actualTripCost) {
    this.actualTripCost = actualTripCost;
  }

  public boolean isTripEnded() {
    return tripEnded;
  }

  public void setTripEnded(boolean tripEnded) {
    this.tripEnded = tripEnded;
  }
}
