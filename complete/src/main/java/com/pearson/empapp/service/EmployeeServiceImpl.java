package com.pearson.empapp.service;

import java.util.List;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearson.empapp.entity.Department;
import com.pearson.empapp.entity.Employee;
import com.pearson.empapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee add(Employee e) {
		return employeeRepository.save(e);
	}
	
	public void assign(Employee e, Department d) {
		employeeRepository.updateDepartment(e, d);
	}
}
