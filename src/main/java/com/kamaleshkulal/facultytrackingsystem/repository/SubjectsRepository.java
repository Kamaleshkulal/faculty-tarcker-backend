package com.kamaleshkulal.facultytrackingsystem.repository;

import com.kamaleshkulal.facultytrackingsystem.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subjects,String> {
    boolean existsBySubjectID(String subjectID);
}