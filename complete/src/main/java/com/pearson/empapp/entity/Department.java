package com.pearson.empapp.entity;

import java.util.Collections;
import java.util.List;

public class Department {
	
	private String id;
	private String name;
	private List<Employee> employees;
	
	public Department(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
