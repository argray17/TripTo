package com.tripto.controller;

import com.tripto.model.Account;
import com.tripto.model.Location;
import com.tripto.model.Student;
import com.tripto.model.Trip;
import com.tripto.model.moneyhandling.Balance;
import com.tripto.model.moneyhandling.Money;
import com.tripto.model.userdataencryption.BankAccountNumber;
import com.tripto.model.userdataencryption.BankRoutingNumber;
import com.tripto.model.userdataencryption.Password;

import java.math.BigDecimal;

import java.sql.*;
import java.util.ArrayList;

public class TripToController {

    public Trip getTripFromDatabase(String id) {
        try {
            Connection connection;
            Statement statement;
            ResultSet resultSet;

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trips",
                    DatabaseCredentials.user,
                    DatabaseCredentials.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select " + id + " from trips");

            Trip trip = new Trip();

            String driverID = resultSet.getString("driver_id");
            String riderID = resultSet.getString("rider_id");
            String tripID = resultSet.getString("trip_id");
            double beginLatitude = resultSet.getDouble("begin_latitude");
            double beginLongitude = resultSet.getDouble("begin_longitude");
            double endLatitude = resultSet.getDouble("end_latitude");
            double endLongitude = resultSet.getDouble("end_longitude");
            Date beginTime = resultSet.getDate("begin_time");
            Date endTime = resultSet.getDate("end_time");
            BigDecimal estimatedTripCostDec = resultSet.getBigDecimal("estimated_trip_cost");
            BigDecimal actualTripCostDec = resultSet.getBigDecimal("actual_trip_cost");

            Student driver = getStudentFromDatabase(driverID);
            Student rider = getStudentFromDatabase(riderID);
            Location beginLocation = new Location(beginLatitude, beginLongitude);
            Location endLocation = new Location(endLatitude, endLongitude);
            Money estimatedTripCost = new Money(estimatedTripCostDec);
            Money actualTripCost = new Money(actualTripCostDec);

            trip.setDriver(driver);
            trip.setRider(rider);
            trip.setTripID(tripID);
            trip.setBeginLocation(beginLocation);
            trip.setEndLocation(endLocation);
            trip.setBeginTime(beginTime);
            trip.setEndTime(endTime);
            trip.setEstimatedTripCost(estimatedTripCost);
            trip.setActualTripCost(actualTripCost);
            trip.setTripEnded(true);

            resultSet.close();
            statement.close();
            connection.close();

            return trip;

        } catch (SQLException sqlException) {
            System.out.println("No trip " + id + ".");
            return null;
        }
    }

    public Student getStudentFromDatabase(String id) {
        try {
            Connection connection;
            Statement statement;
            ResultSet resultSet;

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students",
                    DatabaseCredentials.user,
                    DatabaseCredentials.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select " + id + " from students");

            Student student = new Student();
            Student.StudentVehicle studentVehicle;

            String studentID = resultSet.getString("student_id");
            String lastName = resultSet.getString("last_name");
            String middleInitial = resultSet.getString("middle_initial");
            String firstName = resultSet.getString("first_name");
            String address = resultSet.getString("address");
            String phoneNumber = resultSet.getString("phone_number");
            String driversLicenseNumber = resultSet.getString("drivers_license_number");
            Date birthDate = resultSet.getDate("birth_date");
            Date dateOfLicenseExpiration = resultSet.getDate("date_of_license_expiration");

            student.setStudentID(studentID);
            student.setLastName(lastName);
            student.setMiddleInitial(middleInitial);
            student.setFirstName(firstName);
            student.setAddress(address);
            student.setPhoneNumber(phoneNumber);
            student.setDriversLicenseNumber(driversLicenseNumber);
            student.setBirthDate(birthDate);
            student.setDateOfLicenseExpiration(dateOfLicenseExpiration);

            resultSet.close();
            statement.close();
            connection.close();

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_vehicles",
                    DatabaseCredentials.user,
                    DatabaseCredentials.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select " + id + " from student_vehicles");

            if(resultSet != null) {
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                String year = resultSet.getString("year");
                String licensePlateNumber = resultSet.getString("license_plate_number");
                String insuranceNumber = resultSet.getString("insurance_number");
                Date dateOfInsuranceExpiration = resultSet.getDate("date_of_insurance_expiration");

                studentVehicle = student.getVehicle();
                studentVehicle.setMake(make);
                studentVehicle.setModel(model);
                studentVehicle.setYear(year);
                studentVehicle.setLicensePlateNumber(licensePlateNumber);
                studentVehicle.setInsuranceNumber(insuranceNumber);
                studentVehicle.setDateOfInsuranceExpiration(dateOfInsuranceExpiration);
                student.setVehicle(studentVehicle);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return student;

        } catch (SQLException sqlException) {
            System.out.println("No record of an account matching " + id + ".");

            return null;
        }
    }

    public Account getAccountFromDatabase(String id) {
        try {
            Connection connection;
            Statement statement;
            ResultSet resultSet;

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/accounts",
                    DatabaseCredentials.user,
                    DatabaseCredentials.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select " + id + " from accounts");

            Account account = new Account();

            String studentID = resultSet.getString("student_id");
            BigDecimal balanceDec = resultSet.getBigDecimal("balance");
            String encryptedPassword = resultSet.getString("password");
            String encryptedBankAccountNumber = resultSet.getString("bank_account_number");
            String encryptedBankRoutingNUmber = resultSet.getString("bank_routing_number");

            Student student = getStudentFromDatabase(studentID);
            Balance balance = new Balance(new Money(balanceDec));

            try {
                Password password = new Password(encryptedPassword);
                BankAccountNumber bankAccountNumber = new BankAccountNumber(encryptedBankAccountNumber);
                BankRoutingNumber bankRoutingNumber = new BankRoutingNumber(encryptedBankRoutingNUmber);

                account.setPassword(password);
                account.setBankAccountNumber(bankAccountNumber);
                account.setBankRoutingNumber(bankRoutingNumber);

            } catch (Exception e) {
                System.out.println("Cannot decrypt userdata.");
            }

            account.setStudent(student);
            account.setBalance(balance);

            resultSet.close();
            statement.close();
            connection.close();

            return account;

        } catch (SQLException sqlException) {
            System.out.println("No record of an account matching " + id + ".");

            return null;
        }
    }

    public void setTripHistoryFromDatabase(Account account) {
        try {
            Student student = account.getStudent();
            Connection connection;
            Statement statement;
            ResultSet resultSet;

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trips",
                    DatabaseCredentials.user,
                    DatabaseCredentials.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select " + student.getStudentID() + " from trips");

            ArrayList<Trip> tripHistory = new ArrayList<>();

            while(resultSet.next()) {
                Trip trip = getTripFromDatabase(resultSet.getString("trip_id"));
                tripHistory.add(trip);
            }

            account.setTripHistory(tripHistory);

        } catch (SQLException sqlException) {
            System.out.println("No trip history for account " + account + ".");
        }
    }
}
