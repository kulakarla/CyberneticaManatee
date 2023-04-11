package ee.cyber.manatee.api;


import ee.cyber.manatee.dto.InterviewDto;
import ee.cyber.manatee.dto.InterviewTypeDto;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ee.cyber.manatee.dto.ApplicationDto;
import ee.cyber.manatee.dto.CandidateDto;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class ApplicationApiTests {

    @Autowired
    private ApplicationApi applicationApi;

    @Autowired
    private InterviewApi interviewApi;


    @Test
    public void submitApplicationWithValidData() {
        val draftCandidate = CandidateDto
                .builder().firstName("Mari").lastName("Maasikas").build();
        val draftApplication = ApplicationDto
                .builder().candidate(draftCandidate).build();

        val response = applicationApi.addApplication(draftApplication);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        val application = response.getBody();
        assertNotNull(application);
        assertNotNull(application.getId());
        assertNotNull(application.getApplicationState());
        assertNotNull(application.getUpdatedOn());

        assertEquals(draftApplication.getCandidate().getFirstName(),
                     application.getCandidate().getFirstName());
        assertEquals(draftApplication.getCandidate().getLastName(),
                     application.getCandidate().getLastName());
    }


    @Test
    public void submitApplicationAndScheduleAnInterviewForIt(){
        val draftCandidate = CandidateDto
                .builder().firstName("Cyber").lastName("Netica").build();

        val draftApplication = ApplicationDto
                .builder().candidate(draftCandidate).build();

        val responseAddApplication = applicationApi.addApplication(draftApplication);

        assertEquals(HttpStatus.CREATED, responseAddApplication.getStatusCode());

        val draftInterview = InterviewDto
                .builder().firstName("Interview").lastName("Guru").interviewTime(OffsetDateTime.parse("2022-04-15T10:30:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .interviewType(InterviewTypeDto.INFORMAL).build();

        int applicationJustAdded = responseAddApplication.getBody().getId();
        val responseScheduleInterview = applicationApi.scheduleInterview(applicationJustAdded, draftInterview);

        assertEquals(HttpStatus.CREATED, responseScheduleInterview.getStatusCode());

        System.out.println(responseScheduleInterview.getBody());

        System.out.println(applicationApi.getApplications());

    }



}
