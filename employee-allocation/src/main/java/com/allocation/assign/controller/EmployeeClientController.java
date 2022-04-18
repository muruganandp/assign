package com.allocation.assign.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allocation.assign.model.Employee;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeClientController {
	
	Logger logger = LoggerFactory.getLogger(EmployeeClientController.class);

	@Autowired
	EmployeeClient empClient;

	@GetMapping(value = "/getEmployee/{empCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getEmployeeInfoViaFeign(@PathVariable(name = "empCode") String empCode) {
		logger.info("Using the feign client controller to fetch the employee information for code= " + empCode);
		List<Employee> empList = new ArrayList<Employee>();
		empList = empClient.getEmployees(empCode);
		// Sending the response
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

}
