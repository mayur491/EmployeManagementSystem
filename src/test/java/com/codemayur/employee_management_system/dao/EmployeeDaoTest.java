package com.codemayur.employee_management_system.dao;

import com.codemayur.employee_management_system.entity.Employee;
import com.codemayur.employee_management_system.enums.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeDaoTest {

    private final EmployeeDao employeeDao;

    @Autowired
    EmployeeDaoTest(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @AfterEach
    void tearDown() {
        employeeDao.deleteAll();
    }

    @Test
    void testFindAllByNameIfExists() {
        // Given
        Employee employee = new Employee(
                "Employee",
                "Employee",
                "employee@codemayur.com",
                101,
                Gender.OTHER
        );
        employeeDao.save(employee);

        // When
        List<Employee> employeeList = employeeDao.findAllByName("Employee", "Employee");

        // Then
        assertThat(employeeList).hasSize(1);
    }

    @Test
    void testFindAllByNameIfNotExists() {
        // Given
        Employee employee = new Employee(
                "Employee2",
                "Employee2",
                "employee2@codemayur.com",
                101,
                Gender.OTHER
        );
        employeeDao.save(employee);

        // When
        List<Employee> employeeList = employeeDao.findAllByName("Employee", "Employee");

        // Then
        assertThat(employeeList).isEmpty();
    }
}