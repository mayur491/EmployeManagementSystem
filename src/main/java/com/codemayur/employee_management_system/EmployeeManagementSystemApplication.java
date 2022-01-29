package com.codemayur.employee_management_system;

import com.codemayur.employee_management_system.dao.EmployeeDao;
import com.codemayur.employee_management_system.entity.Employee;
import com.codemayur.employee_management_system.enums.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mayur.somani
 */
@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(EmployeeDao employeeDao) {
        return (String[] args) -> {
            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee("Mayur", "Somani", 25, Gender.MALE));
            employees.add(new Employee("Reuben", "Fernandes", 25, Gender.MALE));
            employees.add(new Employee("Divya", "Walke", 23, Gender.FEMALE));
            employees.add(new Employee("Prabhat", "Gaikwad", 24, Gender.MALE));
            employees.add(new Employee("Saurabh", "Kanojia", 26, Gender.MALE));
            employees.add(new Employee("Aditya", "Shinde", 24, Gender.MALE));
            employees.add(new Employee("Manoj", "Goregaonkar", 23, Gender.OTHER));
            employeeDao.saveAll(employees);
            employeeDao.findAll().forEach(System.out::println);
        };
    }

}
