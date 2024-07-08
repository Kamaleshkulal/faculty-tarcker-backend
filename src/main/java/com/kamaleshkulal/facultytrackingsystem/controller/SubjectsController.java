package com.kamaleshkulal.facultytrackingsystem.controller;

import com.kamaleshkulal.facultytrackingsystem.model.Subjects;
import com.kamaleshkulal.facultytrackingsystem.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/subjects")
public class SubjectsController {

    @Autowired
    private SubjectsService subjectsService;

//    @PostMapping("/add")
//    public Subjects addSubject(@RequestBody Subjects subject) {
//        return subjectsService.saveSubject(subject);
//    }

    @PostMapping("/add")
    public ResponseEntity<Subjects> addSubject(@RequestBody Subjects subject) {
        // Generate subjectID based on subjectName if not already set
        if (subject.getSubjectID() == null || subject.getSubjectID().isEmpty()) {
            subject.setSubjectID(subjectsService.generateUniqueSubjectID(subject.getSubjectName()));
        }

        // Save subject to database via service layer
        Subjects savedSubject = subjectsService.saveSubject(subject);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }


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