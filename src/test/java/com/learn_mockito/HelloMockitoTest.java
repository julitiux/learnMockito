package com.learn_mockito;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelloMockitoTest {

  @Mock
  private PersonRepository personRepository;

  @Mock
  private TranslationService translationService;

  @InjectMocks
  private HelloMockito helloMockito;

  @Test
  @DisplayName("Greet Admiral Hopper")
  void greetAPersonThatExists() {
    // set the expectations on the mocks
    when(personRepository.findById(anyInt()))
      .thenReturn(Optional.of(new Person(1, "Julio", "Ramirez", LocalDate.now())));
    when(translationService
      .translate("Hello, Julio, from Mockito!", "en", "en"))
      .thenReturn("Hello, Julio, from Mockito!");

    // test the greet method
    String greeting = helloMockito.greet(1, "en", "en");
    assertEquals("Hello, Julio, from Mockito!", greeting);

    // verify the methods are called once, in the right order
    InOrder inOrder = Mockito.inOrder(personRepository, translationService);
    inOrder.verify(personRepository).findById(anyInt());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }

  @Test
  @DisplayName("Greet a person not in the database")
  public void greetAPersonThatDoesNotExist() {
    when(personRepository.findById(anyInt()))
      .thenReturn(Optional.empty());
    when(translationService.translate("Hello, World, from Mockito!", "en", "en"))
      .thenReturn("Hello, World, from Mockito!");

    String greeting = helloMockito.greet(100, "en", "en");
    assertEquals("Hello, World, from Mockito!", greeting);

    // verify the methods are called once, in the right order
    InOrder inOrder = Mockito.inOrder(personRepository, translationService);
    inOrder.verify(personRepository).findById(anyInt());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }

}
