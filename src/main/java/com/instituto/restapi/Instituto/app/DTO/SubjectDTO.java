package com.instituto.restapi.Instituto.app.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Subject model info")
@Data
public class SubjectDTO {

    @ApiModelProperty(value = "Subject Id")
    private Long id;

    @ApiModelProperty(value = "Subject professor's name")
    @NotEmpty(message = "Professor name should not be empty")
    private String professorName;

    @ApiModelProperty(value = "Subject subject name")
    @NotEmpty(message = "Professor name should not be empty")
    @Size(min = 3, message = "Subject must have a least three characters")
    private String subjectName;

    private Set<StudentDTO> students;
}
