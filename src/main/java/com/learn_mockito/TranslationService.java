package com.learn_mockito;

public interface TranslationService {

  default String translate(String text,
                           String sourceLang,
                           String targetLang){
    return text;
  }

}
