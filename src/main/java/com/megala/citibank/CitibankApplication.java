package com.megala.citibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.chainsys")
public class CitibankApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(CitibankApplication.class, args);
		
	}
	
	

}
