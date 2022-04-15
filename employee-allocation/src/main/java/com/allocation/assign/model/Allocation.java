package com.allocation.assign.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "allocation")
@Data
public class Allocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empCode")
	private Employee employee;
	@Column(name = "project")
	private String project;
	public Allocation() {
		
	}
	public Allocation(Employee employee, String project) {
		this.employee=employee;
		this.project=project;		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}	
	

}
