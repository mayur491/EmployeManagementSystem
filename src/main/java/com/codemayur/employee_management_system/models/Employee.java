package com.codemayur.employee_management_system.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details about the Employee")
public class Employee {

	@ApiModelProperty(notes = "The unique ID of the Employee")
	private String id;

	@ApiModelProperty(notes = "The first name of the Employee")
	private String firstName;

	@ApiModelProperty(notes = "The last name of the Employee")
	private String lastName;

	@ApiModelProperty(notes = "The age of the Employee")
	private Integer age;

	@ApiModelProperty(notes = "The Gender of the Employee (MALE/FEMALE)")
	private Gender gender;

}
