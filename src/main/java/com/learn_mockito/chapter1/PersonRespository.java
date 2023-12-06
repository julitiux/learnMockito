package com.learn_mockito.chapter1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRespository extends JpaRepository<Person, Long> {
}
