package com.codemayur.employee_management_system.convertors;

import com.codemayur.employee_management_system.dto.EmployeeDto;
import com.codemayur.employee_management_system.entity.Employee;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author mayur.somani
 */
public class EmployeeConvertor {

    private EmployeeConvertor() {
        // restricting creation of object for this class
    }

    public static @NotNull EmployeeDto employeeEntityToDto(@NotNull Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setGender(employee.getGender());
        return employeeDto;
    }

    public static @NotNull List<EmployeeDto> employeeEntityToDto(@NotNull List<Employee> employees) {
        return employees.stream().map(EmployeeConvertor::employeeEntityToDto).collect(Collectors.toList());
    }

    public static @NotNull Employee employeeDtoToEntity(@NotNull EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAge(employeeDto.getAge());
        employee.setGender(employeeDto.getGender());
        return employee;
    }

    public static @NotNull List<Employee> employeeDtoToEntity(@NotNull List<EmployeeDto> employeeDtos) {
        return employeeDtos.stream().map(EmployeeConvertor::employeeDtoToEntity).collect(Collectors.toList());
    }

}
