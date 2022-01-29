package com.codemayur.employee_management_system.service.impl;

import com.codemayur.employee_management_system.convertors.EmployeeConvertor;
import com.codemayur.employee_management_system.dao.EmployeeDao;
import com.codemayur.employee_management_system.dto.EmployeeDto;
import com.codemayur.employee_management_system.entity.Employee;
import com.codemayur.employee_management_system.exceptions.EmployeeNotFoundException;
import com.codemayur.employee_management_system.service.EmployeeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author mayur.somani
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        Iterable<Employee> employees = employeeDao.findAll();
        for (Employee employee: employees) {
            employeeDtos.add(EmployeeConvertor.employeeEntityToDto(employee));
        }
        return employeeDtos;
    }

    @Override
    public @NotNull EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> employeeOptional = employeeDao.findById(employeeId);
        if(employeeOptional.isPresent()) {
            return EmployeeConvertor.employeeEntityToDto(employeeOptional.get());
        } else {
            throw new EmployeeNotFoundException(String.format("Employee with ID '%s' Not Found", employeeId));
        }
    }

    @Override
    public Long setEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeConvertor.employeeDtoToEntity(employeeDto);
        employeeDao.save(employee);
        return employee.getId();
    }

    @Override
    public Long deleteEmployeeById(Long employeeId) {
        employeeDao.deleteById(employeeId);
        return employeeId;
    }

}
