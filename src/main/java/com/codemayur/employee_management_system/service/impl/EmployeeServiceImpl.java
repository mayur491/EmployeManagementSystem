package com.codemayur.employee_management_system.service.impl;

import com.codemayur.employee_management_system.convertors.EmployeeConvertor;
import com.codemayur.employee_management_system.dto.EmployeeDto;
import com.codemayur.employee_management_system.entity.Employee;
import com.codemayur.employee_management_system.enums.Gender;
import com.codemayur.employee_management_system.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author mayur.somani
 */
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
    public List<EmployeeDto> getAllEmployees() {
        return EmployeeConvertor.employeeEntityToDto(employees);
    }

    @Override
    public Optional<EmployeeDto> getEmployeeById(String employeeId) {
        return employees.stream()
                .filter(tempEmployee -> employeeId.equals(tempEmployee.getId()))
                .map(EmployeeConvertor::employeeEntityToDto)
                .findFirst();
    }

    @Override
    public String setEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeConvertor.employeeDtoToEntity(employeeDto);
        employees.add(employee);
        return employee.getId();
    }

}
