package com.redhawkride.model.locationhandling;

import java.util.ArrayList;
import java.util.Date;

public class RouteLog {
  private ArrayList<RouteEvent> routeEvents;

  private class RouteEvent {
    private Location location;
    private Date time;
    private boolean atEndOfTrip;
  }
}
