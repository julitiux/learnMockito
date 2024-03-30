package com.learn_mockito;

public class HelloMockitoRevised {

  private String greeting = "Hello, %s, from Mockito!";

  private final PersonRepository personRepository;
  private final TranslationService translationService;


  public HelloMockitoRevised(PersonRepository personRepository, TranslationService translationService) {
    this.personRepository = personRepository;
    this.translationService = translationService;
  }
}
