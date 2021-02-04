package com.example.personapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
public class PersonappApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PersonappApplication.class, args);
		
	}
	 
	
	

}
