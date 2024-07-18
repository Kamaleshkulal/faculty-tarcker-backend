package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Admin;
import com.kamaleshkulal.facultytrackingsystem.model.College;
import com.kamaleshkulal.facultytrackingsystem.model.Faculty;
import com.kamaleshkulal.facultytrackingsystem.repository.CollegeRepository;
import com.kamaleshkulal.facultytrackingsystem.repository.FacultyRepository;
import com.kamaleshkulal.facultytrackingsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kamaleshkulal.facultytrackingsystem.model.Subjects;

import java.util.List;
import java.util.Optional;


@Service
public class FacultyServiceImpl implements  FacultyService{


    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public Faculty saveFaculty(Faculty faculty) {

        faculty.setCollege(collegeRepository.findById(faculty.getCollege().getCollegeID()).orElse(null));

        if (faculty.getFacultyID() == null || faculty.getFacultyID().isEmpty()) {
            String facultyId = generateFacultyId(faculty.getCollege().getCollegeID());
            faculty.setFacultyID(facultyId);
        }

        if (faculty.getSubjects() != null) {
            for (Subjects subject : faculty.getSubjects()) {
                subject.setFaculty(faculty);
            }
        }

        // Save faculty
        return facultyRepository.save(faculty);
    }




    @Override
    public Faculty getFacultyById(String facultyId) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(facultyId);
        return facultyOptional.orElse(null);
    }


    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public void updateFaculty(Faculty faculty) {
        if (faculty.getFacultyName() == null || faculty.getFacultyName().isEmpty()) {
            throw new IllegalArgumentException("Faculty name must not be null or empty");
        }
        // Proceed with update logic
        facultyRepository.save(faculty);
    }

    @Override
    public Faculty updateFaculty(String facultyId, Faculty facultyDetails) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(facultyId);
        if (facultyOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            faculty.setFacultyName(facultyDetails.getFacultyName());
            faculty.setFacultyEmail(facultyDetails.getFacultyEmail());
            faculty.setFacultyProfile(facultyDetails.getFacultyProfile());
            faculty.setFacultyPermanentAddress(facultyDetails.getFacultyPermanentAddress());
            faculty.setFacultyResidentialAddress(facultyDetails.getFacultyResidentialAddress());
            faculty.setFacultyFatherName(facultyDetails.getFacultyFatherName());
            faculty.setFacultyMotherName(facultyDetails.getFacultyMotherName());
            faculty.setFacultyPANCard(facultyDetails.getFacultyPANCard());
            faculty.setFacultyAadharCard(facultyDetails.getFacultyAadharCard());
            faculty.setFacultyPhoneNumber(facultyDetails.getFacultyPhoneNumber());
            faculty.setFacultyHomeNumber(facultyDetails.getFacultyHomeNumber());
            faculty.setFacultyPassword(facultyDetails.getFacultyPassword());
            faculty.setStudentsTaught(facultyDetails.getStudentsTaught());
            faculty.setSubjectsCount(facultyDetails.getSubjectsCount());
            faculty.setPassRate(facultyDetails.getPassRate());
            faculty.setFailRate(facultyDetails.getFailRate());
            faculty.setRating(facultyDetails.getRating());
            return facultyRepository.save(faculty);
        } else {
            return null;
        }
    }

    @Override
    public Faculty updateFacultyCollege(String facultyId, String collegeId) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(facultyId);
        Optional<College> collegeOptional = collegeRepository.findById(collegeId);
        if (facultyOptional.isPresent() && collegeOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            faculty.setCollege(collegeOptional.get());
            return facultyRepository.save(faculty);
        } else {
            return null;
        }
    }

    @Override
    public boolean existsById(String facultyId) {
        return facultyRepository.existsById(facultyId);
    }

    @Override
    public void deleteFacultyById(String facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    @Override
    public Faculty getFacultyByEmailAndPassword(String facultyEmail, String facultyPassword) {
        return facultyRepository.findByFacultyEmailAndFacultyPassword(facultyEmail, facultyPassword);
    }


    @Override
    public String generateFacultyId(String collegeID) {
        int facultyCount = facultyRepository.countByCollege_CollegeID(collegeID);
        String sequentialNumber = String.format("%03d", facultyCount + 1);
        String temp = "FAC" + collegeID + sequentialNumber;
        // Check if the generated ID already exists
        while (facultyRepository.existsById(temp)) {
            facultyCount++;
            sequentialNumber = String.format("%03d", facultyCount + 1);
            temp = "FAC" + collegeID + sequentialNumber;
        }

        return temp;
    }
    @Override
    public Faculty findByFacultyEmail(String facultyEmail) {
        return facultyRepository.findByFacultyEmail(facultyEmail);
    }

    @Override
    public Faculty findByFacultyPhoneNumber(String facultyPhoneNumber) {
        return facultyRepository.findByFacultyPhoneNumber(facultyPhoneNumber);
    }


}
