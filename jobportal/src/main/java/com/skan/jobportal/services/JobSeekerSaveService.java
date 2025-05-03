package com.skan.jobportal.services;

import com.skan.jobportal.entity.JobPostActivity;
import com.skan.jobportal.entity.CandidateProfile;
import com.skan.jobportal.entity.CandidateSave;
import com.skan.jobportal.repository.JobSeekerSaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerSaveService {

    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

    public List<CandidateSave> getCandidatesJob(CandidateProfile userAccountId) {
        return jobSeekerSaveRepository.findByUserId(userAccountId);
    }

    public List<CandidateSave> getJobCandidates(JobPostActivity job) {
        return jobSeekerSaveRepository.findByJob(job);
    }

    public void addNew(CandidateSave jobSeekerSave) {
        jobSeekerSaveRepository.save(jobSeekerSave);
    }

    public Optional<CandidateSave> findById(Integer id) {

        return jobSeekerSaveRepository.findById(id);
    }
}
