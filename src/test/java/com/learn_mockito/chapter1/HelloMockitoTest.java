package com.learn_mockito.chapter1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloMockitoTest {

  private HelloWorld helloWorld = new HelloWorld();

  @Test
  void greetPerson() {
    String greeting = helloWorld.greet("World");
    assertEquals("Hello World, from Mockito", greeting);
  }

}
