package com.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.webapp.model.Employee;
import com.example.webapp.service.EmployeeService;


@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String home(Model model) {
		Iterable<Employee> listEmployees = employeeService.getEmployees();
		model.addAttribute("employees", listEmployees);
		
		return "home";
	}
}
