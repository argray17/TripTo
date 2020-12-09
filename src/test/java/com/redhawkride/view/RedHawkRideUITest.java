package com.redhawkride.view;

import com.redhawkride.controller.RedHawkRideController;
import java.io.IOException;
import java.text.ParseException;
import org.junit.jupiter.api.Test;

class RedHawkRideUITest {
  private RedHawkRideController rHRController;
  private RedHawkRideUI rHRUI;

  RedHawkRideUITest() throws IOException, ParseException {
    this.rHRController = new RedHawkRideController();
    this.rHRUI = new RedHawkRideUI(rHRController);
  }

  @Test
  void mainMenu() {
    rHRUI.mainMenu();
  }

  @Test
  void newUser() {}

  @Test
  void rider() {}

  @Test
  void driver() {}
}
