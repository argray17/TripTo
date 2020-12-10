package com.redhawkride.controller;

import com.redhawkride.controller.lists.StudentsList;
import com.redhawkride.controller.lists.TripsList;
import com.redhawkride.controller.maps.StudentsMap;
import com.redhawkride.controller.maps.TripsMap;
import com.redhawkride.model.Student;
import com.redhawkride.model.Trip;
import com.redhawkride.model.locationhandling.RouteLog;
import com.redhawkride.model.moneyhandling.BankTransaction;
import com.redhawkride.model.moneyhandling.Money;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RedHawkRideController {
  private StudentsMap mapOfStudents;
  private TripsMap mapOfTrips;
  private StudentsList listOfAvailableDrivers, listOfDriversOnATrip;
  private TripsList listOfUnchargedTrips, listOfTripsInProgress;

  public RedHawkRideController() throws IOException, ParseException {
    setMapOfStudents();
    setMapOfTrips();
    setListOfUnchargedTrips();
    listOfAvailableDrivers = new StudentsList();
    listOfDriversOnATrip = new StudentsList();
    listOfTripsInProgress = new TripsList();
  }

  public void setMapOfStudents() throws IOException {
    File file = new File("src/main/java/com/redhawkride/data/Students.csv");
    mapOfStudents = new StudentsMap();
    mapOfStudents.loadFromFile(file);
  }

  public void setMapOfTrips() throws IOException, ParseException {
    File file = new File("src/main/java/com/redhawkride/data/Trips.csv");
    mapOfTrips = new TripsMap();
    mapOfTrips.loadFromFile(file, mapOfStudents);
  }

  public void setListOfUnchargedTrips() throws IOException {
    File file = new File("src/main/java/com/redhawkride/data/UnchargedTrips.csv");
    this.listOfUnchargedTrips = new TripsList();
    listOfUnchargedTrips.loadFromFile(file, mapOfTrips);
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

  public boolean addCreatedAccount(Student createdAccount) throws FileNotFoundException {
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

  public void processBalances() throws FileNotFoundException {
    ArrayList<Student> studentsToProcess = mapOfStudents.processStudentBalances();

    for (Student student : studentsToProcess) {
      BankTransaction transaction = new BankTransaction(student);
      processTransaction(transaction);
    }
  }

  private void processTransaction(BankTransaction transaction) throws FileNotFoundException {
    File file = new File("src/main/java/com/redhawkride/data/BankTransactions.csv");
    PrintWriter printWriter = new PrintWriter(file);
    StringBuilder stringBuilder = new StringBuilder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    String student = transaction.getStudent().getStudentID();
    String bankAccountNumber = transaction.getBankAccountNumber();
    String bankRoutingNumber = transaction.getRoutingNumber();
    String dateOfTransaction = dateFormat.format(transaction.getDateOfTransaction());
    String amountOfTransaction = String.valueOf(transaction.getAmountOfTransaction().getAmount().doubleValue());
    String initialBalance = String.valueOf(transaction.getInitialBalance().getAmount().doubleValue());
    String updatedBalance = String.valueOf(transaction.getUpdatedBalance().getAmount().doubleValue());

    stringBuilder.append(student + ",");
    stringBuilder.append(bankAccountNumber + ",");
    stringBuilder.append(bankRoutingNumber + ",");
    stringBuilder.append(dateOfTransaction + ",");
    stringBuilder.append(amountOfTransaction + ",");
    stringBuilder.append(initialBalance + ",");
    stringBuilder.append(updatedBalance);

    printWriter.print(stringBuilder.toString());
    printWriter.flush();
  }

  public Student findStudent(String studentID) {
    return mapOfStudents.findStudent(studentID);
  }

  public Trip findTrip(String tripID) {
    return mapOfTrips.findTrip((tripID));
  }
}
