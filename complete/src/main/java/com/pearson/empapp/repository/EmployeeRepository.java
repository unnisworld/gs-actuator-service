package com.pearson.empapp.repository;

import java.util.List;

import com.pearson.empapp.entity.Department;
import com.pearson.empapp.entity.Employee;

public interface EmployeeRepository {
	
	/**
	 * Find all employees.
	 * 
	 * @return
	 */
	List<Employee> findAll();
	
	
	/**
	 *  Save Employee.
	 *  
	 * @param e
	 */
	Employee save(Employee e);
	
	
	/**
	 * Updates the Department Id of an Employee.
	 * 
	 * @param e
	 * @param deptId
	 */
	void updateDepartment(Employee e, Department dept);

}
