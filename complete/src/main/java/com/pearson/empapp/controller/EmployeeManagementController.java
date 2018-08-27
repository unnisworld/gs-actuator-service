package com.pearson.empapp.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearson.empapp.entity.Employee;
import com.pearson.empapp.repository.EmployeeRepository;
import com.pearson.empapp.service.EmployeeService;

@Controller
public class EmployeeManagementController {
	
	@Autowired
	private EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseBody	
	List<Employee> getEmployees() {
		return employeeService.getAllEmployees();
	}
}
