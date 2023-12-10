package com.learn_mockito.chapter1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelloMockitoTestFull {

  @Mock
  private PersonRespository personRespository;

  @Mock
  private TranslationService translationService;

  @InjectMocks
  private HelloMockito helloMockito;

  @Test
  @DisplayName("Greet Admiral Hopper")
  void greetAPersonThatExists() {
    when(personRespository.findById(anyLong()))
      .thenReturn(Optional.of(new Person(1L, "Julio", "Ramirez", "rrodriguez.julio@gmail.com")));
    when(translationService
      .translate("Hello, Grace, from Mockito!", "en", "en"))
      .thenReturn("Hello, Grace, from Mockito!");

    String greeting = helloMockito.greet(1L, "en", "en");
    assertEquals("Hello, Grace, from Mockito!", greeting);

    InOrder inOrder = Mockito.inOrder(personRespository, translationService);
    inOrder.verify(personRespository).findById(anyLong());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }

  @Test
  @DisplayName("Greet a person not in the database")
  public void greetAPersonThatDoesNotExist() {
    when(personRespository.findById(anyLong()))
      .thenReturn(Optional.empty());
    when(translationService.translate("Hello, World, from Mockito!", "en", "en"))
      .thenReturn("Hello, World, from Mockito!");

    String greeting = helloMockito.greet(100L, "en", "en");
    assertEquals("Hello, World, from Mockito!", greeting);

    InOrder inOrder = Mockito.inOrder(personRespository, translationService);
    inOrder.verify(personRespository).findById(anyLong());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }

}
