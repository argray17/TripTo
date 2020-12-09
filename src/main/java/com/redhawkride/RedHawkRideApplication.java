package com.redhawkride;

import com.redhawkride.controller.RedHawkRideController;
import com.redhawkride.view.RedHawkRideUI;

public class RedHawkRideApplication {
  public static void main(String[] args) {
    RedHawkRideController rHRController = new RedHawkRideController();
    RedHawkRideUI rHRUI = new RedHawkRideUI(rHRController);
    rHRUI.mainMenu();
  }
}
