package com.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webapp.model.Employee;
import com.example.webapp.repository.EmployeeProxy;

import lombok.Data;

@Data
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeProxy employeeProxy;
	
	public Iterable<Employee> getEmployees() {
		return employeeProxy.getEmployees();
	}
	
	public Employee getEmployee(final int id) {
		return employeeProxy.getEmployee(id);
	}
	
	public void deleteEmployee(final int id) {
		employeeProxy.deleteEmployee(id);
	}
	
	public Employee saveEmployee(Employee employee) {
		Employee savedEmployee;
		
		employee.setLastName(employee.getLastName().toUpperCase());
		
		if(employee.getId() == null) {
			savedEmployee = employeeProxy.createEmployee(employee);
		}else {
			savedEmployee = employeeProxy.updateEmployee(employee);
		}
		
		return savedEmployee;
	}
	
}
