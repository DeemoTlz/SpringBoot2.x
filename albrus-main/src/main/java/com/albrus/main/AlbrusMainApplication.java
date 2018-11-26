package com.albrus.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.albrus.*"})
public class AlbrusMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbrusMainApplication.class, args);
    }
}
