package com.kamaleshkulal.facultytrackingsystem.controller;

import com.kamaleshkulal.facultytrackingsystem.model.Faculty;
import com.kamaleshkulal.facultytrackingsystem.model.Subjects;
import com.kamaleshkulal.facultytrackingsystem.service.FacultyService;
import com.kamaleshkulal.facultytrackingsystem.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/v1/subjects")
public class SubjectsController {

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private FacultyService facultyService;

//    @PostMapping("/add")
//    public Subjects addSubject(@RequestBody Subjects subject) {
//        return subjectsService.saveSubject(subject);
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Subjects> addSubject(@RequestBody Subjects subject) {
//        // Generate subjectID based on subjectName if not already set
//        if (subject.getSubjectID() == null || subject.getSubjectID().isEmpty()) {
//            subject.setSubjectID(subjectsService.generateUniqueSubjectID(subject.getSubjectName()));
//        }
//
//        // Save subject to database via service layer
//        Subjects savedSubject = subjectsService.saveSubject(subject);
//        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
//    }


    // Constructor injection of services
    public SubjectsController(FacultyService facultyService, SubjectsService subjectsService) {
        this.facultyService = facultyService;
        this.subjectsService = subjectsService;
    }

    // Endpoint to add subjects for a specific faculty
    @PostMapping("/add/{facultyId}")
    public ResponseEntity<?> addSubjects(@PathVariable String facultyId, @RequestBody List<Subjects> subjectsList) {
        // Find the Faculty by facultyId
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build(); // Handle if Faculty with given ID is not found
        }

        // Set the faculty for each subject and generate subjectID if necessary
        for (Subjects subject : subjectsList) {
            subject.setFaculty(faculty);

            if (subject.getSubjectID() == null || subject.getSubjectID().isEmpty()) {
                subject.setSubjectID(subjectsService.generateUniqueSubjectID(subject.getSubjectName()));
            }
        }

        // Save subjects to database via service layer
        List<Subjects> savedSubjects = subjectsService.saveSubjects(subjectsList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubjects);
    }

//    @PostMapping("/add/{facultyId}")
//    public ResponseEntity<List<Subjects>> addSubjects(@PathVariable String facultyId, @RequestBody List<Subjects> subjectsList) {
//        // Find the Faculty by facultyId
//        Faculty faculty = facultyService.getFacultyById(facultyId);
//        if (faculty == null) {
//            return ResponseEntity.notFound().build(); // Handle if Faculty with given ID is not found
//        }
//
//        // Set the faculty for each subject
//        for (Subjects subject : subjectsList) {
//            subject.setFaculty(faculty);
//
//            // Generate subjectID based on subjectName if not already set
//            if (subject.getSubjectID() == null || subject.getSubjectID().isEmpty()) {
//                subject.setSubjectID(subjectsService.generateUniqueSubjectID(subject.getSubjectName()));
//            }
//        }
//
//        // Save subjects to database via service layer
//        List<Subjects> savedSubjects = subjectsService.saveSubjects(subjectsList);
//        return new ResponseEntity<>(savedSubjects, HttpStatus.CREATED);
//    }

    @GetMapping("/{subjectId}")
    public Subjects getSubjectById(@PathVariable String subjectId) {
        return subjectsService.getSubjectById(subjectId);
    }

    @PutMapping("/{subjectId}")
    public Subjects updateSubject(@PathVariable String subjectId, @RequestBody Subjects subjectDetails) {
        return subjectsService.updateSubject(subjectId, subjectDetails);
    }

    @DeleteMapping("/{subjectId}")
    public void deleteSubject(@PathVariable String subjectId) {
        subjectsService.deleteSubject(subjectId);
    }

    @GetMapping("/all")
    public List<Subjects> getAllSubjects() {
        return subjectsService.getAllSubjects();
    }
}