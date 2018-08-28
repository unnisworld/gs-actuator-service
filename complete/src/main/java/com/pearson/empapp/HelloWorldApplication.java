package com.pearson.empapp;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pearson.empapp.entity.Employee;
import com.pearson.empapp.repository.EmployeeRepository;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	

	@Bean
	CommandLineRunner initData(EmployeeRepository er){
	   return args -> {
	      er.save(new Employee("1", "Tom", "1/1/1960"));
	      er.save(new Employee("2", "Larry", "1/1/1952"));
	      er.save(new Employee("3", "Ajay", "1/1/1975"));
	   };
	}
}
