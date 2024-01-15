package com.learn_mockito.hr;

import com.learn_mockito.hr.HelloWorld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

  private HelloWorld helloWorld = new HelloWorld();

  @Test
  void greetPerson() {
    String greeting = helloWorld.greet("World");
    assertEquals("Hello World, from Mockito", greeting);
  }

}
