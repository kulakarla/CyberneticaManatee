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
    public void submitApplicationAndScheduleInterview(){
        val draftCandidate = CandidateDto
                .builder().firstName("Mari").lastName("Maasikas").build();

        val draftCandidate2 = CandidateDto.builder().firstName("Mart").lastName("Minemunni").build();

        val draftApplication = ApplicationDto
                .builder().candidate(draftCandidate).build();

        val draftApplication2 = ApplicationDto
                .builder().candidate(draftCandidate2).build();



        val response = applicationApi.addApplication(draftApplication);

        val response2 = applicationApi.addApplication(draftApplication2);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        val draftInterview = InterviewDto
                .builder().firstName("Teet").lastName("Margna").interviewTime(OffsetDateTime.parse("2017-12-03T10:30:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .interviewType(InterviewTypeDto.INFORMAL).build();

        System.out.println(draftInterview);

        int response1id = response.getBody().getId();

        int response2id = response2.getBody().getId();

        System.out.println("LISATUD ENTITY 1 ID: " + response1id);
        System.out.println("LISATUD ENTITY 2 ID: " + response2id);

        val response3 = applicationApi.scheduleInterview(response2id, draftInterview);

        System.out.println(response3.getBody());

        System.out.println(applicationApi.getApplications());

        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||");

        System.out.println(interviewApi.getInterviews());

        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||");








    }


}
