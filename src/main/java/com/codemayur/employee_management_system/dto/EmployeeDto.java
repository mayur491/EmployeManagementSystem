package com.codemayur.employee_management_system.dto;

import com.codemayur.employee_management_system.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author mayur.somani
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details of the EmployeeDto")
public class EmployeeDto {

    @ApiModelProperty(notes = "The identifier of the Employee")
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @ApiModelProperty(notes = "The first name of the Employee")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @ApiModelProperty(notes = "The last name of the Employee")
    private String lastName;

    @NotNull(message = "Age is mandatory")
    @ApiModelProperty(notes = "The age of the Employee")
    private Integer age;

    @NotNull(message = "Gender is mandatory")
    @ApiModelProperty(notes = "The Gender of the Employee")
    private Gender gender;

}
