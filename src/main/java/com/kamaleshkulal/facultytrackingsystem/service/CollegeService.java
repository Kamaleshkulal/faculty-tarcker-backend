package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.College;

import java.util.Optional;


public interface CollegeService {
    College saveCollege(College college);
    College updateCollege(String collegeId, College college);
    College getCollegeById(String collegeId);
    void deleteCollegeById(String collegeId);
    Optional<College> getCollegeByAdminId(String adminId);
    boolean existsById(String collegeId);
}

