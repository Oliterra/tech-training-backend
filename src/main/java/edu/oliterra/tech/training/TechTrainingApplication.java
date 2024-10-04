package edu.oliterra.tech.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TechTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechTrainingApplication.class, args);
    }

}
