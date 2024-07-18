package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Faculty;
import com.kamaleshkulal.facultytrackingsystem.model.ResearchProjects;

import java.util.List;

public interface ResearchProjectsService {
    ResearchProjects saveProject(ResearchProjects project);
    ResearchProjects getProjectById(String projectId);
    ResearchProjects updateProject(String projectId, ResearchProjects projectDetails);
    void deleteProject(String projectId);
    List<ResearchProjects> getAllProjects();
    Faculty getFacultyById(String facultyId);

}
