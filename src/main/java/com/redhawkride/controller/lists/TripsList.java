package com.redhawkride.controller.lists;

import com.redhawkride.model.Trip;
import java.util.ArrayList;

public class TripsList {
  private ArrayList<Trip> listOfUnchargedTrips;

  public TripsList(ArrayList listOfUnchargedTrips) {
    this.listOfUnchargedTrips = listOfUnchargedTrips;
  }

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
}
