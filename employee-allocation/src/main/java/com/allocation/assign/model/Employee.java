package com.allocation.assign.model;
import javax.persistence.*;
@Entity
@Table(name = "employee")
public class Employee {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Id
	@Column(name = "empCode")
	private String empCode;
	@Column(name = "name")
	private String name;
	@Column(name = "designation")
	private String designation;
	@Column(name = "department")
	private String department;
	
	public Employee() {
		
	}
	
	public Employee(String empCode,String name, String designation, String department) {
		this.empCode = empCode;
		this.name = name;
		this.designation = designation;
		this.department = department;				
		
	}
	
	
	public long getId() {
		return id;
	}
	public String getEmpCode() {
		return empCode;
	}
	
	public String getName() {
		return name;
	}
	public String getDesignation() {
		return designation;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setId(long id) {
		this.id=id;		
	}
	public void setEmpCode(String empCode) {
		this.empCode=empCode;		
	}
	public void setName ( String name) {
		this.name = name;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public void setDepartment(String department) {
		this.department=department;
	}
}
