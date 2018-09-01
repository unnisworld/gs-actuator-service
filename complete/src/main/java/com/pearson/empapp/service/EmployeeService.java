package com.pearson.empapp.service;

import java.util.List;

import com.pearson.empapp.entity.Department;
import com.pearson.empapp.entity.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	Employee add(Employee e);

	void assign(Employee e, Department d);
}
