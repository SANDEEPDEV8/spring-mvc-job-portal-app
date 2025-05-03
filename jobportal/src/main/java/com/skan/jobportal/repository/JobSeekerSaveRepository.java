package com.skan.jobportal.repository;

import com.skan.jobportal.entity.JobPostActivity;
import com.skan.jobportal.entity.CandidateProfile;
import com.skan.jobportal.entity.CandidateSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<CandidateSave, Integer> {

    public List<CandidateSave> findByUserId(CandidateProfile userAccountId);

    List<CandidateSave> findByJob(JobPostActivity job);

}
