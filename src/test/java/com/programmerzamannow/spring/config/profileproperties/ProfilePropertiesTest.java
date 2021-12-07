package com.programmerzamannow.spring.config.profileproperties;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfilePropertiesTest.TestApplication.class)
@ActiveProfiles({"production", "test"})
public class ProfilePropertiesTest {

  @Autowired
  private TestApplication.ProfileProperties properties;

  @Test
  void nameProfileProperties() {
    Assertions.assertEquals("Default", properties.getDefaultFile());
    Assertions.assertEquals("Production", properties.getProductionFile());
    Assertions.assertEquals("Test", properties.getTestFile());
  }

  @SpringBootApplication
  public static class TestApplication {

    @Getter
    @Component
    public static class ProfileProperties {

      @Value("${profile.default}")
      private String defaultFile;

      @Value("${profile.production}")
      private String productionFile;

      @Value("${profile.test}")
      private String testFile;

    }

  }

}
