package com.programmerzamannow.spring.config.configurationproperties;

import com.programmerzamannow.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

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

  @Test
  void testCollection() {
    Assertions.assertEquals(Arrays.asList("products", "customers", "categories"), properties.getDatabase().getWhitelistTables());
    Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("products"));
    Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("categories"));
    Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("customers"));
  }

  @Test
  void testEmbeddedCollection() {
    Assertions.assertEquals("default", properties.getDefaultRoles().get(0).getId());
    Assertions.assertEquals("Default Role", properties.getDefaultRoles().get(0).getName());
    Assertions.assertEquals("guest", properties.getDefaultRoles().get(1).getId());
    Assertions.assertEquals("Guest Role", properties.getDefaultRoles().get(1).getName());

    Assertions.assertEquals("admin", properties.getRoles().get("admin").getId());
    Assertions.assertEquals("Admin Role", properties.getRoles().get("admin").getName());
    Assertions.assertEquals("finance", properties.getRoles().get("finance").getId());
    Assertions.assertEquals("Finance Role", properties.getRoles().get("finance").getName());
  }

  @Test
  void testDuration() {
    Assertions.assertEquals(Duration.ofSeconds(10), properties.getDefaultTimeout());
  }

  @Test
  void testCustomConverter() {
    Date expireDate = properties.getExpireDate();

    var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Assertions.assertEquals("2020-10-10", dateFormat.format(expireDate));
  }

  @SpringBootApplication
  @EnableConfigurationProperties({
      ApplicationProperties.class
  })
  public static class TestApplication {

  }

}
