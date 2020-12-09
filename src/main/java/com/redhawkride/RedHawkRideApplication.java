package com.redhawkride;

import com.redhawkride.controller.RedHawkRideController;
import com.redhawkride.view.RedHawkRideUI;
import java.io.IOException;
import java.text.ParseException;

public class RedHawkRideApplication {
  public static void main(String[] args) throws IOException, ParseException {
    RedHawkRideController rHRController = new RedHawkRideController();
    RedHawkRideUI rHRUI = new RedHawkRideUI(rHRController);
    rHRUI.mainMenu();
  }
}
