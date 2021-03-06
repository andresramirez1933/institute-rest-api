package com.instituto.restapi.Instituto.app.service.impl;

import com.instituto.restapi.Instituto.app.payload.SubjectDTO;
import com.instituto.restapi.Instituto.app.payload.SubjectResponse;
import com.instituto.restapi.Instituto.app.entity.Subject;
import com.instituto.restapi.Instituto.app.exception.ResourceNotFound;
import com.instituto.restapi.Instituto.app.repository.RepositorySubject;
import com.instituto.restapi.Instituto.app.service.ServiceSubject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceSubjectImpl implements ServiceSubject {

    @Autowired
    private RepositorySubject subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubjectDTO generateSubject(SubjectDTO subjectDTO) {

        Subject subject = mapToEntity(subjectDTO);

        Subject savedSubject = subjectRepository.save(subject);


        return mapToDTO(savedSubject);
    }

    @Override
    public SubjectResponse listSubjects(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Subject> subjects = subjectRepository.findAll(pageable);

        List<Subject> listOfSubjects = subjects.getContent();

        List<SubjectDTO> content = listOfSubjects.stream().map(subject -> mapToDTO(subject)).collect(Collectors.toList());

        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setContent(content);
        subjectResponse.setPageNo(subjects.getNumber());
        subjectResponse.setPageSize(subjects.getSize());
        subjectResponse.setTotalElements(subjects.getTotalElements());
        subjectResponse.setTotalPages(subjects.getTotalPages());
        subjectResponse.setLast(subjects.isLast());

        return subjectResponse;
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO, Long id) {

        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFound( "Subject",id));
        subject.setSubjectName(subjectDTO.getSubjectName());
        subject.setProfessorName(subjectDTO.getProfessorName());

        Subject subjectUpdated = subjectRepository.save(subject);

        return mapToDTO(subject);

    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Subject",id));

        return mapToDTO(subject) ;
    }

    @Override
    public void deleteById(Long id) {

        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Subject",id));

        subjectRepository.delete(subject);

    }

    @Override
    public List<SubjectDTO> findByProfessorName(String name) {

        List<Subject> subjects = subjectRepository.findByProfessorName(name);

        return subjects.stream().map(subject -> mapToDTO(subject)).collect(Collectors.toList());

    }

    private SubjectDTO mapToDTO(Subject subject){

        return modelMapper.map(subject, SubjectDTO.class);
    }

    private Subject mapToEntity(SubjectDTO subjectDTO){
        return modelMapper.map(subjectDTO, Subject.class);
    }
}
