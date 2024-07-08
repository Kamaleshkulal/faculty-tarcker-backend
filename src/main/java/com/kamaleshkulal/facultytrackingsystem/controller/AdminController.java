package com.kamaleshkulal.facultytrackingsystem.controller;

import com.kamaleshkulal.facultytrackingsystem.model.Admin;
import com.kamaleshkulal.facultytrackingsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/adminapp/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint to get all admins
    @GetMapping("/")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // Endpoint to get admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") String adminId) {
        Admin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // Endpoint to create a new admin
    @PostMapping("/")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }


    // Endpoint to update an existing admin
//    @PutMapping("/{id}")
//    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") String adminId, @RequestBody Admin updatedAdminDetails) {
//        Admin existingAdmin = adminService.getAdminById(adminId);
//
//        if (existingAdmin != null) {
//            existingAdmin.setAdminName(updatedAdminDetails.getAdminName());
//            existingAdmin.setAdminProfile(updatedAdminDetails.getAdminProfile());
//            existingAdmin.setAdminEmail(updatedAdminDetails.getAdminEmail());
//            existingAdmin.setAdminPermanentAddress(updatedAdminDetails.getAdminPermanentAddress());
//            existingAdmin.setAdminResidentialAddress(updatedAdminDetails.getAdminResidentialAddress());
//            existingAdmin.setAdminCity(updatedAdminDetails.getAdminCity());
//            existingAdmin.setAdminPincode(updatedAdminDetails.getAdminPincode());
//            existingAdmin.setAdminFatherName(updatedAdminDetails.getAdminFatherName());
//            existingAdmin.setAdminMotherName(updatedAdminDetails.getAdminMotherName());
//            existingAdmin.setAdminAadharCard(updatedAdminDetails.getAdminAadharCard());
//            existingAdmin.setAdminPassword(updatedAdminDetails.getAdminPassword());
//            existingAdmin.setAdminPhoneNumber(updatedAdminDetails.getAdminPhoneNumber());
//            existingAdmin.setAdminHomeNumber(updatedAdminDetails.getAdminHomeNumber());
//            existingAdmin.setAdminPANCard(updatedAdminDetails.getAdminPANCard());
//
//            if (updatedAdminDetails.getCollege() != null) {
//                existingAdmin.setCollege(updatedAdminDetails.getCollege());
//            }
//            Admin updatedAdmin = adminService.updateAdmin(adminId, existingAdmin);
//            return ResponseEntity.ok(updatedAdmin);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") String adminId, @RequestBody Admin updatedAdminDetails) {
        Admin existingAdmin = adminService.getAdminById(adminId);

        if (existingAdmin != null) {
            // Check if the updated details are the same as existing admin details
            if (isSameAsDefault(existingAdmin, updatedAdminDetails)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return a bad request status
            } else {
                // Update the existing admin with new details
                existingAdmin.setAdminName(updatedAdminDetails.getAdminName());
                existingAdmin.setAdminProfile(updatedAdminDetails.getAdminProfile());
                existingAdmin.setAdminEmail(updatedAdminDetails.getAdminEmail());
                existingAdmin.setAdminPermanentAddress(updatedAdminDetails.getAdminPermanentAddress());
                existingAdmin.setAdminResidentialAddress(updatedAdminDetails.getAdminResidentialAddress());
                existingAdmin.setAdminCity(updatedAdminDetails.getAdminCity());
                existingAdmin.setAdminPincode(updatedAdminDetails.getAdminPincode());
                existingAdmin.setAdminFatherName(updatedAdminDetails.getAdminFatherName());
                existingAdmin.setAdminMotherName(updatedAdminDetails.getAdminMotherName());
                existingAdmin.setAdminAadharCard(updatedAdminDetails.getAdminAadharCard());
                existingAdmin.setAdminPassword(updatedAdminDetails.getAdminPassword());
                existingAdmin.setAdminPhoneNumber(updatedAdminDetails.getAdminPhoneNumber());
                existingAdmin.setAdminHomeNumber(updatedAdminDetails.getAdminHomeNumber());
                existingAdmin.setAdminPANCard(updatedAdminDetails.getAdminPANCard());

                // Update the admin in the database
                Admin updatedAdmin = adminService.updateAdmin(adminId, existingAdmin);
                return ResponseEntity.ok(updatedAdmin);
            }
        } else {
            // Admin with the given ID not found
            return ResponseEntity.notFound().build();
        }
    }

    // Method to check if updated admin details are the same as default values
    private boolean isSameAsDefault(Admin existingAdmin, Admin updatedAdminDetails) {
        return existingAdmin.equals(updatedAdminDetails); // Implement Admin.equals() method appropriately
    }



    // Endpoint to delete an admin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") String adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to add a new admin (Ensure this doesn't conflict with existing mappings)
    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    // Endpoint for admin login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> adminLogin(@RequestBody Admin adminCredentials) {
        String adminEmail = adminCredentials.getAdminEmail();
        String adminPassword = adminCredentials.getAdminPassword();

        Admin admin = adminService.getAdminByEmailAndPassword(adminEmail, adminPassword);

        if (admin != null) {
            System.out.println(admin.getAdminName() + " has logged in");
            // Create a map to hold the response data
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("adminId", admin.getAdminID());
            response.put("adminEmail", admin.getAdminEmail());
            response.put("adminName", admin.getAdminName());
            response.put("collegeId", admin.getCollegeId());
            response.put("collegeCity",admin.getCity());
            response.put("collegeZipcode",admin.getZipcode());
            response.put("collegeLogo", admin.getCollegeLogo());
            response.put("collegeName", admin.getCollegeName());

            return ResponseEntity.ok(response);
        } else {
            System.out.println("Failed login attempt for email: " + adminEmail);
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }



}
