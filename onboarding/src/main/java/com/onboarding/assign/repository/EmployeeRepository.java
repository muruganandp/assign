package com.onboarding.assign.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onboarding.assign.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {	
	
	List<Employee> findById(long id);
	List<Employee> findByEmpCode(String empCode);
	List<Employee> findByDesignation(String designation);
	List<Employee> findByNameContaining(String name);


}
