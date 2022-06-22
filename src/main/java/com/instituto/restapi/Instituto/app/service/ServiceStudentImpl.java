package com.instituto.restapi.Instituto.app.service;

import com.instituto.restapi.Instituto.app.DTO.StudentDTO;
import com.instituto.restapi.Instituto.app.DTO.SubjectDTO;
import com.instituto.restapi.Instituto.app.entity.Student;
import com.instituto.restapi.Instituto.app.entity.Subject;
import com.instituto.restapi.Instituto.app.exception.InstituteAPIException;
import com.instituto.restapi.Instituto.app.exception.ResourceNotFound;
import com.instituto.restapi.Instituto.app.repository.RepositoryStudent;
import com.instituto.restapi.Instituto.app.repository.RepositorySubject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServiceStudentImpl  implements ServiceStudent {

    @Autowired
    private RepositorySubject repositorySubject;

    @Autowired
    private RepositoryStudent repositoryStudent;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDTO saveStudent(Long subjectId, StudentDTO studentDTO) {

        Subject subject  = repositorySubject.findById(subjectId).orElseThrow(() -> new ResourceNotFound("Subject",subjectId));

        Student student = toEntity(studentDTO);
        student.setSubject(subject);

        return toDTO(repositoryStudent.save(student));
    }

    @Override
    public List<StudentDTO> getStudentsBySubjectId(Long subjectId) {

        List<Student> students =  repositoryStudent.findBySubjectId(subjectId);

        return students.stream().map(student -> toDTO(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long subjectId, Long studentId) {

        Subject subject  = repositorySubject.findById(subjectId).orElseThrow(() -> new ResourceNotFound("Subject",subjectId));

        Student student = repositoryStudent.findById(studentId).orElseThrow(() -> new ResourceNotFound("Student",subjectId));

        if(!student.getSubject().getId().equals(subject.getId())){
            throw new InstituteAPIException(HttpStatus.BAD_REQUEST, "Student doesn't belong to that class");
        }

        return toDTO(student);

    }

    @Override
    public StudentDTO upgradeStudent(Long subjectId, Long studentId, StudentDTO studentDTO) {

        Subject subject = repositorySubject.findById(subjectId).orElseThrow(() -> new ResourceNotFound("Subject",subjectId));

        Student student = repositoryStudent.findById(subjectId).orElseThrow(() -> new ResourceNotFound("Student",subjectId));

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());

        Student studentUpgraded = repositoryStudent.save(student);

        return toDTO(studentUpgraded);
    }

    @Override
    public void deleteById(Long subjectId, Long studentId) {

        Subject subject = repositorySubject.findById(subjectId).orElseThrow(() -> new ResourceNotFound("Subject",subjectId));

        Student student = repositoryStudent.findById(subjectId).orElseThrow(() -> new ResourceNotFound("Student",subjectId));

        repositoryStudent.deleteById(studentId);

    }


    private StudentDTO toDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }

    private Student toEntity(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, Student.class);
    }

}
