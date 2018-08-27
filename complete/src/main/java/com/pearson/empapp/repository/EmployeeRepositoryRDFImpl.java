package com.pearson.empapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pearson.empapp.entity.Employee;

@Repository ("employeeDao")
public class EmployeeRepositoryRDFImpl implements EmployeeRepository {

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		List<Employee> employees = new ArrayList<>();
		
		employees.add(new Employee("1", "Tom", "Hanks"));
		employees.add(new Employee("2", "Elon", "Musk"));
		
		return employees;
	}

}
