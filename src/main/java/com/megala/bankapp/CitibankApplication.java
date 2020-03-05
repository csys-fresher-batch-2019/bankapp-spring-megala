package com.megala.bankapp;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.megala.bankapp.servlet.UserLoginServlet;

@SpringBootApplication
@ServletComponentScan("com.megala.bankapp.servlet")
public class CitibankApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitibankApplication.class, args);

	}

}
