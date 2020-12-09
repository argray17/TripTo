package com.redhawkride.controller;

import com.redhawkride.controller.filehandling.LoadFromFile;
import com.redhawkride.controller.filehandling.RecordToFile;
import com.redhawkride.controller.lists.StudentsList;
import com.redhawkride.controller.lists.TripsList;
import com.redhawkride.controller.maps.StudentsMap;
import com.redhawkride.controller.maps.TripsMap;
import com.redhawkride.model.Student;
import com.redhawkride.model.Trip;
import com.redhawkride.model.locationhandling.RouteLog;
import com.redhawkride.model.moneyhandling.BankTransaction;
import com.redhawkride.model.moneyhandling.Money;
import java.util.ArrayList;
import java.util.HashMap;

public class RedHawkRideController {
  private StudentsMap mapOfStudents;
  private TripsMap mapOfTrips;
  private StudentsList listOfAvailableDrivers, listOfDriversOnATrip;
  private TripsList listOfUnchargedTrips, listOfTripsInProgress;

  public RedHawkRideController() {
    setMapOfStudents();
    setMapOfTrips();
    setListOfUnchargedTrips();
    listOfAvailableDrivers = new StudentsList();
    listOfDriversOnATrip = new StudentsList();
    listOfTripsInProgress = new TripsList();
  }

  public void setMapOfStudents() {
    HashMap<String, Student> mapOfStudents = LoadFromFile.studentMap();
    this.mapOfStudents = new StudentsMap(mapOfStudents);
  }

  public void setMapOfTrips() {
    HashMap<String, Trip> mapOfTrips = LoadFromFile.tripsMap();
    this.mapOfTrips = new TripsMap(mapOfTrips);
  }

  public void setListOfUnchargedTrips() {
    ArrayList<Trip> listOfUnchargedTrips = LoadFromFile.unchargedTripsList();
    this.listOfUnchargedTrips = new TripsList(listOfUnchargedTrips);
  }

  public void setDriverStatus(Student student, boolean isAvailable) {
    student.setIsAvailable(isAvailable);
  }

  private Student findNearestDriver(Trip trip) {
    return listOfAvailableDrivers.findNearest(trip.getStartLocation());
  }

  public void requestTrip(Trip trip) {
    Student driver = findNearestDriver(trip);

    if (driver == null) {
      System.out.println("No Available Drivers.");
      return;
    }

    Student rider = trip.getRider();
    trip.setDriver(driver);
    driver.alertDriver(trip);
    driver.beginTracking();
    rider.alertRider(trip);

    listOfAvailableDrivers.removeStudent(driver);
    listOfDriversOnATrip.addStudent(driver);
    listOfTripsInProgress.addTrip(trip);
  }

  public void startTrip(Trip trip) {
    trip.setStartTime();
    trip.setRouteLog(new RouteLog());
    trip.logLocation(false);

    Student driver = trip.getDriver();
    Student rider = trip.getRider();

    mapOfTrips.addTrip(trip);
    driver.addTrip(trip);
    rider.addTrip(trip);
  }

  public void logTripProgress(Trip trip) {
    trip.logLocation(false);
  }

  public void endTrip(Trip trip) {
    trip.setEndTime();
    trip.logLocation(true);
    trip.setFinalTripCost();

    Student driver = trip.getDriver();

    listOfUnchargedTrips.addTrip(trip);
    listOfTripsInProgress.removeTrip(trip);
    listOfDriversOnATrip.removeStudent(driver);
    listOfAvailableDrivers.addStudent(driver);
  }

  public boolean addCreatedAccount(Student createdAccount) {
    return this.mapOfStudents.addStudent(createdAccount);
  }

  public boolean validateStudentID(String studentID) {
    return this.mapOfStudents.validateStudentID(studentID);
  }

  public void processUnchargedTrips() {
    ArrayList<Trip> unchargedTrips = listOfUnchargedTrips.getList();

    for (Trip trip : unchargedTrips) {
      chargeAccounts(trip);
      listOfUnchargedTrips.removeTrip(trip);
    }
  }

  private void chargeAccounts(Trip trip) {
    Student driver = trip.getDriver();
    Student rider = trip.getRider();
    Money amount = trip.getFinalTripCost();

    driver.credit(calcDriverPay(amount));
    rider.debit(amount);
  }

  private Money calcDriverPay(Money amount) {
    return amount;
  }

  public void processBalances() {
    ArrayList<Student> studentsToProcess = mapOfStudents.processStudentBalances();

    for (Student student : studentsToProcess) {
      BankTransaction transaction = new BankTransaction(student);
      processTransaction(transaction);
    }
  }

  private void processTransaction(BankTransaction transaction) {
    RecordToFile.bankTransaction(transaction);
  }

  public Student findStudent(String studentID) {
    return mapOfStudents.findStudent(studentID);
  }
}
