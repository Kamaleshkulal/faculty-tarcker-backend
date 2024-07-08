package com.kamaleshkulal.facultytrackingsystem.repository;

import com.kamaleshkulal.facultytrackingsystem.model.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, String>  {

}
