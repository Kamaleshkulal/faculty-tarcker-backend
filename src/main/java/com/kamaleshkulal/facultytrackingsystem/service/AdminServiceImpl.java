package com.kamaleshkulal.facultytrackingsystem.service;

import com.kamaleshkulal.facultytrackingsystem.model.Admin;
import com.kamaleshkulal.facultytrackingsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(String adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    @Override
    public Admin updateAdmin(String adminId, Admin adminDetails) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            admin.setAdminName(adminDetails.getAdminName());
            admin.setAdminProfile(adminDetails.getAdminProfile());
            admin.setAdminEmail(adminDetails.getAdminEmail());
            admin.setAdminPermanentAddress(adminDetails.getAdminPermanentAddress());
            admin.setAdminResidentialAddress(adminDetails.getAdminResidentialAddress());
            admin.setAdminCity(adminDetails.getAdminCity());
            admin.setAdminPincode(adminDetails.getAdminPincode());
            admin.setAdminFatherName(adminDetails.getAdminFatherName());
            admin.setAdminAadharCard(adminDetails.getAdminAadharCard());
            admin.setAdminMotherName(adminDetails.getAdminMotherName());
            admin.setAdminPassword(adminDetails.getAdminPassword());
            admin.setAdminPhoneNumber(adminDetails.getAdminPhoneNumber());
            admin.setAdminHomeNumber(adminDetails.getAdminHomeNumber());
            admin.setAdminPANCard(adminDetails.getAdminPANCard());
            admin.setCollege(adminDetails.getCollege()); // Ensure college is properly set
            return adminRepository.save(admin);
        }
        return null;
    }

    @Override
    public void deleteAdmin(String adminId) {
        adminRepository.deleteById(adminId);
    }

    @Override
    public Admin getAdminByEmailAndPassword(String adminEmail, String adminPassword) {
        return adminRepository.findByAdminEmailAndAdminPassword(adminEmail, adminPassword);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

}
