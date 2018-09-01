package com.pearson.empapp;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pearson.empapp.entity.Department;
import com.pearson.empapp.entity.Employee;
import com.pearson.empapp.repository.DepartmentRepository;
import com.pearson.empapp.repository.EmployeeRepository;
import com.pearson.empapp.service.EmployeeService;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(EmployeeApplication.class, args);
	}

	

	@Bean
	CommandLineRunner initEmpData(EmployeeService es, DepartmentRepository dr){
	   return args -> {
	      Employee tom = es.add(new Employee("Tom", "1/1/1960"));
	      Employee larry = es.add(new Employee("Larry", "1/1/1952"));
	      Employee ajay = es.add(new Employee("Ajay", "1/1/1975"));
	      
	      Department finance = dr.save(new Department("Finance"));
	      Department marketing = dr.save(new Department("Marketing"));
	      
	      es.assign(tom, finance);
	      es.assign(larry, marketing);
	      es.assign(ajay, marketing);
	   };
	}
	
}
