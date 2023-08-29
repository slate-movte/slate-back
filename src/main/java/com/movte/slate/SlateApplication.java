package com.movte.slate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SlateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlateApplication.class, args);
    }

}
