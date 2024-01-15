package com.learn_mockito;

public interface TranslationService {
  default String translate(String text,
                           String sourceLang,
                           String tagetLang) {
    return text;
  }
}
