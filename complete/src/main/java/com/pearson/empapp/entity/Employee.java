package com.pearson.empapp.entity;

public class Employee {

	private String id;
	private String name;
	private String dob;
	private String departmenId;
	
	public Employee() {
		
	}
	
	public Employee(String name, String dob) {
		this.name = name;
		this.dob = dob;
	}
	
	public Employee(String id, String name, String dob) {
		this(name, dob);
		this.id = id;
	}

	public Employee(String id, String name, String dob, String deptId) {
		this(id, name, dob);
		this.departmenId = deptId;
	}
	
	public String getDepartmentId() {
		return departmenId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDob() {
		return dob;
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
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + ", departmenId=" + departmenId + "]";
	}
	
	
	
	
}
