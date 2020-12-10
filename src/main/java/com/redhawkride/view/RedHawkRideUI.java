package com.redhawkride.view;

import com.redhawkride.controller.RedHawkRideController;
import com.redhawkride.model.Student;
import com.redhawkride.model.Trip;
import com.redhawkride.model.locationhandling.Location;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RedHawkRideUI {
  private RedHawkRideController rHRController;
  private Student currentStudent;
  private Trip currentTrip;

  public RedHawkRideUI(RedHawkRideController rHRController) {
    this.rHRController = rHRController;
  }

  public void mainMenu() throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a number from the menu to continue. \n");
    System.out.println(
        "\t(1) Register as new user. \n\t(2) Continue as Rider. \n\t(3) Continue as Driver. "
            + "\n\t(4) Quit\nChoice: ");

    int choice = sc.nextInt();

    switch (choice) {
      case 1:
        newUser();
        mainMenu();
        break;
      case 2:
        rider();
        break;
      case 3:
        driver();
        break;
      case 4:
        System.out.println("You have quit the RedhawkRide System.");
        break;
    }
  }

  public void newUser() throws IOException {
    Scanner sc = new Scanner(System.in);
    Student student = new Student();

    System.out.println("Enter your First Name: ");
    String studentFirstName = sc.nextLine();
    student.setFirstName(studentFirstName);

    System.out.println("Enter your Last Name: ");
    String studentLastName = sc.nextLine();
    student.setLastName(studentLastName);

    System.out.println("Enter your id: ");
    String studentID = sc.nextLine();
    student.setStudentID(studentID);

    System.out.println("Enter a password: ");
    String studentPassword = sc.nextLine();
    student.setPassword(studentPassword);

    System.out.println("Enter your Bank account number: ");
    String accountNumber = sc.nextLine();
    student.setBankAccountNumber(accountNumber);

    System.out.println("Enter your routing number: ");
    String routingNumber = sc.nextLine();
    student.setBankRoutingNumber(routingNumber);

    rHRController.addCreatedAccount(student);
  }

  public void rider() throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter your studentID: ");
    String id = sc.nextLine();
    currentStudent = rHRController.findStudent(id);

    System.out.println(
        "\t(1) Provide your start and end location as (latitude, longitude) pairs \nto receive estimated cost for the ride"
            + " and request a ride. \n\t(2) View ride history. \n\t(3) Return to Main Menu. \nChoice: ");

    int choice = sc.nextInt();

    do {
      switch (choice) {
        case 1:
          Trip trip = new Trip();
          System.out.println("Enter your start location latitude: ");
          Float startLat = sc.nextFloat();

          System.out.println("Enter your start location longitude: ");
          Float startLon = sc.nextFloat();

          Location startLocation = new Location(startLat, startLon);
          trip.setStartLocation(startLocation);

          System.out.println("Enter your end location latitude: ");
          Float endLat = sc.nextFloat();

          System.out.println("Enter your end location longitude: ");
          Float endLon = sc.nextFloat();

          Location endLocation = new Location(endLat, endLon);
          trip.setEndLocation(endLocation);

          // estimate trip cost and display cost
          System.out.println(
              "Your estimated cost is: "
                  + trip.estimateTripCost(startLocation, endLocation));

          System.out.println("Do you want to request a ride for this trip? Y/N");
          char rideYN = sc.next().charAt(0);

          if (rideYN == 'Y' || rideYN == 'y') {
            currentTrip = trip;
            rHRController.requestTrip(currentTrip);
          }

          if (rideYN == 'N' || rideYN == 'n') {
            rider();
          }

          break;

        case 2:
          ArrayList<Trip> tripHistory = currentStudent.getTripHistory();
          for (int i = 0; i < tripHistory.size(); i++) {
            Trip tripTemp = tripHistory.get(i);
            System.out.println("Ride Request Info:");
            System.out.println("Name of Requestor: " + tripTemp.getRider().getFirstName());
            System.out.println("Start Location: " + tripTemp.getStartLocation());
            System.out.println("End Location: " + tripTemp.getEndLocation());
            System.out.println("Estimated Price: " + tripTemp.getEstimatedTripCost());

            System.out.println("Driver Info:");
            System.out.println("Driver: " + tripTemp.getDriver().getFirstName());

            System.out.println("Ride Info:");
            System.out.println(
                "'Start ride' time: "
                    + tripTemp.getStartTime()
                    + "@"
                    + tripTemp.getStartLocation());
            System.out.println(
                "'End ride' time: " + tripTemp.getEndTime() + "@" + tripTemp.getEndLocation());
            System.out.println("Final cost: " + tripTemp.getFinalTripCost());
          }
          break;
        case 3:
          mainMenu();
          break;
      }
    } while (choice != 3);
  }

  public void driver() throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter your studentID: ");
    String id = sc.nextLine();
    currentStudent = rHRController.findStudent(id);

    System.out.println(
        "\t(1) Start driving or End driving \n\t(2) Start ride or finish ride\n\t(3) Update location with latitude, longitude pair. "
            + "\n\t(4) Return to Main Menu.\nChoice: ");

    int choice = sc.nextInt();

    do {
      switch (choice) {
        case 1:
          System.out.println("Enter S for start driving. Enter E for end driving.");
          char drivingOption = sc.next().charAt(0);

          if (drivingOption == 'S' || drivingOption == 's')
            rHRController.setDriverStatus(currentStudent, true);

          if (drivingOption == 'E' || drivingOption == 'e')
            rHRController.setDriverStatus(currentStudent, false);
          break;

        case 2:
          System.out.println("Enter S for start ride. Enter F for finish ride.");
          char rideOption = sc.next().charAt(0);

          if (rideOption == 'S' || rideOption == 's') {
            rHRController.startTrip(currentTrip);
          }

          if (rideOption == 'F' || rideOption == 'f') {
            rHRController.endTrip(currentTrip);
            currentTrip.setFinalTripCost();
          }
          break;
        case 3:
          Location driverLoc = new Location(0, 0);
          System.out.println("Enter your location latitude: ");
          Float updateLat = sc.nextFloat();

          System.out.println("Enter your location longitude: ");
          Float updateLon = sc.nextFloat();

          Location updateLocation = new Location(updateLat, updateLon);

          break;
        case 4:
          mainMenu();
          break;
      }
    } while (choice != 4);
  }
}
