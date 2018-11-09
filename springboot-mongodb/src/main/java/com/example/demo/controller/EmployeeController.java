package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();

	}

	@GetMapping("/{id}")
	public Employee getByEmployeesId(@PathVariable(value = "id") Integer id) {
		return employeeRepository.findById(id).get();

	}

	@PostMapping("/addemployee")
	public List<Employee> addEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return employeeRepository.findAll();

	}

	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer id,
			@RequestBody Employee employee) {
		Optional<Employee> findById = employeeRepository.findById(id);
		if (findById.isPresent()) {
			Employee employee2 = employeeRepository.save(employee);
			return new ResponseEntity<Employee>(employee2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> addEmployee(@PathVariable(value = "id") Integer id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if (optional.isPresent()) {
			employeeRepository.deleteById(id);
			return new ResponseEntity<String>("employee deleted succesfuly", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("employee not found", HttpStatus.NOT_FOUND);
		}

	}
}
