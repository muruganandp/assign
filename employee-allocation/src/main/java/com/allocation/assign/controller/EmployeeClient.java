package com.allocation.assign.controller;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.allocation.assign.model.Employee;


@FeignClient(name= "EMPLOYEE-SERVICE")
public interface EmployeeClient {
	
	/**
     * Interface method to get the employee information from a different microservice.
     * @param empCode
     * @return
     */
    @GetMapping(value= "/api/employee/{empCode}")
    public List<Employee> getEmployees(@PathVariable(name= "empCode") String empCode);

}
