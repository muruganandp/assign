package com.allocation.assign.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.allocation.assign.delegate.EmployeeServiceDelegate;
import com.allocation.assign.model.Allocation;
import com.allocation.assign.repository.AllocationRepository;
import com.allocation.assign.util.StringConstants;

@RestController
@RequestMapping("/allocation")
public class EmployeeAllocationController {
	Logger logger = LoggerFactory.getLogger(EmployeeAllocationController.class);

	@Autowired
	AllocationRepository allocationRepository;

	@Autowired 
	RestTemplate restTemplate;
	 
	
	@Autowired
	EmployeeServiceDelegate empServiceDelegate;

	@GetMapping("/allallocations")
	public ResponseEntity<List<Allocation>> getAllAllocations() {

		logger.info("Inside EmployeeAllocationController :: getAllAllocations method for the parameter :");
		try {
			List<Allocation> allocationList = new ArrayList<Allocation>();
			allocationRepository.findAll().forEach(allocationList::add);
			if (allocationList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(allocationList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/empallocations/{allocationId}")
	public String getEmployeeAllocations(
			@PathVariable("allocationId") long allocationId) {
		logger.info("Inside EmployeeAllocationController :: getEmployeeAllocatios method for the parameter :"
				+ allocationId + "::");
		try {
			List<Allocation> allocationList = new ArrayList<Allocation>();
			// ** fetching the allocation details.
			allocationList = allocationRepository.findById(allocationId);
			
			/*  return restTemplate.exchange( StringConstants.REST_URL_EMPLOYEE +
			 	allocationList.get(0).getEmployee().getEmpCode(), HttpMethod.GET, null,
			  	String.class).getBody(); */
			 
			return empServiceDelegate.callEmployeeServiceAndgetData(allocationList.get(0).getEmployee().getEmpCode());
				
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PostMapping("/createAllocation")
	public ResponseEntity<Allocation> createAllocation(@RequestBody Allocation allocation) {
		try {
			Allocation newAllocation = new Allocation(allocation.getEmployee(), allocation.getProject());
			allocationRepository.save(newAllocation);
			return new ResponseEntity<>(newAllocation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{allocationId}")
	public String getEmployeeAllocationId(
			@PathVariable("allocationId") long allocationId) {
		logger.info("Inside EmployeeAllocationController :: getEmployeeAllocatios method for the parameter :"
				+ allocationId + "::");
		try {
			List<Allocation> allocationList = new ArrayList<Allocation>();
			// ** fetching the allocation details.
			allocationList = allocationRepository.findById(allocationId);			
			return restTemplate.exchange( StringConstants.REST_URL_EMPLOYEE1 +
			 	allocationList.get(0).getEmployee().getEmpCode(), HttpMethod.GET, null,
			  	String.class).getBody(); 			 
				
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
