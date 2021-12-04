package com.programmerzamannow.spring.config.resourceloader;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ResourceLoaderTest.TestApplication.class)
public class ResourceLoaderTest {

  @Autowired
  private TestApplication.SampleResource sampleResource;

  @Test
  void testResourceLoader() throws Exception {
    Assertions.assertEquals("Eko Kurniawan Khannedy", sampleResource.getText().trim());
  }

  @SpringBootApplication
  public static class TestApplication {

    @Component
    public static class SampleResource implements ResourceLoaderAware {

      @Setter
      private ResourceLoader resourceLoader;

      public String getText() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
        try (var inputStream = resource.getInputStream()) {
          return new String(inputStream.readAllBytes());
        }
      }

    }

  }

}
