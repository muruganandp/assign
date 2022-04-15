package com.allocation.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allocation.assign.model.Allocation;

public interface AllocationRepository extends JpaRepository<Allocation,Long> {
	
	List<Allocation> findById(long id);
	List<Allocation> findByEmployeeId(String employeeId);
	List<Allocation> findByProject(String project);

}
