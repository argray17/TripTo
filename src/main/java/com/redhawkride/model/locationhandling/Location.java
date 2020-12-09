package com.redhawkride.model.locationhandling;

// import com.google.api.gbase.client.Location;?

public class Location {
  private float latitude, longitude;

  public Location(float latitude, float longitude){
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public float getLatitude(){
    return latitude;
  }

  public void setLatitude(float latitude){
    this.latitude = latitude;
  }

  public float getLongitude(){
    return longitude;
  }

  public void setLongitude(float longitude){
    this.longitude = longitude;
  }


}
