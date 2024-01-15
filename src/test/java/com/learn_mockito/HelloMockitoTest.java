package com.learn_mockito;


import com.learn_mockito.HelloMockito;
import com.learn_mockito.PersonRespository;
import com.learn_mockito.TranslationService;
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
public class HelloMockitoTest {

  @Mock
  private PersonRespository personRespository;

  @Mock
  private TranslationService translationService;

  @InjectMocks
  private HelloMockito helloMockito;

  @Test
  @DisplayName("Greet Admiral Hopper")
  void greetAPersonThatExists() {
    // set the expectations on the mocks
    when(personRespository.findById(anyLong()))
      .thenReturn(Optional.of(new Person(1L, "Julio", "Ramirez", "rrodriguez.julio@gmail.com")));
    when(translationService
      .translate("Hello, Julio, from Mockito!", "en", "en"))
      .thenReturn("Hello, Julio, from Mockito!");

    // test the greet method
    String greeting = helloMockito.greet(1L, "en", "en");
    assertEquals("Hello, Julio, from Mockito!", greeting);

    // verify the methods are called once, in the right order
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

    // verify the methods are called once, in the right order
    InOrder inOrder = Mockito.inOrder(personRespository, translationService);
    inOrder.verify(personRespository).findById(anyLong());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }

}
