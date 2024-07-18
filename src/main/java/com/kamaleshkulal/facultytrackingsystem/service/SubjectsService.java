package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Subjects;
import java.util.List;

public interface SubjectsService {
    Subjects saveSubject(Subjects subject);
    Subjects getSubjectById(String subjectId);
    Subjects updateSubject(String subjectId, Subjects subjectDetails);
    void deleteSubject(String subjectId);
    List<Subjects> getAllSubjects();
    String generateUniqueSubjectID(String subjectName);
    List<Subjects> saveSubjects(List<Subjects> subjects);
    void deleteSubjectById(String subjectId);
}