package com.kamaleshkulal.facultytrackingsystem.repository;

import com.kamaleshkulal.facultytrackingsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByAdminEmail(String adminEmail);
    Admin findByAdminEmailAndAdminPassword(String adminEmail, String adminPassword);
    List<Admin> findAll();
}
