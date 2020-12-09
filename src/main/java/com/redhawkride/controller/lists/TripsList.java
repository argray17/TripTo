package com.redhawkride.controller.lists;

import com.redhawkride.controller.RedHawkRideController;
import com.redhawkride.model.Trip;
import java.io.*;
import java.util.ArrayList;

public class TripsList {
  private ArrayList<Trip> listOfUnchargedTrips;

  public TripsList() {
    listOfUnchargedTrips = new ArrayList<>();
  }

  public void addTrip(Trip trip) {
    listOfUnchargedTrips.add(trip);
  }

  public void removeTrip(Trip trip) {
    listOfUnchargedTrips.remove(trip);
  }

  public ArrayList getList() {
    return listOfUnchargedTrips;
  }

  public void loadFromFile(File file, RedHawkRideController rHRController) throws IOException {
    FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    String tripID;

    while ((tripID = bufferedReader.readLine()) != null) {
      Trip trip = rHRController.findTrip(tripID);
      listOfUnchargedTrips.add(trip);
    }
  }

  public void writeToFile(File file) throws IOException {
    PrintWriter printWriter = new PrintWriter(file);

    for (Trip trip : listOfUnchargedTrips) {
      printWriter.print(trip.getTripID());
    }
    printWriter.flush();
  }
}
