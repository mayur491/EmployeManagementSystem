package com.codemayur.employee_management_system.service;

import com.codemayur.employee_management_system.dto.EmployeeDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    @NotNull EmployeeDto getEmployeeById(Long employeeId);

    Long setEmployee(EmployeeDto employeeDto);

    Long deleteEmployeeById(Long employeeId);
}
