package com.megala.bankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.megala.bankapp.servlet")
public class CitibankApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitibankApplication.class, args);

	}

}
