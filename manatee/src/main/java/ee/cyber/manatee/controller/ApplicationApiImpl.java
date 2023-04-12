package ee.cyber.manatee.controller;


import java.util.List;

import ee.cyber.manatee.dto.InterviewDto;
import ee.cyber.manatee.mapper.InterviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ee.cyber.manatee.api.ApplicationApi;
import ee.cyber.manatee.dto.ApplicationDto;
import ee.cyber.manatee.mapper.ApplicationMapper;
import ee.cyber.manatee.service.ApplicationService;

import static org.springframework.http.HttpStatus.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationApiImpl implements ApplicationApi {

    private final ApplicationMapper applicationMapper;
    private final ApplicationService applicationService;
    private final InterviewMapper interviewMapper;

    @Override
    public ResponseEntity<List<ApplicationDto>> getApplications() {
        val applications = applicationService.getApplications();
        return ResponseEntity.ok(applicationMapper.entitiesToDtoList(applications));

    }

    @Override
    public ResponseEntity<ApplicationDto> addApplication(ApplicationDto applicationDto) {
        val draftApplication = applicationMapper.dtoToEntity(applicationDto);
        val application = applicationService.insertApplication(draftApplication);

        return ResponseEntity.status(CREATED)
                             .body(applicationMapper.entityToDto(application));
    }

    @Override
    public ResponseEntity<ApplicationDto> getApplicationWithId(Integer applicationId){
        try{
            val application = applicationService.getApplicationWithId(applicationId);

            return ResponseEntity.status(OK)
                    .body(applicationMapper.entityToDto(application));
        } catch (IllegalArgumentException exception){
            throw new ResponseStatusException(NOT_FOUND, "Invalid application id", exception);
        }
    }

    @Override
    public ResponseEntity<Void> rejectApplication(Integer applicationId) {
        try {
            applicationService.rejectApplication(applicationId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(NOT_FOUND, "Invalid application id", exception);
        }
    }

    @Override
    public ResponseEntity<InterviewDto> scheduleInterview(Integer applicationId, InterviewDto interviewDto){
        try {
            val draftInterview = interviewMapper.dtoToEntity(interviewDto);
            applicationService.scheduleInterview(applicationId, draftInterview);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(interviewDto);
        } catch (IllegalArgumentException exception){
            throw new ResponseStatusException(NOT_FOUND, "Invalid application id", exception);
        }
    }



}
