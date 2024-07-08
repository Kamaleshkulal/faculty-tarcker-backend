package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.ResearchProjects;
import com.kamaleshkulal.facultytrackingsystem.repository.ResearchProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchProjectsServiceImpl implements ResearchProjectsService {

    @Autowired
    private ResearchProjectsRepository researchProjectsRepository;

    @Override
    public ResearchProjects saveProject(ResearchProjects project) {
        return researchProjectsRepository.save(project);
    }

    @Override
    public ResearchProjects getProjectById(int projectId) {
        return researchProjectsRepository.findById(projectId).orElse(null);
    }

    @Override
    public ResearchProjects updateProject(int projectId, ResearchProjects projectDetails) {
        ResearchProjects project = researchProjectsRepository.findById(projectId).orElse(null);
        if (project != null) {
            // Update project details
            project.setProjectDescription(projectDetails.getProjectDescription());
            project.setFaculty(projectDetails.getFaculty());
            return researchProjectsRepository.save(project);
        }
        return null;
    }

    @Override
    public void deleteProject(int projectId) {
        researchProjectsRepository.deleteById(projectId);
    }
}
