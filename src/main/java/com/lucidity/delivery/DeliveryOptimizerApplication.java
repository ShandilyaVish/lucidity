package com.lucidity.delivery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryOptimizerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DeliveryOptimizerApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Bean
    public CommandLineRunner demoDriver(DriverRunner driverRunner) {
        return args -> driverRunner.run();
    }
} 