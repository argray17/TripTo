package com.redhawkride.controller.maps;

import com.redhawkride.controller.filehandling.LoadFromFile;
import com.redhawkride.controller.filehandling.RecordToFile;
import com.redhawkride.model.Student;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StudentsMap {
  static HashMap<String, Student> mapOfStudents;

  public StudentsMap(HashMap mapOfStudents) {
    this.mapOfStudents = mapOfStudents;
  }

  public static boolean addStudent(Student student) {
    String key = student.getStudentID();
    if (mapOfStudents.containsKey(key)) {
      System.out.println("An account already exists under this Student ID.");
      return false;
    } else if (LoadFromFile.validateStudentID()) {
      mapOfStudents.put(key, student);
      RecordToFile.createdAccount(student);
      return true;
    } else {
      System.out.println("There is no record of this Student ID.");
      return false;
    }
  }

  public static boolean validateStudentID(String key) {
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
}
