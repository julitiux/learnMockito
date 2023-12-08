package com.learn_mockito.chapter1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private String mail;

  public String getFirstName() {
    return firstName;
  }
}
