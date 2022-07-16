package com.instituto.restapi.Instituto.app.service;

import com.instituto.restapi.Instituto.app.payload.SubjectDTO;
import com.instituto.restapi.Instituto.app.payload.SubjectResponse;

import java.util.List;

public interface ServiceSubject {

    SubjectDTO generateSubject(SubjectDTO subjectDTO);

    SubjectResponse listSubjects(int pageNo, int pageSize, String sortBy, String sortDir);

    SubjectDTO updateSubject(SubjectDTO subjectDTO, Long id);

    SubjectDTO getSubjectById(Long id);

    void deleteById(Long id);

    List<SubjectDTO> findByProfessorName(String name);



}
