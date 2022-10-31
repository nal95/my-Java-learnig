package com.nal95.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClinicalserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicalserviceApplication.class, args);
    }

}
