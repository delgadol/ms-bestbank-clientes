package com.bestbank.clientes.application.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;

public class TestUtils {
  
  private TestUtils() {}
  
  private static ObjectMapper objectMapper = new ObjectMapper();

  public static <T> T readJsonFromPath(String filePath, TypeReference<T> typeReference) throws IOException {
    File file = new ClassPathResource(filePath).getFile();
    return objectMapper.readValue(file, typeReference);
  }

  public static <T> T readJson(String jsonString, TypeReference<T> typeReference) throws IOException {
      return objectMapper.readValue(jsonString, typeReference);
  }

  public static <T> String writeJson(T object) throws IOException {
      return objectMapper.writeValueAsString(object);
  }

}
