package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.College;
import com.kamaleshkulal.facultytrackingsystem.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public College saveCollege(College college) {
        return collegeRepository.save(college);
    }

    @Override
    public College updateCollege(String collegeId, College college) {
        Optional<College> collegeOptional = collegeRepository.findById(collegeId);
        if (collegeOptional.isPresent()) {
            College existingCollege = collegeOptional.get();
            existingCollege.setCollegeName(college.getCollegeName());
            existingCollege.setCollegeLogo(college.getCollegeLogo());
            existingCollege.setAddress(college.getAddress());
            existingCollege.setCity(college.getCity());
            existingCollege.setState(college.getState());
            existingCollege.setZipcode(college.getZipcode());
            return collegeRepository.save(existingCollege);
        }
        return null; // Or throw an exception
    }

    @Override
    public College getCollegeById(String collegeId) {
        Optional<College> collegeOptional = collegeRepository.findById(collegeId);
        return collegeOptional.orElse(null);
    }

    @Override
    public void deleteCollegeById(String collegeId) {
        collegeRepository.deleteById(collegeId);
    }

    @Override
    public Optional<College> getCollegeByAdminId(String adminId) {
        // Implement logic to retrieve college by adminId
        // Example:
        // return collegeRepository.findByAdminId(adminId);
        return Optional.empty(); // Placeholder
    }

    @Override
    public boolean existsById(String collegeId) {
        return collegeRepository.existsById(collegeId);
    }
}
