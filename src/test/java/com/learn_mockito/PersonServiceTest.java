package com.learn_mockito;


import com.learn_mockito.Person;
import com.learn_mockito.PersonRepository;
import com.learn_mockito.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;


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

  @Test
  public void getLastNames_usingAnnotations() {
    when(personRepository.findAll()).thenReturn(people);
    assertThat(personService.getLastNames())
      .contains("Borg", "Goldberg", "Hopper", "Liskov", "Lovelace");
    verify(personRepository).findAll();
  }

  @Test
  void findByIds_explicitWhens() {
    when(personRepository.findById(0))
      .thenReturn(Optional.of(people.get(0)));
    when(personRepository.findById(1))
      .thenReturn(Optional.of(people.get(1)));
    when(personRepository.findById(2))
      .thenReturn(Optional.of(people.get(2)));
    when(personRepository.findById(3))
      .thenReturn(Optional.of(people.get(3)));
    when(personRepository.findById(4))
      .thenReturn(Optional.of(people.get(4)));
    when(personRepository.findById(5))
      .thenReturn(Optional.empty());

    List<Person> personList = personService.findByIds(0, 1, 2, 3, 4, 5);
    assertThat(personList).containsExactlyElementsOf(people);
  }

  @Test
  void findByIds_thenReturnWithMultipleArgs() {
    when(personRepository.findById(anyInt())).thenReturn(
      Optional.of(people.get(0)),
      Optional.of(people.get(1)),
      Optional.of(people.get(2)),
      Optional.of(people.get(3)),
      Optional.of(people.get(4)),
      Optional.empty());
    List<Person> personList = personService.findByIds(0, 1, 2, 3, 4, 5);
    assertThat(personList).containsExactlyElementsOf(people);
  }

  @Test
  public void deleteAllWithNulls() {
    // Set up findAll to return a list containing nulls of type Person
    when(personRepository.findAll()).thenReturn(
      Arrays.asList((Person) null)
    );

    // This Won't compile
    // when(personRepository.delete(null)).thenThrow(RuntimeException.class);

    // But this will
    doThrow(RuntimeException.class).when(personRepository).delete(null);
    assertThrows(RuntimeException.class, () -> personService.deleteAll());
    verify(personRepository).delete(null);
  }

}
