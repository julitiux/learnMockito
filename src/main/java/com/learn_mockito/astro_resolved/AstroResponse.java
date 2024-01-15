package com.learn_mockito.astro_resolved;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class AstroResponse {

  private final int number;
  private final String message;
  private final List<Assigment> people;

}
