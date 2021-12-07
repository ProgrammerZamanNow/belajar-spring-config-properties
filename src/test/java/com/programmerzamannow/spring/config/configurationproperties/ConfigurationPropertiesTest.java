package com.programmerzamannow.spring.config.configurationproperties;

import com.programmerzamannow.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

  @Autowired
  private ApplicationProperties properties;

  @Test
  void testConfigurationProperties() {
    Assertions.assertEquals("Belajar Spring Boot", properties.getName());
    Assertions.assertEquals(1, properties.getVersion());
    Assertions.assertEquals(false, properties.isProductionMode());
  }

  @Test
  void testDatabaseProperties() {
    Assertions.assertEquals("eko", properties.getDatabase().getUsername());
    Assertions.assertEquals("rahasia", properties.getDatabase().getPassword());
    Assertions.assertEquals("belajar", properties.getDatabase().getDatabase());
    Assertions.assertEquals("jdbc:contoh", properties.getDatabase().getUrl());
  }

  @SpringBootApplication
  @EnableConfigurationProperties({
      ApplicationProperties.class
  })
  public static class TestApplication {

  }

}
