package com.redhawkride.controller.maps;

import com.redhawkride.model.Trip;
import java.util.HashMap;
import java.util.UUID;

public class TripsMap {
  private final HashMap<String, Trip> mapOfTrips;

  public TripsMap(HashMap mapOfTrips) {
    this.mapOfTrips = mapOfTrips;
  }

  public void addTrip(Trip trip) {
    String key = genUniqueKey();
    trip.setTripID(key);
    mapOfTrips.put(key, trip);
  }

  private String genUniqueKey() {
    String key = UUID.randomUUID().toString();
    while (mapOfTrips.containsKey(key)) {
      key = UUID.randomUUID().toString();
    }
    return key;
  }
}
