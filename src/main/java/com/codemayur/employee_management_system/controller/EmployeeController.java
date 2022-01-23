package com.codemayur.employee_management_system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemayur.employee_management_system.models.Employee;
import com.codemayur.employee_management_system.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("getEmployee")
	@ApiOperation(value = "Get All Employees", notes = "This API will return the details of all the employees")
	public ResponseEntity<Map<String, Object>> getEmployee() {
		Map<String, Object> responseMap = new HashMap<>();
		List<Employee> employees;
		try {

			employees = employeeService.getAllEmployees();

		} catch (Exception e) {
			// TODO: log the error
			responseMap.put("detail", e.getMessage());
			return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("employees", employees);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@GetMapping("getEmployee/{employeeId}")
	public ResponseEntity<Map<String, Object>> getEmployee(
			@ApiParam(value = "ID value of the employee you need to retreive") @PathVariable String employeeId) {
		Map<String, Object> responseMap = new HashMap<>();
		Optional<Employee> employeeOptional;
		try {

			employeeOptional = employeeService.getEmployeeById(employeeId);

		} catch (Exception e) {
			// TODO: log the error
			responseMap.put("detail", e.getMessage());
			return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (employeeOptional.isEmpty()) {
			// TODO: log the error
			responseMap.put("detail", String.format("Employee with ID: '%s' Not Found", employeeId));
			return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
		}

		responseMap.put("employee", employeeOptional.get());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

}
