package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Faculty;
import com.kamaleshkulal.facultytrackingsystem.model.ResearchProjects;
import com.kamaleshkulal.facultytrackingsystem.repository.FacultyRepository;
import com.kamaleshkulal.facultytrackingsystem.repository.ResearchProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ResearchProjectsServiceImpl implements ResearchProjectsService {
    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private ResearchProjectsRepository researchProjectsRepository;

    @Override
    public ResearchProjects saveProject(ResearchProjects project) {
        return researchProjectsRepository.save(project);
    }

    @Override
    public ResearchProjects getProjectById(String projectId) {
        return researchProjectsRepository.findById(projectId).orElse(null);
    }

    @Override
    public ResearchProjects updateProject(String projectId, ResearchProjects projectDetails) {
        ResearchProjects project = researchProjectsRepository.findById(projectId).orElse(null);
        if (project != null) {
            // Update project details
            project.setProjectName(projectDetails.getProjectName());

            project.setProjectDescription(projectDetails.getProjectDescription());
            project.setProjectStatus(projectDetails.getProjectStatus());
            project.setFaculty(projectDetails.getFaculty()); // Assuming Faculty is also updated if needed
            return researchProjectsRepository.save(project);
        }
        return null; // Or throw an exception if needed
    }


    @Override
    public void deleteProject(String projectId) {
        researchProjectsRepository.deleteById(projectId);
    }

    @Override
    public List<ResearchProjects> getAllProjects() {
        return researchProjectsRepository.findAll();
    }

    @Override
    public Faculty getFacultyById(String facultyId) {
        return facultyRepository.findById(facultyId).orElse(null);
    }


}