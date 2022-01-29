package com.codemayur.employee_management_system.service.impl;

import com.codemayur.employee_management_system.convertors.EmployeeConvertor;
import com.codemayur.employee_management_system.dao.EmployeeDao;
import com.codemayur.employee_management_system.dto.EmployeeDto;
import com.codemayur.employee_management_system.entity.Employee;
import com.codemayur.employee_management_system.enums.Gender;
import com.codemayur.employee_management_system.exceptions.EmployeeNotFoundException;
import com.codemayur.employee_management_system.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDao employeeDao;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Test
    void testGetAllEmployees() {
        // Given
        Long employeeId = 1L;
        Employee employee = new Employee(
                employeeId,
                "Employee",
                "Employee",
                101,
                Gender.OTHER
        );
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        List<EmployeeDto> expectedEmployeeDtoList = EmployeeConvertor.employeeEntityToDto(employeeList);
        given(employeeDao.findAll()).willReturn(employeeList);

        // When
        List<EmployeeDto> actualEmployeeDtoList = employeeService.getAllEmployees();

        // Then
        verify(employeeDao).findAll();
        assertThat(actualEmployeeDtoList).usingRecursiveComparison().isEqualTo(expectedEmployeeDtoList);
    }

    @Test
    void testGetEmployeeByIdWhenExists() {
        // Given
        Long employeeId = 1L;
        Employee employee = new Employee(
                employeeId,
                "Employee",
                "Employee",
                101,
                Gender.OTHER
        );
        EmployeeDto expectedEmployeeDto = EmployeeConvertor.employeeEntityToDto(employee);
        given(employeeDao.findById(anyLong())).willReturn(Optional.of(employee));

        // When
        EmployeeDto actualEmployeeDto = employeeService.getEmployeeById(employeeId);

        // Then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(employeeDao).findById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(employeeId);

        assertThat(actualEmployeeDto).usingRecursiveComparison().isEqualTo(expectedEmployeeDto);
    }

    @Test
    void getEmployeeByIdWhenNotExists() {
        // Given
        Long employeeId = 1L;

        // When

        // Then
        assertThatThrownBy(() -> employeeService.getEmployeeById(employeeId))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage(String.format("Employee with ID '%s' Not Found", employeeId));

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(employeeDao).findById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(employeeId);
    }

    @Test
    void testSetEmployee() {
        // Given
        Employee employee = new Employee(
                "Employee",
                "Employee",
                101,
                Gender.OTHER
        );
        EmployeeDto employeeDto = EmployeeConvertor.employeeEntityToDto(employee);

        // When
        employeeService.setEmployee(employeeDto);

        // Then
        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeDao).save(argumentCaptor.capture());
        Employee capturedEmployee = argumentCaptor.getValue();

        assertThat(capturedEmployee).usingRecursiveComparison().isEqualTo(employee);
    }

    @Test
    void deleteEmployeeById() {
        // Given
        Long employeeId = 1L;

        // When
        Long actualEmployeeId = employeeService.deleteEmployeeById(employeeId);

        // Then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(employeeDao).deleteById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(employeeId);

        assertThat(actualEmployeeId).isEqualTo(employeeId);
    }
}