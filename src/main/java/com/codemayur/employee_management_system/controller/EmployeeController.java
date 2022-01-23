package com.codemayur.employee_management_system.controller;

import com.codemayur.employee_management_system.constants.Constants;
import com.codemayur.employee_management_system.dto.EmployeeDto;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
     * @return
     */
    @GetMapping("getEmployee")
    @ApiOperation(value = "Get All Employees", notes = "This API will return the details of all the employees")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Constants.GENERIC_ERROR_MSG)
    })
    public ResponseEntity<Map<String, Object>> getEmployee() {
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
     * @param employeeId
     * @return
     */
    @GetMapping("getEmployee/{employeeId}")
    @ApiOperation(value = "Get Employee By ID", notes = "This API will return the details of an employee by ID if it exists")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Employee Not Found"),
            @ApiResponse(code = 500, message = Constants.GENERIC_ERROR_MSG)
    })
    public ResponseEntity<Map<String, Object>> getEmployee(
            @ApiParam(value = "ID value of the employee you need to retrieve") @PathVariable String employeeId) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<EmployeeDto> employeeOptional;
        try {

            employeeOptional = employeeService.getEmployeeById(employeeId);

        } catch (Exception e) {
            log.error(Constants.GENERIC_ERROR_MSG, e);
            responseMap.put(Constants.MESSAGE, e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!employeeOptional.isPresent()) {
            String message = String.format("Employee with ID: '%s' Not Found", employeeId);
            log.error(message);
            responseMap.put(Constants.MESSAGE, message);
            return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
        }

        responseMap.put("employee", employeeOptional.get());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    /**
     * This API will set the details of an employee.<br>
     *
     * @param employeeDto
     * @return
     */
    @PostMapping("setEmployee")
    @ApiOperation(value = "Register An Employee", notes = "This API will return the details of an employee by ID if it exists")
    @ApiResponses(value = {@ApiResponse(code = 201, message = Constants.GENERIC_ERROR_MSG),
            @ApiResponse(code = 500, message = Constants.GENERIC_ERROR_MSG)})
    public ResponseEntity<Map<String, Object>> setEmployee(EmployeeDto employeeDto) {
        Map<String, Object> responseMap = new HashMap<>();
        String employeeId;
        try {

            employeeId = employeeService.setEmployee(employeeDto);

        } catch (Exception e) {
            responseMap.put(Constants.GENERIC_ERROR_MSG, e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        responseMap.put("employeeId", employeeId);
        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    }

}
