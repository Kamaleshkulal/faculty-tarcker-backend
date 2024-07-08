package com.kamaleshkulal.facultytrackingsystem.controller;

import com.kamaleshkulal.facultytrackingsystem.model.Faculty;
import com.kamaleshkulal.facultytrackingsystem.model.ResearchProjects;
import com.kamaleshkulal.facultytrackingsystem.model.Subjects;
import com.kamaleshkulal.facultytrackingsystem.service.CollegeService;
import com.kamaleshkulal.facultytrackingsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8000"})
@RequestMapping("/api/v1/adminapp/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private CollegeService collegeService;

    // Endpoint to add a new faculty
    @PostMapping("/add")
    public ResponseEntity<?> addFaculty(@RequestBody Faculty faculty) {
        // Check if collegeId exists
        boolean collegeExists = collegeService.existsById(faculty.getCollege().getCollegeID());
        if (!collegeExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("College with ID " + faculty.getCollege().getCollegeID() + " does not exist.");
        }


        // Check if facultyEmail already exists
        Faculty existingFacultyByEmail = facultyService.findByFacultyEmail(faculty.getFacultyEmail());
        if (existingFacultyByEmail != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Faculty with email " + faculty.getFacultyEmail() + " already exists.");
        }

        // Check if facultyPhoneNumber already exists
        Faculty existingFacultyByPhoneNumber = facultyService.findByFacultyPhoneNumber(faculty.getFacultyPhoneNumber());
        if (existingFacultyByPhoneNumber != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Faculty with phone number " + faculty.getFacultyPhoneNumber() + " already exists.");
        }

        // Generate faculty ID if not provided
        if (faculty.getFacultyID() == null || faculty.getFacultyID().isEmpty()) {
            String facultyId = facultyService.generateFacultyId(faculty.getCollege().getCollegeID());
            faculty.setFacultyID(facultyId);
        }

        // Validate maximum subjects
        if (faculty.getSubjects().size() > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cannot add more than 5 subjects for a faculty.");
        }

        // Save faculty
        Faculty savedFaculty = facultyService.saveFaculty(faculty);
        return ResponseEntity.ok(savedFaculty);
    }

    // Endpoint to check if collegeId exists
    @GetMapping("/add/{collegeId}")
    public ResponseEntity<?> checkCollegeExists(@PathVariable("collegeId") String collegeId) {
        boolean collegeExists = collegeService.existsById(collegeId);
        if (collegeExists) {
            return ResponseEntity.ok("College with ID " + collegeId + " exists.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("College with ID " + collegeId + " does not exist.");
        }
    }

    // Endpoint to fetch a faculty by ID
    @GetMapping("/{facultyId}")
    public Faculty getFacultyById(@PathVariable String facultyId) {
        return facultyService.getFacultyById(facultyId);
    }

    // Endpoint to fetch all faculties
    @GetMapping("/all")
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    // Endpoint to update faculty details
    // Endpoint to update faculty details including subjects
    @PutMapping("/update/{facultyId}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable String facultyId, @RequestBody Faculty updatedFaculty) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty != null) {
            // Update faculty details
            faculty.setFacultyName(updatedFaculty.getFacultyName());
            faculty.setFacultyProfile(updatedFaculty.getFacultyProfile());
            faculty.setFacultyPermanentAddress(updatedFaculty.getFacultyPermanentAddress());
            faculty.setFacultyResidentialAddress(updatedFaculty.getFacultyResidentialAddress());
            faculty.setFacultyFatherName(updatedFaculty.getFacultyFatherName());
            faculty.setFacultyMotherName(updatedFaculty.getFacultyMotherName());
            faculty.setFacultyPANCard(updatedFaculty.getFacultyPANCard());
            faculty.setFacultyAadharCard(updatedFaculty.getFacultyAadharCard());
            faculty.setFacultyPhoneNumber(updatedFaculty.getFacultyPhoneNumber());
            faculty.setFacultyHomeNumber(updatedFaculty.getFacultyHomeNumber());
            faculty.setFacultyPassword(updatedFaculty.getFacultyPassword());
            faculty.setActive(updatedFaculty.getActive());
            faculty.setStatus(updatedFaculty.getStatus());
            faculty.setStudentsTaught(updatedFaculty.getStudentsTaught());
            faculty.setSubjectsCount(updatedFaculty.getSubjectsCount());
            faculty.setPassRate(updatedFaculty.getPassRate());
            faculty.setFailRate(updatedFaculty.getFailRate());
            faculty.setRating(updatedFaculty.getRating());
            faculty.setAttendance(updatedFaculty.getAttendance());

            // Update subjects (assuming facultySubjects are updated directly in the Faculty object)
            List<Subjects> updatedSubjects = updatedFaculty.getSubjects();
            for (Subjects updatedSubject : updatedSubjects) {
                // Find the matching subject in existing faculty subjects list or add new subjects if not found
                boolean subjectFound = false;
                for (Subjects facultySubject : faculty.getSubjects()) {
                    if (facultySubject.getSubjectID().equals(updatedSubject.getSubjectID())) {
                        // Update existing subject details
                        facultySubject.setSubjectName(updatedSubject.getSubjectName());
                        facultySubject.setTotal(updatedSubject.getTotal());
                        facultySubject.setPassed(updatedSubject.getPassed());
                        facultySubject.setFailed(updatedSubject.getFailed());
                        subjectFound = true;
                        break;
                    }
                }
                if (!subjectFound) {
                    // Add new subject if not found in existing list
                    faculty.getSubjects().add(updatedSubject);
                }
            }

            List<ResearchProjects> updatedResearchProjects = updatedFaculty.getResearchProjects();
            for (ResearchProjects updatedProject : updatedResearchProjects) {
                boolean projectFound = false;
                for (ResearchProjects facultyProject : faculty.getResearchProjects()) {
                    if (facultyProject.getProjectID().equals(updatedProject.getProjectID())) {
                        facultyProject.setProjectName(updatedProject.getProjectName());
                        facultyProject.setProjectDescription(updatedProject.getProjectDescription());
                        facultyProject.setProjectStatus(updatedProject.getProjectStatus());
                        projectFound = true;
                        break;
                    }
                }
                if (!projectFound) {
                    faculty.getResearchProjects().add(updatedProject);
                }
            }

            // Save updated faculty
            Faculty savedFaculty = facultyService.updateFaculty(facultyId, faculty);
            return ResponseEntity.ok(savedFaculty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{facultyId}/projects")
    public ResponseEntity<?> getResearchProjectsByFacultyId(@PathVariable String facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty != null) {
            List<ResearchProjects> researchProjects = faculty.getResearchProjects();
            return ResponseEntity.ok(researchProjects);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Faculty with ID " + facultyId + " not found.");
        }
    }

    // Endpoint to update faculty college association
    @PutMapping("/update-college/{facultyId}")
    public Faculty updateFacultyCollege(@PathVariable String facultyId, @RequestParam String collegeId) {
        return facultyService.updateFacultyCollege(facultyId, collegeId);
    }

    // Endpoint to delete a faculty by ID
    @DeleteMapping("/{facultyId}")
    public ResponseEntity<?> deleteFaculty(@PathVariable String facultyId) {
        boolean exists = facultyService.existsById(facultyId);
        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Faculty with ID " + facultyId + " does not exist.");
        }
        facultyService.deleteFacultyById(facultyId);
        return ResponseEntity.ok("Faculty with ID " + facultyId + " has been deleted.");
    }

    // Endpoint for faculty login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> facultyLogin(@RequestBody Faculty facultyCredentials) {
        String facultyEmail = facultyCredentials.getFacultyEmail();
        String facultyPassword = facultyCredentials.getFacultyPassword();

        Faculty faculty = facultyService.getFacultyByEmailAndPassword(facultyEmail, facultyPassword);
        if (faculty != null) {
            System.out.println(faculty.getFacultyName() + " has logged in");
            // Create a map to hold the response data
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("facultyId", faculty.getFacultyID());
            response.put("facultyEmail", faculty.getFacultyEmail());
            response.put("facultyName", faculty.getFacultyName());
            response.put("collegeId", faculty.getCollegeId());
            response.put("collegeLogo", faculty.getCollegeLogo());
            response.put("collegeName", faculty.getCollegeName());
            response.put("collegeCity", faculty.getCity());
            response.put("collegePincode", faculty.getZipcode());
            response.put("collegeAddress",faculty.getAddress());
            response.put("collegeState",faculty.getState());


            return ResponseEntity.ok(response);
        } else {
            System.out.println("Failed login attempt for email: " + facultyEmail);
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    @GetMapping("/college/{facultyId}")
    public ResponseEntity<?> getCollegeIdByFacultyId(@PathVariable String facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty != null) {
            String collegeId = faculty.getCollege().getCollegeID();
            Map<String, String> response = new HashMap<>();
            response.put("collegeId", collegeId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Faculty with ID " + facultyId + " not found.");
        }
    }

}
