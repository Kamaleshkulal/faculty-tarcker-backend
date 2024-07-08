package com.kamaleshkulal.facultytrackingsystem.repository;

import com.kamaleshkulal.facultytrackingsystem.model.Admin;
import com.kamaleshkulal.facultytrackingsystem.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, String> {
    int countByCollege_CollegeID(String collegeID);
    boolean existsById(String facultyId);
    boolean existsByFacultyEmail(String facultyEmail);
    boolean existsByFacultyPANCard(String facultyPANCard);
    boolean existsByFacultyAadharCard(String facultyAadharCard);
    Faculty findByFacultyEmailAndFacultyPassword(String facultyEmail, String facultyPassword);
    Faculty findByFacultyEmail(String facultyEmail);
    Faculty findByFacultyPhoneNumber(String facultyPhoneNumber);
}