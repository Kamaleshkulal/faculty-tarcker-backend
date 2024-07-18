package com.kamaleshkulal.facultytrackingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faculty {
    @Id
    private String facultyID;

    @Column(nullable = false)
    private String facultyName;

    private String facultyProfile;
    private String facultyPermanentAddress;
    private String facultyResidentialAddress;
    private String facultyFatherName;
    private String facultyMotherName;
    private String facultyPANCard;
    private String facultyAadharCard;
    private String facultyPhoneNumber;
    private String facultyPincode;
    private String facultyCity;
    private String facultyHomeNumber;

    @Column(nullable = false)
    private String facultyPassword;

    private String facultyEmail;
    private Boolean isActive;
    private int studentsTaught;
    private int subjectsCount;
    private double passRate;
    private double failRate;
    private double rating;
    private String status;
    private double attendance;

    @ManyToOne
    @JoinColumn(name = "collegeID")
    @JsonBackReference
    private College college;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subjects> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResearchProjects> researchProjects = new ArrayList<>();

    // Getters and Setters

    public String getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(String facultyID) {
        this.facultyID = facultyID;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyProfile() {
        return facultyProfile;
    }

    public void setFacultyProfile(String facultyProfile) {
        this.facultyProfile = facultyProfile;
    }

    public String getFacultyPermanentAddress() {
        return facultyPermanentAddress;
    }

    public void setFacultyPermanentAddress(String facultyPermanentAddress) {
        this.facultyPermanentAddress = facultyPermanentAddress;
    }

    public String getFacultyResidentialAddress() {
        return facultyResidentialAddress;
    }

    public void setFacultyResidentialAddress(String facultyResidentialAddress) {
        this.facultyResidentialAddress = facultyResidentialAddress;
    }

    public String getFacultyFatherName() {
        return facultyFatherName;
    }

    public void setFacultyFatherName(String facultyFatherName) {
        this.facultyFatherName = facultyFatherName;
    }

    public String getFacultyMotherName() {
        return facultyMotherName;
    }

    public void setFacultyMotherName(String facultyMotherName) {
        this.facultyMotherName = facultyMotherName;
    }

    public String getFacultyPANCard() {
        return facultyPANCard;
    }

    public void setFacultyPANCard(String facultyPANCard) {
        this.facultyPANCard = facultyPANCard;
    }

    public String getFacultyAadharCard() {
        return facultyAadharCard;
    }

    public void setFacultyAadharCard(String facultyAadharCard) {
        this.facultyAadharCard = facultyAadharCard;
    }

    public String getFacultyPhoneNumber() {
        return facultyPhoneNumber;
    }

    public void setFacultyPhoneNumber(String facultyPhoneNumber) {
        this.facultyPhoneNumber = facultyPhoneNumber;
    }

    public String getFacultyPincode() {
        return facultyPincode;
    }

    public void setFacultyPincode(String facultyPincode) {
        this.facultyPincode = facultyPincode;
    }

    public String getFacultyCity() {
        return facultyCity;
    }

    public void setFacultyCity(String facultyCity) {
        this.facultyCity = facultyCity;
    }

    public String getFacultyHomeNumber() {
        return facultyHomeNumber;
    }

    public void setFacultyHomeNumber(String facultyHomeNumber) {
        this.facultyHomeNumber = facultyHomeNumber;
    }

    public String getFacultyPassword() {
        return facultyPassword;
    }

    public void setFacultyPassword(String facultyPassword) {
        this.facultyPassword = facultyPassword;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public int getStudentsTaught() {
        return studentsTaught;
    }

    public void setStudentsTaught(int studentsTaught) {
        this.studentsTaught = studentsTaught;
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public void setSubjectsCount(int subjectsCount) {
        this.subjectsCount = subjectsCount;
    }

    public double getPassRate() {
        return passRate;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
    }

    public double getFailRate() {
        return failRate;
    }

    public void setFailRate(double failRate) {
        this.failRate = failRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public List<ResearchProjects> getResearchProjects() {
        return researchProjects;
    }

    public void setResearchProjects(List<ResearchProjects> researchProjects) {
        this.researchProjects = researchProjects;
    }

    // Transient methods for college details
    @Transient
    public String getCollegeId() {
        return college != null ? college.getCollegeID() : null;
    }

    @Transient
    public String getCollegeName() {
        return college != null ? college.getCollegeName() : null;
    }

    @Transient
    public String getCollegeLogo() {
        return college != null ? college.getCollegeLogo() : null;
    }

    @Transient
    public String getCity() {
        return college != null ? college.getCity() : null;
    }

    @Transient
    public String getZipcode() {
        return college != null ? college.getZipcode() : null;
    }

    @Transient
    public String getState() {
        return college != null ? college.getState() : null;
    }

    @Transient
    public String getAddress() {
        return college != null ? college.getAddress() : null;
    }

    // Standard getters and setters

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
