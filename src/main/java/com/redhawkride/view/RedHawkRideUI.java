package com.redhawkride.view;

import com.redhawkride.controller.RedHawkRideController;
import com.redhawkride.model.Student;
import com.redhawkride.model.Trip;
import com.redhawkride.model.locationhandling.Location;
import java.util.Scanner;

public class RedHawkRideUI {
  private RedHawkRideController rHRController;
  private Student currentStudent;
  private Trip currentTrip;

  public RedHawkRideUI(RedHawkRideController rHRController) {
    this.rHRController = rHRController;
  }

  Scanner in = new Scanner(System.in);

  public void mainMenu() {

    System.out.println("Enter a number from the menu to continue. \n");
    System.out.println(
        "\t(1) Register as new user. \n\t(2) Continue as Rider. \n\t(3) Continue as Driver. "
            + "\n\t(4) Quit\nChoice: ");

    int choice = in.nextInt();

    switch (choice) {
      case 1:
        newUser();
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

  public void newUser() {

    System.out.println("Enter your id: ");
    String studentID = in.nextLine();

    System.out.println("Enter your Bank account number: ");
    String accountNumber = in.nextLine();

    System.out.println("Enter your routing number: ");
    String routingNumber = in.nextLine();

    rHRController.validateStudentID(studentID);

    Student student = new Student();

    rHRController.addCreatedAccount(student);
  }

  public void rider() {
    System.out.println(
        "\t(1) Provide your start and end location as (latitude, longitude) pairs to receive estimated cost for the ride"
            + " \n\t(2) Request a ride. \n\t(3) View ride history. \n\t(4) Return to Main Menu. \nChoice: ");

    int choice = in.nextInt();

    do {
      switch (choice) {
        case 1:
          Trip trip = new Trip();
          System.out.println("Enter your start location latitude: \n");
          Float startLat = in.nextFloat();

          System.out.println("Enter your start location longitude: \n");
          Float startLon = in.nextFloat();

          Location startLocation = new Location(startLat, startLon);

          System.out.println("Enter your end location latitude: \n");
          Float endLat = in.nextFloat();

          System.out.println("Enter your end location longitude: \n");
          Float endLon = in.nextFloat();

          Location endLocation = new Location(endLat, endLon);
          // estimate trip cost and display cost
          System.out.println(
              "Your estimated cost is: "
                  + currentTrip.estimateTripCost(startLocation, endLocation));

          rHRController.requestTrip(currentTrip);
          break;

        case 2:
          rHRController.requestTrip(currentTrip);
          break;
        case 3:
          // no clue where history is;
          break;
        case 4:
          mainMenu();
          break;
      }
    } while (choice != 4);
  }

  public void driver() {
    System.out.println(
        "\t(1) Start driving or End driving \n\t(2) Start ride or finish ride\n\t(3) Update location with latitude, longitude pair. "
            + "\n\t(4) Return to Main Menu.\nChoice: ");

    int choice = in.nextInt();

    do {
      switch (choice) {
        case 1:
          System.out.println("Enter S for start driving. Enter E for end driving.\n");
          char drivingOption = in.next().charAt(0);

          if (drivingOption == 'S' || drivingOption == 's')
            rHRController.setDriverStatus(currentStudent, true);

          if (drivingOption == 'E' || drivingOption == 'e')
            rHRController.setDriverStatus(currentStudent, false);
          break;

        case 2:
          System.out.println("Enter S for start ride. Enter F for finish ride.\n");
          char rideOption = in.next().charAt(0);

          if (rideOption == 'S' || rideOption == 's') {
            rHRController.startTrip(currentTrip);
          }

          if (rideOption == 'F' || rideOption == 'f') {
            rHRController.endTrip(currentTrip);
          }

          break;
        case 3:
          Location driverLoc = new Location(0, 0);
          System.out.println("Enter your location latitude: \n");
          Float updateLat = in.nextFloat();

          System.out.println("Enter your location longitude: \n");
          Float updateLon = in.nextFloat();

          Location updateLocation = new Location(updateLat, updateLon);
          break;
        case 4:
          mainMenu();
          break;
      }
    } while (choice != 4);
  }
}
