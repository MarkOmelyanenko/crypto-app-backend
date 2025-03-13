package dev.march.financepetproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FinancePetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancePetProjectApplication.class, args);
    }

}
