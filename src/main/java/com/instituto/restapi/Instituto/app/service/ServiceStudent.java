package com.instituto.restapi.Instituto.app.service;

import com.instituto.restapi.Instituto.app.DTO.StudentDTO;
import com.instituto.restapi.Instituto.app.DTO.SubjectDTO;
import com.instituto.restapi.Instituto.app.entity.Student;

import java.util.List;

public interface ServiceStudent {

    StudentDTO saveStudent(Long subjectId, StudentDTO studentDTO);
    List<StudentDTO> getStudentsBySubjectId(Long subjectId);

    StudentDTO getStudentById(Long subjectId, Long studentId);

    StudentDTO upgradeStudent(Long subjectId, Long studentId, StudentDTO studentDTO);

    void deleteById(Long subjectId, Long studentId);
}
