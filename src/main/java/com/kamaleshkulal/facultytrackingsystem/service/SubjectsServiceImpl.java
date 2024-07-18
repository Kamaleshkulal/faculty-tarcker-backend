package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Subjects;
import com.kamaleshkulal.facultytrackingsystem.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class SubjectsServiceImpl implements SubjectsService {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Override
    public Subjects saveSubject(Subjects subject) {
        // Implement logic to save subject, generate unique subjectID if needed
        if (subject.getSubjectID() == null || subject.getSubjectID().isEmpty()) {
            String subjectID = generateUniqueSubjectID(subject.getSubjectName());
            subject.setSubjectID(subjectID);
        }
        return subjectsRepository.save(subject);
    }

    @Override
    public Subjects getSubjectById(String subjectId) {
        return subjectsRepository.findById(subjectId).orElse(null);
    }


    @Override
    public Subjects updateSubject(String subjectId, Subjects subjectDetails) {
        Subjects subject = subjectsRepository.findById(subjectId).orElse(null);
        if (subject != null) {
            subject.setSubjectName(subjectDetails.getSubjectName());
            // Update other fields as needed
            return subjectsRepository.save(subject);
        }
        return null;
    }
    @Override
    public void deleteSubjectById(String subjectId) {
        // Find subject by ID
        Optional<Subjects> optionalSubject = subjectsRepository.findById(subjectId);

        if (optionalSubject.isPresent()) {
            Subjects subject = optionalSubject.get();
            // Perform any necessary business logic before deletion

            // Delete subject from repository
            subjectsRepository.delete(subject);
        } else {
            // Handle case where subject with given ID does not exist
            throw new IllegalArgumentException("Subject with ID " + subjectId + " not found");
        }
    }

    @Override
    public void deleteSubject(String subjectId) {
        subjectsRepository.deleteById(subjectId);
    }

    @Override
    public List<Subjects> getAllSubjects() {
        return subjectsRepository.findAll();
    }

    @Override
    public String generateUniqueSubjectID(String subjectName) {
        // Implement logic to generate unique subjectID
        String prefix = subjectName.substring(0, 3).toUpperCase();
        String uniqueID;
        do {
            String randomCode = String.valueOf(new Random().nextInt(900) + 100); // Generate a random 3-digit number
            uniqueID = prefix + randomCode;
        } while (subjectIDExists(uniqueID));
        return uniqueID;
    }

    private boolean subjectIDExists(String subjectID) {
        return subjectsRepository.existsBySubjectID(subjectID);
    }

    @Override
    public List<Subjects> saveSubjects(List<Subjects> subjects) {
        // Implement your logic to save subjects to the database
        return subjectsRepository.saveAll(subjects);
    }
}

