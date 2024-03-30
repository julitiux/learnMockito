package com.learn_mockito;

import org.junit.jupiter.api.Test;

public class HelloMockitoTest {

  private HelloMockito helloMockito = new HelloMockito();

  @Test
  void greetPerson() {
    String greeting = helloMockito.greet("World");
  }

}
