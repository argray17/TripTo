package com.redhawkride.controller.maps;

import com.redhawkride.model.Student;
import com.redhawkride.model.moneyhandling.AccountBalance;
import com.redhawkride.model.moneyhandling.Money;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StudentsMap {
  public HashMap<String, Student> mapOfStudents;

  public StudentsMap() {
    mapOfStudents = new HashMap<>();
  }

  public boolean addStudent(Student student) throws IOException {
    File file = new File("src/main/java/com/redhawkride/data/Students.csv");
    String key = student.getStudentID();
    if (mapOfStudents.containsKey(key)) {
      System.out.println("An account already exists under this Student ID.");
      return false;
      // } else if (LoadFromFile.validateStudentID()) {
    } else {
      mapOfStudents.put(key, student);
      writeToFile(file);
      return true;
    }
  }

  public boolean validateStudentID(String key) {
    if (mapOfStudents.containsKey(key)) {
      return true;
    } else {
      return false;
    }
  }

  public ArrayList<Student> processStudentBalances() {
    Iterator<HashMap.Entry<String, Student>> iterator = mapOfStudents.entrySet().iterator();
    ArrayList<Student> studentList = new ArrayList<>();
    Student student;

    while (iterator.hasNext()) {
      HashMap.Entry<String, Student> set = (HashMap.Entry<String, Student>) iterator.next();
      student = set.getValue();

      if (student.getAccountBalance().getCurrentBalance().getAmount().compareTo(new BigDecimal(0))
          != 0) {
        studentList.add(student);
      }
    }

    return studentList;
  }

  public void loadFromFile(File file) throws IOException {

    FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line;

    while ((line = bufferedReader.readLine()) != null) {
      String[] values = line.split(",");
      String studentID = values[0];
      String password = values[1];
      String firstName = values[2];
      String lastName = values[3];
      String bankAccountNumber = values[4];
      String bankRoutingNumber = values[5];
      BigDecimal bigDecimal = new BigDecimal(Double.valueOf(values[6]));
      Money money = new Money(bigDecimal);
      AccountBalance accountBalance = new AccountBalance(money);
      Student student =
          new Student(
              studentID,
              password,
              firstName,
              lastName,
              bankAccountNumber,
              bankRoutingNumber,
              accountBalance);

      mapOfStudents.put(student.getStudentID(), student);
    }
  }

  public void writeToFile(File file) throws IOException {
    PrintWriter printWriter = new PrintWriter(file);
    Iterator<HashMap.Entry<String, Student>> iterator = mapOfStudents.entrySet().iterator();
    Student student;

    while (iterator.hasNext()) {
      StringBuilder stringBuilder = new StringBuilder();
      HashMap.Entry<String, Student> set = (HashMap.Entry<String, Student>) iterator.next();
      student = set.getValue();

      String studentID = student.getStudentID();
      String password = student.getPassword();
      String firstName = student.getFirstName();
      String lastName = student.getLastName();
      String bankAccountNumber = student.getBankAccountNumber();
      String bankRoutingNumber = student.getBankRoutingNumber();
      AccountBalance accountBalance = student.getAccountBalance();
      Money money = accountBalance.getCurrentBalance();
      BigDecimal bigDecimal = money.getAmount();
      String amount = String.valueOf(bigDecimal.doubleValue());

      stringBuilder.append(studentID + ",");
      stringBuilder.append(password + ",");
      stringBuilder.append(firstName + ",");
      stringBuilder.append(lastName + ",");
      stringBuilder.append(bankAccountNumber + ",");
      stringBuilder.append(bankRoutingNumber + ",");
      stringBuilder.append(amount + "\n");

      printWriter.print(stringBuilder.toString());
    }
    printWriter.flush();
  }

  public Student findStudent(String studentID) {
    return (Student) mapOfStudents.get(studentID);
  }
}
