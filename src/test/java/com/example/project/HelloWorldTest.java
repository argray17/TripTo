package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelloWorldTest {
  @Test
  void helloSaysHello() {
    assertEquals(new HelloWorld().hello(), "Hello!");
  }
}
