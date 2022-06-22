package com.instituto.restapi.Instituto.app.repository;

import com.instituto.restapi.Instituto.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryStudent extends JpaRepository<Student, Long> {

    List<Student> findBySubjectId(Long subjectId);

}
