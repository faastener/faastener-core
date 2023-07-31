package org.faastener.core;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@SpringBootApplication
public class FaastenerCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaastenerCoreApplication.class, args);
    }
}
