package com.kamaleshkulal.facultytrackingsystem.controller;

import com.kamaleshkulal.facultytrackingsystem.model.College;
import com.kamaleshkulal.facultytrackingsystem.repository.CollegeRepository;
import com.kamaleshkulal.facultytrackingsystem.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/adminapp/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    // POST endpoint to add a new college
    @PostMapping("")
    public ResponseEntity<College> addCollege(@RequestBody College college) {
        College savedCollege = collegeService.saveCollege(college);
        return new ResponseEntity<>(savedCollege, HttpStatus.CREATED);
    }

    // PUT endpoint to update an existing college by collegeId
    @PutMapping("/{collegeId}")
    public ResponseEntity<College> updateCollege(@PathVariable("collegeId") String collegeId, @RequestBody College college) {
        College updatedCollege = collegeService.updateCollege(collegeId, college);
        if (updatedCollege != null) {
            return new ResponseEntity<>(updatedCollege, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET endpoint to retrieve a college by collegeId
    @GetMapping("/{collegeId}")
    public ResponseEntity<College> getCollegeById(@PathVariable("collegeId") String collegeId) {
        College college = collegeService.getCollegeById(collegeId);
        if (college != null) {
            return new ResponseEntity<>(college, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE endpoint to delete a college by collegeId
    @DeleteMapping("/{collegeId}")
    public ResponseEntity<Void> deleteCollege(@PathVariable("collegeId") String collegeId) {
        collegeService.deleteCollegeById(collegeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{adminId}/college")
    public ResponseEntity<College> getCollegeByAdminId(@PathVariable Long adminId) {
        // Assuming adminId maps to a specific college ID or relationship in your system
        Optional<College> college = collegeService.getCollegeByAdminId(String.valueOf(adminId));
        return college.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
