package com.instituto.restapi.Instituto.app.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data

@ApiModel(description = "Student model info" )
public class StudentDTO {

    @ApiModelProperty(value = "Product id")
    private Long id;

    @ApiModelProperty(value = "student's name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @ApiModelProperty(value = "student's name")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email must have a correct format")
    private String email;

    @ApiModelProperty(value = "student's age")
    @NotNull(message = "Age should not be empty")
    private Integer age;


}
