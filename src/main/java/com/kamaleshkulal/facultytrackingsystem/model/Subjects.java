package com.kamaleshkulal.facultytrackingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Random;

@Entity
public class Subjects {
    @Id
    @Column(nullable = false, unique = true)
    private String subjectID;

    @Column(nullable = false)
    private String subjectName;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private int passed;

    @Column(nullable = false)
    private int failed;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonBackReference
    private Faculty faculty;


    @PrePersist
    private void generateSubjectID() {
        if (this.subjectID == null || this.subjectID.isEmpty()) {
            String prefix = subjectName.length() >= 3 ? subjectName.substring(0, 3).toUpperCase() : subjectName.toUpperCase();
            Random random = new Random();
            int randomNumber = random.nextInt(900) + 100; // Generates a random number between 100 and 999
            this.subjectID = prefix + randomNumber;
        }
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
