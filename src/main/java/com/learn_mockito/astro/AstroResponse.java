package com.learn_mockito.astro;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AstroResponse {

  private final int number;
  private final String message;
  private final List<Assignment> people;

}
