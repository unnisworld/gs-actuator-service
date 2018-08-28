package com.pearson.empapp.repository;

import java.util.List;

import com.pearson.empapp.entity.Employee;

public interface EmployeeRepository {
	
	/**
	 * Find all employees.
	 * 
	 * @return
	 */
	List<Employee> findAll();
	
	void save(Employee e);
	

}
