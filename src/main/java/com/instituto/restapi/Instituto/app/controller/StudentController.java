package com.instituto.restapi.Instituto.app.controller;

import com.instituto.restapi.Instituto.app.payload.AgeRangeRequest;
import com.instituto.restapi.Instituto.app.payload.StudentDTO;
import com.instituto.restapi.Instituto.app.service.ServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private ServiceStudent serviceStudent;

    @PostMapping("subjects/{subjectId}/students")
    public ResponseEntity<StudentDTO> generateStudent(@Valid @RequestBody StudentDTO studentDTO, @PathVariable(name = "subjectId") Long subjectId){

        return new ResponseEntity<StudentDTO>(serviceStudent.saveStudent(subjectId, studentDTO), HttpStatus.CREATED);
    }

    @GetMapping("subjects/{subjectId}/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents(@PathVariable("subjectId") Long subjectId){

        return new ResponseEntity<List<StudentDTO>>(serviceStudent.getStudentsBySubjectId(subjectId), HttpStatus.OK);
    }

    @GetMapping("subjects/{subjectId}/students/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("subjectId") Long subjectId,
                                                     @PathVariable("studentId") Long studentId){
        return new ResponseEntity<StudentDTO>(serviceStudent.getStudentById(subjectId, studentId), HttpStatus.OK);
    }

    //Get students by age range
    @PostMapping("/subjects/age")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByAgeRange(@RequestBody AgeRangeRequest request){

        return new ResponseEntity<>(serviceStudent.findByStudentRangeAge(request.getStartAge(),
                request.getEndAge()), HttpStatus.OK);
    }

    @PutMapping("subjects/{subjectId}/students/{studentId}")
    public ResponseEntity<StudentDTO> upgradeStudent(@PathVariable("subjectId") Long subjectId,
                                                     @PathVariable("studentId") Long studentId,
                                                     @Valid @RequestBody StudentDTO studentDTO){

        return  new ResponseEntity<StudentDTO>(serviceStudent.upgradeStudent(subjectId,studentId, studentDTO), HttpStatus.OK);
    }

    @DeleteMapping("subjects/{subjectId}/students/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("subjectId") Long subjectId,
                                                    @PathVariable("studentId") Long studentId){
        serviceStudent.deleteById(subjectId, studentId);

        return  new ResponseEntity<String>("Student deleted", HttpStatus.OK);
    }




}
