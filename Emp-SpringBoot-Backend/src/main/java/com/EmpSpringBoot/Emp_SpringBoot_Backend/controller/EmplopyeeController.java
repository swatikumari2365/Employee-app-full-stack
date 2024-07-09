package com.EmpSpringBoot.Emp_SpringBoot_Backend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmpSpringBoot.Emp_SpringBoot_Backend.exception.ResourceNotFoundException;
import com.EmpSpringBoot.Emp_SpringBoot_Backend.model.Employee;
import com.EmpSpringBoot.Emp_SpringBoot_Backend.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmplopyeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//create employees rest API
	@PostMapping("/employees") 
	public Employee createdEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
	//get single employee data
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id:"+id));
		return ResponseEntity.ok(employee);
		
	}
	
	//update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id:"+id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		Employee updatedEmployee=employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id:"+id));
		employeeRepository.delete(employee);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", true);
		return ResponseEntity.ok(response);
	}
	
}
