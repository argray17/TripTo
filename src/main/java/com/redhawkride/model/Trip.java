package com.redhawkride.model;

import com.redhawkride.model.locationhandling.Location;
import com.redhawkride.model.locationhandling.RouteLog;
import com.redhawkride.model.moneyhandling.Money;

public class Trip {
  private Student driver, rider;
  private String tripID;
  private Location beginLocation, endLocation;
  private RouteLog routeLog;
  private Money estimatedTripCost, finalTripCost;
  private boolean hasBeenCharged;
}
