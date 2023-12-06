package com.learn_mockito.chapter1;

public interface TranslationService {
  default String translate(String text,
                           String sourceLang,
                           String tagetLang) {
    return text;
  }
}
