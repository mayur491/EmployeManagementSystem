package com.codemayur.employee_management_system.service;

import java.util.List;
import java.util.Optional;

import com.codemayur.employee_management_system.models.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public Optional<Employee> getEmployeeById(String employeeId);
	
}
