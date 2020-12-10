package com.redhawkride.controller;

import com.redhawkride.model.Student;
import com.redhawkride.model.moneyhandling.AccountBalance;
import com.redhawkride.model.moneyhandling.Money;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import org.junit.jupiter.api.Test;

class RedHawkRideControllerTest {
  RedHawkRideController redHawkRideController = new RedHawkRideController();

  RedHawkRideControllerTest() throws IOException, ParseException {}

  @Test
  void setDriverStatus() {}

  @Test
  void requestTrip() {}

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
