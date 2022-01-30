package com.codemayur.employee_management_system.entity;

import com.codemayur.employee_management_system.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mayur.somani
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details of the Employee")
public class Employee {

    public Employee(String firstName, String lastName, String email, Integer age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The unique ID of the Employee")
    private Long id;

    @ApiModelProperty(notes = "The first name of the Employee")
    private String firstName;

    @ApiModelProperty(notes = "The last name of the Employee")
    private String lastName;

    @ApiModelProperty(notes = "The email of the Employee")
    private String email;

    @ApiModelProperty(notes = "The age of the Employee")
    private Integer age;

    @ApiModelProperty(notes = "The Gender of the Employee")
    private Gender gender;

}
