package com.learn_mockito.chapter2;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Test
  public void defaultImplementations() {
    PersonRepository mockRepo = mock(PersonRepository.class);

    assertAll(
      () -> assertNull(mockRepo.save(any(Person.class))),
      () -> assertTrue(mockRepo.findById(anyInt()).isEmpty()),
      () -> assertTrue(mockRepo.findAll().isEmpty()),
      () -> assertEquals(0, mockRepo.count())
    );

  }

}
