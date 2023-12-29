package com.learn_mockito.chapter2;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Test
  public void defaultImplementations(){
    PersonRepository mockRepo = mock(PersonRepository.class);

    assertAll(
      () -> assertNull(mockRepo.save(any(Person.class)))
    );

  }

}
