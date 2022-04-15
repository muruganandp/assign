package com.onboarding.assign.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.assign.model.Employee;
import com.onboarding.assign.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository employeeRepository;

	/*
	 * @Autowired EmployeeMapper employeeMapper;
	 */

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(required = false) String name) {
		logger.info("Inside EmployeeController :: getAllEmployee method for the parameter :" + name + "::");
		try {
			List<Employee> employee = new ArrayList<Employee>();
			if (name == null)
				employeeRepository.findAll().forEach(employee::add);
			else
				employeeRepository.findByNameContaining(name).forEach(employee::add);
			if (employee.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employee/{empCode}")
	public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable("empCode") String id) {
		logger.info("Inside EmployeeController :: getEmployeeById method for the parameter :" + id + "::");

		List<Employee> employeeList = employeeRepository.findByEmpCode(id);
		if (!employeeList.isEmpty()) {
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployeeRecord(@RequestBody Employee employee) {
		logger.info("Inside EmployeeController :: createEmployeeRecord method for the parameter :" + employee.toString()
				+ "::");
		try {
			// Employee employee = employeeMapper.voToModel(employeeVo);
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Employee finalEmployee = new Employee(employee.getEmpCode(), employee.getName(), employee.getDesignation(),
					employee.getDepartment());
			Set<ConstraintViolation<Employee>> violations = validator.validate(finalEmployee);
			//** USAGE OF VALIDATOR TO CHECK THE EMPCODE NOT BLANK
			for (ConstraintViolation<Employee> violation : violations) {
				logger.error(violation.getMessage()); 
			}
			employeeRepository.save(finalEmployee);
			return new ResponseEntity<>(finalEmployee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
