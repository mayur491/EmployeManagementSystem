package com.codemayur.employee_management_system.service;

import com.codemayur.employee_management_system.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<EmployeeDto> getAllEmployees();

    public Optional<EmployeeDto> getEmployeeById(String employeeId);

    public String setEmployee(EmployeeDto employeeDto);

}
