package com.github.jomardev25.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class ResourceWebConfiguration implements WebMvcConfigurer {

  @Value("${application.file.upload-root-dir}")
  private String dirName;

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    Path uploadDir = Paths.get(dirName);
    String uploadPath = uploadDir.toFile().getAbsolutePath();

    if (dirName.startsWith("../"))
      dirName = dirName.replace("../", "");

      System.out.println("UPLOAD_PATH: "  + uploadPath);
      registry.addResourceHandler("/uploads/**").addResourceLocations("file:/"+ uploadPath + "/");
  }
}
