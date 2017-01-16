package com.mmashyr;

import com.mmashyr.config.JpaConfig;
import com.mmashyr.config.MvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{EshopApplication.class, JpaConfig.class, MvcConfiguration.class}, args);
    }
}
