package com.codemayur.employee_management_system.entity;

import com.codemayur.employee_management_system.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {

    @Test
    void testNoArgsConstructor() {
        Employee employee = new Employee();
        assertThat(employee.getId()).isNull();
        assertThat(employee.getFirstName()).isNull();
        assertThat(employee.getLastName()).isNull();
        assertThat(employee.getAge()).isNull();
        assertThat(employee.getGender()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        Employee employee = new Employee(1L, "Mayur", "Somani", "mayur.somani@codemayur.com", 26, Gender.MALE);
        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getFirstName()).isEqualTo("Mayur");
        assertThat(employee.getLastName()).isEqualTo("Somani");
        assertThat(employee.getEmail()).isEqualTo("mayur.somani@codemayur.com");
        assertThat(employee.getAge()).isEqualTo(26);
        assertThat(employee.getGender()).isEqualTo(Gender.MALE);
    }

}