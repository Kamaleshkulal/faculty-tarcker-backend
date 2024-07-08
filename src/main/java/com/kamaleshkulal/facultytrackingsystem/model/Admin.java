package com.kamaleshkulal.facultytrackingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Admin {

    @Id
    @Column(length = 10) // Adjust the length as per your requirement
    private String adminID;

    @Column(nullable = false)
    private String adminName;

    private String adminProfile;

    @Column(nullable = false, unique = true)
    private String adminEmail;

    private String adminPermanentAddress;
    private String adminResidentialAddress;
    private String adminCity;
    private String adminPincode;
    private String adminFatherName;
    private String adminMotherName;

    @Column(nullable = false) // Ensure Aadhar card is mandatory
    private String adminAadharCard;

    @Column(nullable = false)
    private String adminPassword;

    private String adminPhoneNumber;
    private String adminHomeNumber;
    private String adminPANCard;

    @ManyToOne
    @JoinColumn(name = "collegeID")
    @JsonBackReference // Prevents infinite recursion in JSON serialization
    private College college;

//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
//    private List<AdminLoginLogout> loginLogoutEvents;

    // Transient methods to get related college details

    @Transient
    public String getCollegeName() {
        return college != null ? college.getCollegeName() : null;
    }

    @Transient
    public String getCollegeId() {
        return college != null ? college.getCollegeID() : null;
    }

    @Transient
    public String getCollegeLogo() {
        return college != null ? college.getCollegeLogo() : null;
    }

    @Transient
    public String getZipcode(){
        return college != null ? college.getZipcode() : null;
    }

    @Transient
    public  String getCity(){
        return college != null ? college.getCity() : null;
    }

    // Getters and setters

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminProfile() {
        return adminProfile;
    }

    public void setAdminProfile(String adminProfile) {
        this.adminProfile = adminProfile;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPermanentAddress() {
        return adminPermanentAddress;
    }

    public void setAdminPermanentAddress(String adminPermanentAddress) {
        this.adminPermanentAddress = adminPermanentAddress;
    }

    public String getAdminResidentialAddress() {
        return adminResidentialAddress;
    }

    public void setAdminResidentialAddress(String adminResidentialAddress) {
        this.adminResidentialAddress = adminResidentialAddress;
    }

    public String getAdminCity() {
        return adminCity;
    }

    public void setAdminCity(String adminCity) {
        this.adminCity = adminCity;
    }

    public String getAdminPincode() {
        return adminPincode;
    }

    public void setAdminPincode(String adminPincode) {
        this.adminPincode = adminPincode;
    }

    public String getAdminFatherName() {
        return adminFatherName;
    }

    public void setAdminFatherName(String adminFatherName) {
        this.adminFatherName = adminFatherName;
    }

    public String getAdminMotherName() {
        return adminMotherName;
    }

    public void setAdminMotherName(String adminMotherName) {
        this.adminMotherName = adminMotherName;
    }

    public String getAdminAadharCard() {
        return adminAadharCard;
    }

    public void setAdminAadharCard(String adminAadharCard) {
        this.adminAadharCard = adminAadharCard;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminHomeNumber() {
        return adminHomeNumber;
    }

    public void setAdminHomeNumber(String adminHomeNumber) {
        this.adminHomeNumber = adminHomeNumber;
    }

    public String getAdminPANCard() {
        return adminPANCard;
    }

    public void setAdminPANCard(String adminPANCard) {
        this.adminPANCard = adminPANCard;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

//    public List<AdminLoginLogout> getLoginLogoutEvents() {
//        return loginLogoutEvents;
//    }
//
//    public void setLoginLogoutEvents(List<AdminLoginLogout> loginLogoutEvents) {
//        this.loginLogoutEvents = loginLogoutEvents;
//    }
}
