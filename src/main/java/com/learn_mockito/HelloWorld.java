package com.learn_mockito;

public class HelloWorld {

  private String greeting = "Hello %s, from Mockito";

  public String greet(String name) {
    return String.format(greeting, name);
  }
}