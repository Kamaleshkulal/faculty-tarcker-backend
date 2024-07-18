package com.kamaleshkulal.facultytrackingsystem.controller;

import com.kamaleshkulal.facultytrackingsystem.model.Faculty;
import com.kamaleshkulal.facultytrackingsystem.model.ResearchProjects;
import com.kamaleshkulal.facultytrackingsystem.service.ResearchProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/v1/projects")
public class ResearchProjectsController {

    @Autowired
    private ResearchProjectsService researchProjectsService;

//    @PostMapping("/add")
//    public ResearchProjects addProject(@RequestBody ResearchProjects project) {
//        return researchProjectsService.saveProject(project);
//    }

    @PostMapping("/add/{facultyId}")
    public ResponseEntity<ResearchProjects> addProject(@PathVariable String facultyId, @RequestBody ResearchProjects project) {
        // Retrieve faculty by facultyId
        Faculty faculty = researchProjectsService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }

        // Set faculty for the project
        project.setFaculty(faculty);

        // Save the project
        ResearchProjects savedProject = researchProjectsService.saveProject(project);
        return ResponseEntity.ok().body(savedProject);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<ResearchProjects> getProjectById(@PathVariable String projectId) {
        ResearchProjects project = researchProjectsService.getProjectById(projectId);
        if (project != null) {
            return ResponseEntity.ok().body(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ResearchProjects> updateProject(@PathVariable String projectId, @RequestBody ResearchProjects projectDetails) {
        ResearchProjects updatedProject = researchProjectsService.updateProject(projectId, projectDetails);
        if (updatedProject != null) {
            return ResponseEntity.ok().body(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{projectId}/{facultyId}")
    public ResponseEntity<ResearchProjects> updateProject(@PathVariable String projectId, @PathVariable String facultyId, @RequestBody ResearchProjects projectDetails) {
        Faculty faculty = researchProjectsService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }

        projectDetails.setFaculty(faculty);
        ResearchProjects updatedProject = researchProjectsService.updateProject(projectId, projectDetails);
        if (updatedProject != null) {
            return ResponseEntity.ok().body(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
        researchProjectsService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResearchProjects>> getAllProjects() {
        List<ResearchProjects> projects = researchProjectsService.getAllProjects();
        if (!projects.isEmpty()) {
            return ResponseEntity.ok().body(projects);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
