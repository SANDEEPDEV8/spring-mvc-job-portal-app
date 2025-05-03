package com.skan.jobportal.controller;


import com.skan.jobportal.entity.JobPostActivity;
import com.skan.jobportal.entity.CandidateProfile;
import com.skan.jobportal.entity.CandidateSave;
import com.skan.jobportal.entity.Users;
import com.skan.jobportal.services.JobPostActivityService;
import com.skan.jobportal.services.JobSeekerProfileService;
import com.skan.jobportal.services.JobSeekerSaveService;
import com.skan.jobportal.services.UsersService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JobSeekerSaveController {

    private final UsersService usersService;
    private final JobSeekerProfileService jobSeekerProfileService;
    private final JobPostActivityService jobPostActivityService;
    private final JobSeekerSaveService jobSeekerSaveService;

    public JobSeekerSaveController(UsersService usersService, JobSeekerProfileService jobSeekerProfileService, JobPostActivityService jobPostActivityService, JobSeekerSaveService jobSeekerSaveService) {
        this.usersService = usersService;
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.jobPostActivityService = jobPostActivityService;
        this.jobSeekerSaveService = jobSeekerSaveService;
    }

    @PostMapping("job-details/save/{id}")
    public String save(@PathVariable("id") int id, CandidateSave jobSeekerSave) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users user = usersService.findByEmail(currentUsername);
            Optional<CandidateProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
            JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);

//            Optional<JobSeekerSave> existingJobSeekerSave = jobSeekerSaveService.findById(jobSeekerSave.getId());
//            if (existingJobSeekerSave.isPresent()) {
//                jobSeekerSave = existingJobSeekerSave.get();
//            }

            CandidateSave jobSeekerSaveToAdd = new CandidateSave();
            if (seekerProfile.isPresent() && jobPostActivity != null) {

                jobSeekerSaveToAdd.setJob(jobPostActivity);
                jobSeekerSaveToAdd.setUserId(seekerProfile.get());
            } else {
                throw new RuntimeException("User not found");
            }
            jobSeekerSaveService.addNew(jobSeekerSaveToAdd);
        }
        return "redirect:/dashboard";
    }

    @GetMapping("saved-jobs")
    public String savedJobs(Model model) {

        List<JobPostActivity> jobPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUserProfile();

        List<CandidateSave> jobSeekerSaveList = jobSeekerSaveService.getCandidatesJob((CandidateProfile) currentUserProfile);
        for (CandidateSave jobSeekerSave : jobSeekerSaveList) {
            jobPost.add(jobSeekerSave.getJob());
        }

        model.addAttribute("jobPost", jobPost);
        model.addAttribute("user", currentUserProfile);

        return "saved-jobs";
    }
}
