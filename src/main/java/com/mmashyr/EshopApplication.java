package com.mmashyr;

import com.mmashyr.config.JpaConfig;
import com.mmashyr.config.MvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = Jsr310JpaConverters.class)
public class EshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{EshopApplication.class, JpaConfig.class, MvcConfiguration.class, }, args);
    }
}
