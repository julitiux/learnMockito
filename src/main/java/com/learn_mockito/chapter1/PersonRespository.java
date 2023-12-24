package com.learn_mockito.chapter1;

import java.util.List;
import java.util.Optional;

public interface PersonRespository {

  Optional<Person> findById(Long id);
  List<Person> findAll();
  Person saver(Person person);
  void delete(Person person);

}