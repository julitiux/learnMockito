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
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HelloMockitoTestFull {

  @Mock
  private PersonRepository personRepository;

  @Mock
  private TranslationService translationService;

  @InjectMocks
  private HelloMockitoRevised helloMockitoRevised;

  @Test
  @DisplayName("Greet Admiral Hopper")
  void greetAPersonThatExists() {
    when(personRepository.findById(anyInt())).thenReturn(Optional.of(new Person(1, "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9))));
    when(translationService.translate("Hello, Grace, from Mockito!", "en", "en")).thenReturn("Hello, Grace, from Mockito!");

    String greeting = helloMockitoRevised.greet(1, "en", "en");
    assertEquals("Hello, Grace, from Mockito!", greeting);

    InOrder inOrder = Mockito.inOrder(personRepository, translationService);
    inOrder.verify(personRepository).findById(anyInt());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }

}
