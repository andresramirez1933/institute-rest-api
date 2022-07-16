package com.instituto.restapi.Instituto.app.repository;

import com.instituto.restapi.Instituto.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryStudent extends JpaRepository<Student, Long> {

    List<Student> findBySubjectId(Long subjectId);

    @Query("SELECT s FROM Student s where s.age BETWEEN :startAge and :endAge")
    List<Student> findStudentByAgeRange (@Param("startAge") Integer startAge, @Param("endAge")Integer endAge);




}
