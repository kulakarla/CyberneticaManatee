package ee.cyber.manatee.service;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import ee.cyber.manatee.model.Interview;
import ee.cyber.manatee.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import ee.cyber.manatee.model.Application;
import ee.cyber.manatee.repository.ApplicationRepository;
import ee.cyber.manatee.statemachine.ApplicationState;
import ee.cyber.manatee.statemachine.ApplicationStateMachine;

import javax.transaction.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationStateMachine applicationStateMachine;

    private final InterviewRepository interviewRepository;


    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    public Application insertApplication(Application application) {
        application.setId(null);
        application.setApplicationState(ApplicationState.NEW);
        application.setUpdatedOn(OffsetDateTime.now());

        return applicationRepository.save(application);
    }

    public void rejectApplication(Integer applicationId) {
        applicationStateMachine.rejectApplication(applicationId);
    }


    public void scheduleInterview(Integer applicationId, Interview interview) {

        val application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() -> {
                    log.error("Couldn't find the application with given id {}", applicationId);
                    throw new IllegalArgumentException("Invalid application id");
                });

        application.setInterview(interview);
        applicationRepository.save(application);
        applicationStateMachine.scheduleInterview(applicationId);
    }
}
