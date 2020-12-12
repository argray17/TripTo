package com.tripto.model;

import java.util.Date;

public class Student {
  String studentID, lastName, middleInitial, firstName, address, phoneNumber, driversLicenseNumber;
  Date birthDate, dateOfLicenseExpiration;
  StudentVehicle vehicle;
  boolean validDriver;

  public Student() {
    this.vehicle = new StudentVehicle();
  }

  public Student(
      String studentID,
      String lastName,
      String middleInitial,
      String firstName,
      String address,
      String phoneNumber,
      String driversLicenseNumber,
      Date birthDate,
      Date dateOfLicenseExpiration,
      boolean validDriver) {
    this.studentID = studentID;
    this.lastName = lastName;
    this.middleInitial = middleInitial;
    this.firstName = firstName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.driversLicenseNumber = driversLicenseNumber;
    this.birthDate = birthDate;
    this.dateOfLicenseExpiration = dateOfLicenseExpiration;
    this.vehicle = new StudentVehicle();
    this.validDriver = validDriver;
  }

  public String getStudentID() {
    return studentID;
  }

  public void setStudentID(String studentID) {
    this.studentID = studentID;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getDriversLicenseNumber() {
    return driversLicenseNumber;
  }

  public void setDriversLicenseNumber(String driversLicenseNumber) {
    this.driversLicenseNumber = driversLicenseNumber;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Date getDateOfLicenseExpiration() {
    return dateOfLicenseExpiration;
  }

  public void setDateOfLicenseExpiration(Date dateOfLicenseExpiration) {
    this.dateOfLicenseExpiration = dateOfLicenseExpiration;
  }

  public StudentVehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(StudentVehicle vehicle) {
    this.vehicle = vehicle;
  }

  public boolean isValidDriver() {
    return validDriver;
  }

  public void setValidDriver(boolean validDriver) {
    this.validDriver = validDriver;
  }

  public class StudentVehicle {
    String make, model, year, color, licensePlateNumber, insuranceNumber;
    Date dateOfInsuranceExpiration;

    private StudentVehicle() {}

    private StudentVehicle(
        String make,
        String model,
        String year,
        String color,
        String licensePlateNumber,
        String insuranceNumber,
        Date dateOfInsuranceExpiration) {
      this.make = make;
      this.model = model;
      this.year = year;
      this.color = color;
      this.licensePlateNumber = licensePlateNumber;
      this.insuranceNumber = insuranceNumber;
      this.dateOfInsuranceExpiration = dateOfInsuranceExpiration;
    }

    public String getMake() {
      return make;
    }

    public void setMake(String make) {
      this.make = make;
    }

    public String getModel() {
      return model;
    }

    public void setModel(String model) {
      this.model = model;
    }

    public String getYear() {
      return year;
    }

    public void setYear(String year) {
      this.year = year;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public String getLicensePlateNumber() {
      return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
      this.licensePlateNumber = licensePlateNumber;
    }

    public String getInsuranceNumber() {
      return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
      this.insuranceNumber = insuranceNumber;
    }

    public Date getDateOfInsuranceExpiration() {
      return dateOfInsuranceExpiration;
    }

    public void setDateOfInsuranceExpiration(Date dateOfInsuranceExpiration) {
      this.dateOfInsuranceExpiration = dateOfInsuranceExpiration;
    }
  }
}
