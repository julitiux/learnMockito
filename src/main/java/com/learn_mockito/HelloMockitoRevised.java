package com.learn_mockito;

import java.util.Optional;

public class HelloMockitoRevised {

  private String greeting = "Hello, %s, from Mockito!";

  // Dependencies
  private final PersonRepository personRepository;
  private final TranslationService translationService;

  // Constructor to inject the dependencies
  public HelloMockitoRevised(PersonRepository personRepository, TranslationService translationService) {
    this.personRepository = personRepository;
    this.translationService = translationService;
  }

  // Method ww want to test
  public String greet(int id, String sourceLang, String targetLang) {
    Optional<Person> person = personRepository.findById(id);
    String name = person.map(Person::getFirst).orElse("World");
    return translationService.translate(String.format(greeting, name), sourceLang, targetLang);
  }

}
