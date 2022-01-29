package com.codemayur.employee_management_system.controller;

import com.codemayur.employee_management_system.constants.Constants;
import com.codemayur.employee_management_system.dto.EmployeeDto;
import com.codemayur.employee_management_system.exceptions.EmployeeNotFoundException;
import com.codemayur.employee_management_system.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mayur.somani
 */
@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * This API will return the details of all the employees.<br>
     *
     * @return A list of employee details
     */
    @GetMapping("getEmployee")
    @ApiOperation(value = "Get All Employees", notes = "This API will return the details of all the employees")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Constants.GENERIC_ERROR_MSG)
    })
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        Map<String, Object> responseMap = new HashMap<>();
        List<EmployeeDto> employees;
        try {

            employees = employeeService.getAllEmployees();

        } catch (Exception e) {
            log.error(Constants.GENERIC_ERROR_MSG, e);
            responseMap.put(Constants.MESSAGE, e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        responseMap.put("employees", employees);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    /**
     * This API will return the details of an employee by ID if it exists.<br>
     *
     * @param employeeId The identifier of the employee
     * @return An employee's details
     */
    @GetMapping("getEmployee/{employeeId}")
    @ApiOperation(value = "Get Employee By ID", notes = "This API will return the details of an employee by ID if it exists")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Employee Not Found"),
            @ApiResponse(code = 500, message = Constants.GENERIC_ERROR_MSG)
    })
    public ResponseEntity<Map<String, Object>> getEmployee(
            @ApiParam(value = "ID value of the employee you need to retrieve") @PathVariable Long employeeId) {
        Map<String, Object> responseMap = new HashMap<>();
        EmployeeDto employeeDto;
        try {

            employeeDto = employeeService.getEmployeeById(employeeId);

        } catch (EmployeeNotFoundException employeeNotFoundException) {
            log.error(employeeNotFoundException.getMessage(), employeeNotFoundException);
            responseMap.put(Constants.MESSAGE, employeeNotFoundException.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(Constants.GENERIC_ERROR_MSG, e);
            responseMap.put(Constants.MESSAGE, e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("employee", employeeDto);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    /**
     * This API will set the details of an employee.<br>
     *
     * @param employeeDto The details of a new Employee
     * @return employeeId - The identifier of the new employee
     */
    @PostMapping("setEmployee")
    @ApiOperation(value = "Register An Employee", notes = "This API will create a new Employee")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = Constants.GENERIC_ERROR_MSG)})
    public ResponseEntity<Map<String, Object>> setEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        Map<String, Object> responseMap = new HashMap<>();
        Long employeeId;
        try {

            employeeId = employeeService.setEmployee(employeeDto);

        } catch (Exception e) {
            log.error(Constants.GENERIC_ERROR_MSG, e);
            responseMap.put(Constants.MESSAGE, e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("employeeId", employeeId);
        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    }

}
