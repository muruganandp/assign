package com.allocation.assign.delegate;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.allocation.assign.util.StringConstants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EmployeeServiceDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	


	@HystrixCommand(fallbackMethod = "callEmployeeServiceAndGetData_Fallback")
	public String callEmployeeServiceAndgetData(String empCode) {
		// ** fetching the allocation details.
		return restTemplate.exchange(StringConstants.REST_URL_EMPLOYEE + empCode, HttpMethod.GET, null, String.class)
				.getBody();
	}

	@SuppressWarnings("unused")
	private String callEmployeeServiceAndGetData_Fallback(String schoolname) {

		System.out.println("Employee Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!! No Response From Employee Service at this moment. "
				+ " Service will be back shortly - " + new Date();
	}

}
