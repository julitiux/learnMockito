package com.learn_mockito.hr;

public interface TranslationService {
  default String translate(String text,
                           String sourceLang,
                           String tagetLang) {
    return text;
  }
}
