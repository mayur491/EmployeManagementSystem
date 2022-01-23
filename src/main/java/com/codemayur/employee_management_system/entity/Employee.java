package com.codemayur.employee_management_system.entity;

import com.codemayur.employee_management_system.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mayur.somani
 */
//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details of the Employee")
public class Employee {

    //	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The unique ID of the Employee")
    private String id;

    @ApiModelProperty(notes = "The first name of the Employee")
    private String firstName;

    @ApiModelProperty(notes = "The last name of the Employee")
    private String lastName;

    @ApiModelProperty(notes = "The age of the Employee")
    private Integer age;

    @ApiModelProperty(notes = "The Gender of the Employee (MALE/FEMALE/OTHERS)")
    private Gender gender;

}
