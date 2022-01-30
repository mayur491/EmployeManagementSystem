package com.codemayur.employee_management_system.dao;

import com.codemayur.employee_management_system.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    @Query("SELECT e " +
            "FROM Employee e " +
            "WHERE e.firstName = ?1 " +
            "AND e.lastName = ?2"
    )
    List<Employee> findAllByName(String firstName, String lastName);

    @Query("SELECT e " +
            "FROM Employee e " +
            "WHERE e.email = ?1 "
    )
    List<Employee> findByEmail(String email);

}
