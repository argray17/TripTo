package com.redhawkride.model.locationhandling;

import java.util.ArrayList;
import java.util.Date;

public class RouteLog {
  private ArrayList<RouteEvent> routeEvents;

  public void addEvent(Location location, Date time, boolean atEndOfTrip) {
    RouteEvent event = new RouteEvent(location, time, atEndOfTrip);
    routeEvents.add(event);
  }

  private class RouteEvent {
    private Location location;
    private Date time;
    private boolean atEndOfTrip;

    public RouteEvent() {}

    public RouteEvent(Location location, Date time, boolean atEndOfTrip) {
      this.location = location;
      this.time = time;
      this.atEndOfTrip = atEndOfTrip;
    }
  }
}
