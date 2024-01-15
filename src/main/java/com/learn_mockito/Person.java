package com.learn_mockito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  private Long id;
  private String firstName;
  private String lastName;
  private String mail;

  public String getFirstName() {
    return firstName;
  }
}
