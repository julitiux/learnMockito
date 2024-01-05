package com.learn_mockito.chapter2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Integer> savePeople(Person... person) {
    return Arrays.stream(person)
      .map(personRepository::save)
      .map(Person::getId)
      .collect(Collectors.toList());
  }

  public void asyncSavePerson(Person person, long delay) {
    CompletableFuture.runAsync(() -> {
      System.out.println("Running on thread " + Thread.currentThread().getName());
      try {
        Thread.sleep(delay);
      } catch (InterruptedException ignored) {
      }
      personRepository.save(person);
    });
  }

  public List<String> getLastNames() {
    return personRepository.findAll().stream()
      .map(Person::getLast)
      .collect(Collectors.toList());
  }

  public List<Person> findByIds(int... ids) {
    return Arrays.stream(ids)
      .mapToObj(personRepository::findById)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(Collectors.toList());
  }

  public Integer getHighestId() {
    return personRepository.findAll().stream()
      .map(Person::getId)
      .max(Integer::compareTo).orElse(0);
  }

  public Person createPerson(int id, String first, String last, LocalDate dob) {
    Person person = new Person(id, first, last, dob);
    return personRepository.save(person);
  }

  public Person createPerson(int id, String first, String last, String dobString) {
    Person person = new Person(id, first, last, LocalDate.parse(dobString));
    return personRepository.save(person);
  }

  public long getTotalPeople() {
    return personRepository.count();
  }

  public void deleteAll() {
    personRepository.findAll()
      .forEach(personRepository::delete);
  }

}
