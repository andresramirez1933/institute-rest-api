package com.instituto.restapi.Instituto.app.service;

import com.instituto.restapi.Instituto.app.DTO.SubjectDTO;
import com.instituto.restapi.Instituto.app.DTO.SubjectResponse;

import java.util.List;

public interface ServiceSubject {

    public SubjectDTO generateSubject(SubjectDTO subjectDTO);

    public SubjectResponse listSubjects(int pageNo, int pageSize, String sortBy, String sortDir);

    public SubjectDTO updateSubject(SubjectDTO subjectDTO, Long id);

    public SubjectDTO getSubjectById(Long id);

    public void deleteById(Long id);

}
