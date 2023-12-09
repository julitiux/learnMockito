package com.learn_mockito.chapter1;

import java.util.Optional;

public class HelloMockito {

  private String greeting = "Hello, %s, from Mockito!";

  private final PersonRespository personRespository;
  private final TranslationService translationService;

  public HelloMockito(PersonRespository personRespository, TranslationService translationService) {
    this.personRespository = personRespository;
    this.translationService = translationService;
  }

  public String greet(Long id, String sourceLang, String targetLang) {
    Optional<Person> person = personRespository.findById(id);
    String name = person.map(Person::getFirstName).orElse("World");
    return translationService.translate(String.format(greeting, name), sourceLang, targetLang);
  }
}
