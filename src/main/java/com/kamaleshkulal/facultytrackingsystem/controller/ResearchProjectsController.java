package com.kamaleshkulal.facultytrackingsystem.controller;


import com.kamaleshkulal.facultytrackingsystem.model.ResearchProjects;
import com.kamaleshkulal.facultytrackingsystem.service.ResearchProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/projects")
public class ResearchProjectsController {

    @Autowired
    private ResearchProjectsService researchProjectsService;

    @PostMapping("/add")
    public ResearchProjects addProject(@RequestBody ResearchProjects project) {
        return researchProjectsService.saveProject(project);
    }

    @GetMapping("/{projectId}")
    public ResearchProjects getProjectById(@PathVariable int projectId) {
        return researchProjectsService.getProjectById(projectId);
    }

    @PutMapping("/{projectId}")
    public ResearchProjects updateProject(@PathVariable int projectId, @RequestBody ResearchProjects projectDetails) {
        return researchProjectsService.updateProject(projectId, projectDetails);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable int projectId) {
        researchProjectsService.deleteProject(projectId);
    }
}
