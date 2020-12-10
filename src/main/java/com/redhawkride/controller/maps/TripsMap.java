package com.redhawkride.controller.maps;

import com.redhawkride.model.Student;
import com.redhawkride.model.Trip;
import com.redhawkride.model.locationhandling.Location;
import com.redhawkride.model.moneyhandling.Money;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TripsMap {
  private HashMap<String, Trip> mapOfTrips;

  public TripsMap() {
    mapOfTrips = new HashMap<>();
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

  public void writeTripToFile(Trip trip) throws FileNotFoundException {
    FileReader fileReader = new FileReader("src/main/java/com/redhawkride/data/Trips.csv");
    PrintWriter printWriter = new PrintWriter(String.valueOf(fileReader));
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    StringBuilder stringBuilder = new StringBuilder();

    String driver = trip.getDriver().getStudentID();
    String rider = trip.getRider().getStudentID();
    String tripID = trip.getTripID();
    String startLocationLat = Float.toString(trip.getStartLocation().getLatitude());
    String startLocationLon = Float.toString(trip.getStartLocation().getLongitude());
    String endLocationLat = Float.toString(trip.getEndLocation().getLatitude());
    String endLocationLon = Float.toString(trip.getEndLocation().getLongitude());
    String startTime = dateFormat.format(trip.getStartTime());
    String endTime = dateFormat.format(trip.getEndTime());
    String estimatedTripCost =
        String.valueOf(trip.getEstimatedTripCost().getAmount().doubleValue());
    String finalTripCost = String.valueOf(trip.getFinalTripCost().getAmount().doubleValue());

    stringBuilder.append(driver + ",");
    stringBuilder.append(rider + ",");
    stringBuilder.append(tripID + ",");
    stringBuilder.append(startLocationLat + ",");
    stringBuilder.append(startLocationLon + ",");
    stringBuilder.append(endLocationLat + ",");
    stringBuilder.append(endLocationLon + ",");
    stringBuilder.append(startTime + ",");
    stringBuilder.append(endTime + ",");
    stringBuilder.append(estimatedTripCost + ",");
    stringBuilder.append(finalTripCost + "\n");

    printWriter.print(stringBuilder.toString());
    printWriter.flush();
  }

  public void loadFromFile(File file, StudentsMap mapOfStudents)
      throws IOException, ParseException {
    FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    String line;

    while ((line = bufferedReader.readLine()) != null) {
      String[] values = line.split(",");
      String driverID = values[0];
      Student driver = mapOfStudents.findStudent(driverID);
      String riderID = values[1];
      Student rider = mapOfStudents.findStudent(riderID);
      String tripID = values[2];
      Float startLocationLat = Float.parseFloat(values[3]);
      Float startLocationLon = Float.parseFloat(values[4]);
      Location startLocation = new Location(startLocationLat, startLocationLon);
      Float endLocationLon = Float.parseFloat(values[5]);
      Float endLocationLat = Float.parseFloat(values[6]);
      Location endLocation = new Location(endLocationLat, endLocationLon);
      String startTimeStr = values[7];
      Date startTime = dateFormat.parse(startTimeStr);
      String endTimeStr = values[8];
      Date endTime = dateFormat.parse(endTimeStr);
      BigDecimal bigDecimal = new BigDecimal(Double.valueOf(values[9]));
      Money estimatedTripCost = new Money(bigDecimal);
      bigDecimal = new BigDecimal(Double.valueOf(values[10]));
      Money finalTripCost = new Money(bigDecimal);

      Trip trip =
          new Trip(
              driver,
              rider,
              tripID,
              startLocation,
              endLocation,
              startTime,
              endTime,
              estimatedTripCost,
              finalTripCost);

      driver.addTrip(trip);
      rider.addTrip(trip);

      mapOfTrips.put(trip.getTripID(), trip);
    }
  }

  public void writeToFile(File file) throws IOException {
    PrintWriter printWriter = new PrintWriter(file);
    Iterator<HashMap.Entry<String, Trip>> iterator = mapOfTrips.entrySet().iterator();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    Trip trip;

    while (iterator.hasNext()) {
      StringBuilder stringBuilder = new StringBuilder();
      HashMap.Entry<String, Trip> set = (HashMap.Entry<String, Trip>) iterator.next();
      trip = set.getValue();

      String driver = trip.getDriver().getStudentID();
      String rider = trip.getRider().getStudentID();
      String tripID = trip.getTripID();
      String startLocationLat = Float.toString(trip.getStartLocation().getLatitude());
      String startLocationLon = Float.toString(trip.getStartLocation().getLongitude());
      String endLocationLat = Float.toString(trip.getEndLocation().getLatitude());
      String endLocationLon = Float.toString(trip.getEndLocation().getLongitude());
      String startTime = dateFormat.format(trip.getStartTime());
      String endTime = dateFormat.format(trip.getEndTime());
      String estimatedTripCost =
          String.valueOf(trip.getEstimatedTripCost().getAmount().doubleValue());
      String finalTripCost = String.valueOf(trip.getFinalTripCost().getAmount().doubleValue());

      stringBuilder.append(driver + ",");
      stringBuilder.append(rider + ",");
      stringBuilder.append(tripID + ",");
      stringBuilder.append(startLocationLat + ",");
      stringBuilder.append(startLocationLon + ",");
      stringBuilder.append(endLocationLat + ",");
      stringBuilder.append(endLocationLon + ",");
      stringBuilder.append(startTime + ",");
      stringBuilder.append(endTime + ",");
      stringBuilder.append(estimatedTripCost + ",");
      stringBuilder.append(finalTripCost + "\n");

      printWriter.print(stringBuilder.toString());
    }
    printWriter.flush();
  }

  public Trip findTrip(String tripID) {
    return mapOfTrips.get(tripID);
  }
}
