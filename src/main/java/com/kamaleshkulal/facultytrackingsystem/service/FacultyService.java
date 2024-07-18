package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Admin;
import com.kamaleshkulal.facultytrackingsystem.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty saveFaculty(Faculty faculty);
    Faculty getFacultyById(String facultyId);
    List<Faculty> getAllFaculties();
    Faculty getFacultyByEmailAndPassword(String facultyEmail, String facultyPassword);
    Faculty updateFaculty(String facultyId, Faculty facultyDetails);
    Faculty updateFacultyCollege(String facultyId, String collegeId);
    String generateFacultyId(String collegeId);
    void deleteFacultyById(String facultyId);
    boolean existsById(String facultyId);
    Faculty findByFacultyEmail(String facultyEmail);
    Faculty findByFacultyPhoneNumber(String facultyPhoneNumber);
    void updateFaculty(Faculty faculty);
}