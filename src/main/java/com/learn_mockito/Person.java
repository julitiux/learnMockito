package com.learn_mockito;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {

  private Integer id;
  private String first;
  private String last;
  private LocalDate localDate;

}
