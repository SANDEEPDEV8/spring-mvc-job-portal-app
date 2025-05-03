package com.skan.jobportal.services;

import com.skan.jobportal.entity.JobPostActivity;
import com.skan.jobportal.entity.CandidateApply;
import com.skan.jobportal.entity.CandidateProfile;
import com.skan.jobportal.repository.JobSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {

    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    public List<CandidateApply> getCandidatesJobs(CandidateProfile userAccountId) {
        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<CandidateApply> getJobCandidates(JobPostActivity job) {
        return jobSeekerApplyRepository.findByJob(job);
    }

    public void addNew(CandidateApply jobSeekerApply) {
        jobSeekerApplyRepository.save(jobSeekerApply);
    }
}
