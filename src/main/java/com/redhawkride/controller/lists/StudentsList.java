package com.redhawkride.controller.lists;

import com.redhawkride.model.Student;
import com.redhawkride.model.locationhandling.Location;
import java.util.ArrayList;

public class StudentsList {
  private ArrayList<Student> listOfStudents;

  public StudentsList() {
    listOfStudents = new ArrayList<>();
  }

  public void addStudent(Student student) {
    listOfStudents.add(student);
  }

  public void removeStudent(Student student) {
    listOfStudents.remove(student);
  }

  public Student findNearest(Location location) {
    Student nearest = listOfStudents.get(0);
    for (Student student : listOfStudents) {
      if (student.closerThan(nearest)) {
        nearest = student;
      }
    }
    return nearest;
  }
}
