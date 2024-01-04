package com.learn_mockito.chapter2;

public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  // methods to be tested

}
