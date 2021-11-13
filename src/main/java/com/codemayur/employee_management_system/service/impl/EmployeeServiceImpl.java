package com.codemayur.employee_management_system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codemayur.employee_management_system.models.Employee;
import com.codemayur.employee_management_system.models.Gender;
import com.codemayur.employee_management_system.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	List<Employee> employees = new ArrayList<>();

	{
		employees.add(new Employee("1", "Mayur", "Somani", 25, Gender.MALE));
		employees.add(new Employee("2", "Reuben", "Fernandes", 25, Gender.MALE));
		employees.add(new Employee("3", "Divya", "Walke", 23, Gender.FEMALE));
		employees.add(new Employee("4", "Prabhat", "Gaikwad", 24, Gender.MALE));
		employees.add(new Employee("5", "Saurabh", "Kanojia", 26, Gender.MALE));
		employees.add(new Employee("6", "Aditya", "Shinde", 24, Gender.MALE));
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employees;
	}

	@Override
	public Optional<Employee> getEmployeeById(String employeeId) {
		Employee employee = null;
		for (Employee emp : employees) {
			if (emp.getId().equals(employeeId)) {
				employee = emp;
				break;
			}
		}
		return Optional.ofNullable(employee);
	}

}
