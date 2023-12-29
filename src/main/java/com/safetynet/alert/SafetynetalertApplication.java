package com.safetynet.alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alert.util.JsonDataExtractor;

@SpringBootApplication
public class SafetynetalertApplication {

    public static void main(String[] args) {

        SpringApplication.run(SafetynetalertApplication.class, args);

        JsonDataExtractor.extractDataFromJson();

    }
}