package com.learn_mockito.chapter2;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  private final List<Person> people = Arrays.asList(
    new Person(1, "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9)),
    new Person(2, "Ada", "Lovelace", LocalDate.of(1815, Month.DECEMBER, 10)),
    new Person(3, "Adele", "Goldberg", LocalDate.of(1945, Month.JULY, 7)),
    new Person(14, "Anita", "Borg", LocalDate.of(1949, Month.JANUARY, 17)),
    new Person(5, "Barbara", "Liskov", LocalDate.of(1939, Month.NOVEMBER, 7))
  );

  @Test
  public void getLastNames_usingMockMethod() {
    // Create a stub for the PersonRepository
    PersonRepository mockRepo = mock(PersonRepository.class);

    // Set the expectations on the mock...
    when(mockRepo.findAll()).thenReturn(people);

    // Inject the mock into the service
    PersonService personService = new PersonService(mockRepo);

    // Get the last names (this is the method to test)
    List<String> lastNames = personService.getLastNames();

    // Check that the last names are correct ()using AssertJ)
    assertThat(lastNames).contains("Borg", "Goldberg", "Hopper", "Liskov", "Lovelace");

    // Verify that the service called findAll on the mockRepo exactly once
    verify(mockRepo).findAll();
  }

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
