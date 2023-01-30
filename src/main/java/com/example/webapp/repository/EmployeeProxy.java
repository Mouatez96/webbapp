package com.example.webapp.repository;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.webapp.CustomProperties;
import com.example.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {
	
	@Autowired
	private CustomProperties customProperties;
	
	public Iterable<Employee> getEmployees() {
		String baseApiUrl = customProperties.getApiUrl();
		String getEmployeesUrl = baseApiUrl + "/v1/employees";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
				getEmployeesUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Employee>>() {}
				);
		System.out.println("GET employees call"+ response.getStatusCode().toString());
		return response.getBody();
	}
	
	public Employee getEmployee(int id) {
		
		String baseApiUrl = customProperties.getApiUrl();
		String getEmployeeUrl = baseApiUrl + "/v1/employee/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employee> response = restTemplate.exchange(
				getEmployeeUrl,
				HttpMethod.GET,
				null,
				Employee.class
				);
		System.out.println("GET employee call "+ response.getStatusCode().toString());
		return response.getBody();
	}
	
	public Employee createEmployee(Employee employee) {
		String baseApiUrl = customProperties.getApiUrl();
		String createEmployeeUrl = baseApiUrl + "/v1/employee";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		ResponseEntity<Employee> response = restTemplate.exchange(
				createEmployeeUrl,
				HttpMethod.POST, 
				request, 
				Employee.class);
		System.out.println("POST employee call "+ response.getStatusCode().toString());
		return response.getBody();
	}
	
	public Employee updateEmployee(Employee employee) {
		String baseApiUrl = customProperties.getApiUrl();
		String updateEmployeeUrl = baseApiUrl + "/v1/employee/" + employee.getId();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		ResponseEntity<Employee> response = restTemplate.exchange(
				updateEmployeeUrl,
				HttpMethod.PUT,
				request,
				Employee.class);
		System.out.println("PUT employee call "+ response.getStatusCode().toString());
		return response.getBody();
	}
	
	public void deleteEmployee(int id) {
		String baseApiUrl = customProperties.getApiUrl();
		String deleteEmployeeUrl = baseApiUrl + "/v1/employee/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteEmployeeUrl,
				HttpMethod.DELETE,
				null,
				Void.class);
		System.out.println("DELETE employee call"+ response.getStatusCode().toString());
	}
}
