package com.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
		employeeService.deleteEmployee(id);
		
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/ajouter")
	public String createEmployee(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		return "formNewEmployee";
	}
	
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(Model model, @PathVariable("id") final int id) {
		Employee e = employeeService.getEmployee(id);
		model.addAttribute("employee", e);
		return "formUpdateEmployee";
	}
	
	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		employeeService.saveEmployee(employee);
		return new ModelAndView("redirect:/");
	}
}
