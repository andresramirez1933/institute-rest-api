package com.instituto.restapi.Instituto.app.controller;

import com.instituto.restapi.Instituto.app.payload.SubjectDTO;
import com.instituto.restapi.Instituto.app.payload.SubjectResponse;
import com.instituto.restapi.Instituto.app.service.ServiceSubject;
import com.instituto.restapi.Instituto.app.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SubjectController {

    @Autowired
    private ServiceSubject serviceSubject;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/subjects")
    public ResponseEntity<SubjectDTO> generateASubject(@Valid @RequestBody SubjectDTO subjectDTO){

        return new ResponseEntity<>(serviceSubject.generateSubject(subjectDTO), HttpStatus.CREATED);
    }

    @GetMapping("/subjects")
    public ResponseEntity<SubjectResponse> getAllSubjects(
            @RequestParam(value ="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){

        return new ResponseEntity<SubjectResponse>(serviceSubject.listSubjects(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable("id") Long id){

        return new ResponseEntity<>(serviceSubject.getSubjectById(id), HttpStatus.OK);
    }

    @GetMapping("/subjects/professor/{name}")
    public ResponseEntity<List<SubjectDTO>> getSubjectByProfessor(@PathVariable("name") String name){

        return new ResponseEntity<>(serviceSubject.findByProfessorName(name), HttpStatus.OK);
    }


    @PutMapping("/subjects/{id}")
    public ResponseEntity<SubjectDTO> updateSubject( @PathVariable("id") Long id, @Valid @RequestBody SubjectDTO subjectDTO){
        return new ResponseEntity<>(serviceSubject.updateSubject(subjectDTO, id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long id){
        serviceSubject.deleteById(id);
        return new ResponseEntity<>("Subject Deleted", HttpStatus.OK);
    }

}
