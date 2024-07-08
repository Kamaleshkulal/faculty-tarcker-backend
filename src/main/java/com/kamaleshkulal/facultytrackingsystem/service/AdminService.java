package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Admin;

import java.util.List;


public interface AdminService {
        Admin saveAdmin(Admin admin);

        Admin getAdminById(String adminId);

        Admin updateAdmin(String adminId, Admin adminDetails);

        void deleteAdmin(String adminId);

        Admin getAdminByEmailAndPassword(String adminEmail, String adminPassword);

        List<Admin> getAllAdmins();
}
