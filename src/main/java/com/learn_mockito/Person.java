package com.learn_mockito;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {

  private final Integer id;
  private final String first;
  private final String last;
  private final LocalDate localDate;

}
