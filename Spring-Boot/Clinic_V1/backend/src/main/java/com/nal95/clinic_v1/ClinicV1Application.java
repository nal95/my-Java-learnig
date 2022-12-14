package com.nal95.clinic_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClinicV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ClinicV1Application.class, args);
	}

}
