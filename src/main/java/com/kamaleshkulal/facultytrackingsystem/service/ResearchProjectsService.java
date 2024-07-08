package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.ResearchProjects;

public interface ResearchProjectsService {
    ResearchProjects saveProject(ResearchProjects project);
    ResearchProjects getProjectById(int projectId);
    ResearchProjects updateProject(int projectId, ResearchProjects projectDetails);
    void deleteProject(int projectId);
}
