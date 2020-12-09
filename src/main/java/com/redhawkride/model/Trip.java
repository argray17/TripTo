package com.redhawkride.model;

import com.redhawkride.model.locationhandling.Location;
import com.redhawkride.model.locationhandling.RouteLog;
import com.redhawkride.model.moneyhandling.Money;
import java.math.BigDecimal;
import java.util.Date;

public class Trip {
  private Money estimatedTripCost, finalTripCost;
  private Student driver, rider;
  private String tripID;
  private Location startLocation, endLocation;
  private Date startTime, endTime;
  private RouteLog routeLog;

  public Trip() {}

  public Trip(
      Student driver,
      Student rider,
      String tripID,
      Location startLocation,
      Location endLocation,
      Date startTime,
      Date endTime,
      Money estimatedTripCost,
      Money finalTripCost) {
    this.driver = driver;
    this.rider = rider;
    this.tripID = tripID;
    this.startLocation = startLocation;
    this.endLocation = endLocation;
    this.startTime = startTime;
    this.endTime = endTime;
    this.estimatedTripCost = estimatedTripCost;
    this.finalTripCost = finalTripCost;
  }

  public Student getDriver() {
    return driver;
  }

  public Student getRider() {
    return rider;
  }

  public Money getTripCost() {
    return finalTripCost;
  }

  public Location getStartLocation() {
    return startLocation;
  }

  public Location getEndLocation() {
    return endLocation;
  }

  public Date getStartTime() {
    return startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setDriver(Student driver) {
    this.driver = driver;
  }

  public void setRider(Student rider) {
    this.rider = rider;
  }

  public void setStartLocation(Location startLocation) {
    this.startLocation = startLocation;
  }

  public void setEndLocation(Location endLocation) {
    this.endLocation = endLocation;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public static double getDistance(Location startLocation, Location endLocation) {
    double latitudeBegin = startLocation.getLatitude(); // idk how we are handling lon/lat pairs
    double longitudeBegin = startLocation.getLongitude();

    double latitudeEnd = endLocation.getLatitude();
    double longitudeEnd = endLocation.getLongitude();

    // distance between latitudes and longitudes
    double dLatitude = Math.toRadians(latitudeEnd - latitudeBegin);
    double dLongitude = Math.toRadians(longitudeEnd - longitudeBegin);

    // convert to radians
    latitudeBegin = Math.toRadians(latitudeBegin);
    latitudeEnd = Math.toRadians(longitudeEnd);

    // apply formula
    double a =
        Math.pow(Math.sin(dLatitude / 2), 2)
            + Math.pow(Math.sin(dLongitude / 2), 2)
                * Math.cos(latitudeBegin)
                * Math.cos(latitudeEnd);
    double rad = 6371;
    double c = 2 * Math.asin(Math.sqrt(a));
    double distance = rad * c; // distance in Km

    distance = distance * 0.621371; // convert fromm Km to Mi

    return distance;
  }

  public Money estimateTripCost(Location beginLocation, Location endLocation) {
    estimatedTripCost = new Money(new BigDecimal(1.25 * getDistance(beginLocation, endLocation)));
    return estimatedTripCost;
  }

  public void calcTripCost() {
    long time =
        ((endTime.getTime() - startTime.getTime()) / 60000)
            % 60; // time difference converted from milliseconds to min
    finalTripCost =
        new Money(new BigDecimal(0.25 * time + 0.5 * getDistance(startLocation, endLocation)));

  }

  public String getTripID() {
    return tripID;
  }

  public void setTripID(String tripID) {
    this.tripID = tripID;
  }

  public void setFinalTripCost() {
    calcTripCost();
  }

  public void setStartTime() {}
  public void setEndTime() {}

  public Money getFinalTripCost() {
    return finalTripCost;
  }

  public void setRouteLog(RouteLog routeLog) {}

  public void logLocation(boolean atEndOfTrip) {
    routeLog.addEvent(driver.getCurrentLocation(), new Date(), atEndOfTrip);
  }

  public Money getEstimatedTripCost() {
    return this.estimatedTripCost;
  }
}
