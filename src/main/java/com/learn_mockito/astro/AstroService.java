package com.learn_mockito.astro;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AstroService {

  private final Gateway<AstroResponse> gateway;

  public Map<String, Long> getAstroData() {
    AstroResponse response = gateway.getResponse();
    return groupByCraft(response);
  }

  private Map<String, Long> groupByCraft(AstroResponse data) {
    return data.getPeople().stream()
      .collect(
        Collectors.groupingBy(
          Assigment::getCraft, Collectors.counting()
        )
      );
  }

}
