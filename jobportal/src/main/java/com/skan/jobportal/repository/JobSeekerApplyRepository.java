package com.skan.jobportal.repository;

import com.skan.jobportal.entity.JobPostActivity;
import com.skan.jobportal.entity.CandidateApply;
import com.skan.jobportal.entity.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<CandidateApply, Integer> {

    List<CandidateApply> findByUserId(CandidateProfile userId);

    List<CandidateApply> findByJob(JobPostActivity job);
}
