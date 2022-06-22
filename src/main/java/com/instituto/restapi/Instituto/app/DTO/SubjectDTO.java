package com.instituto.restapi.Instituto.app.DTO;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SubjectDTO {

    private Long id;

    @NotEmpty(message = "Professor name should not be empty")
    private String professorName;

    @NotEmpty(message = "Professor name should not be empty")
    @Size(min = 3, message = "Subject must have a least three characters")
    private String subjectName;
    private Set<StudentDTO> students;
}
