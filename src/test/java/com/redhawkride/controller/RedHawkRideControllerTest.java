package com.redhawkride.controller;

import com.redhawkride.model.Student;
import com.redhawkride.model.Trip;
import com.redhawkride.model.locationhandling.Location;
import com.redhawkride.model.moneyhandling.AccountBalance;
import com.redhawkride.model.moneyhandling.Money;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;

class RedHawkRideControllerTest {
  RedHawkRideController redHawkRideController = new RedHawkRideController();

  RedHawkRideControllerTest() throws IOException, ParseException {}

  @Test
  void setDriverStatus() {}

  @Test
  void requestTrip() throws IOException {
      Trip trip = new Trip(
              redHawkRideController.findStudent("1"), redHawkRideController.findStudent("2"),
              "1234", new Location(1,2),
              new Location(3,4), new Date(), new Date(),
              new Money(new BigDecimal(15)), new Money( new BigDecimal(20))
      );

      redHawkRideController.getListOfAvailableDrivers().addStudent(redHawkRideController.findStudent("1"));
      redHawkRideController.requestTrip(trip);
      redHawkRideController.startTrip(trip);
      redHawkRideController.endTrip(trip);
  }

  @Test
  void startTrip() {}

  @Test
  void logTripProgress() {}

  @Test
  void endTrip() {}

  @Test
  void addCreatedAccount() throws IOException {
    Student student =
        new Student(
            "1",
            "admin",
            "Austin",
            "Gray",
            "1111",
            "2222",
            new AccountBalance(new Money(new BigDecimal(0))));

    redHawkRideController.addCreatedAccount(student);

    Student student1 =
        new Student(
            "2",
            "admin",
            "Blake",
            "Stroh",
            "1111",
            "2222",
            new AccountBalance(new Money(new BigDecimal(0))));

    redHawkRideController.addCreatedAccount(student1);

    Student student2 =
        new Student(
            "3",
            "admin",
            "Ryan",
            "Munie",
            "1111",
            "2222",
            new AccountBalance(new Money(new BigDecimal(0))));

    redHawkRideController.addCreatedAccount(student2);
  }

  @Test
  void processUnchargedTrips() {}

  @Test
  void processBalances() {}

  @Test
  void findStudent() {}

  @Test
  void findTrip() {}
}
