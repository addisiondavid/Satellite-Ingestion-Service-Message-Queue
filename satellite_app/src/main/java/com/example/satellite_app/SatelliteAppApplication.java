package com.example.satellite_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class SatelliteAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatelliteAppApplication.class, args);
    }

}
