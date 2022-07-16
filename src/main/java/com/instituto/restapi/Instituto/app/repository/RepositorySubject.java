package com.instituto.restapi.Instituto.app.repository;

import com.instituto.restapi.Instituto.app.entity.Student;
import com.instituto.restapi.Instituto.app.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorySubject extends JpaRepository<Subject, Long> {

    List<Subject> findByProfessorName(String name);
}
