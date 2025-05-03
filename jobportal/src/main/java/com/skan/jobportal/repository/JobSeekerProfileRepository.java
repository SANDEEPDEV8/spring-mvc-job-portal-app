package com.skan.jobportal.repository;

import com.skan.jobportal.entity.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepository extends JpaRepository<CandidateProfile, Integer> {
}