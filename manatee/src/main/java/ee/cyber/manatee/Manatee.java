package ee.cyber.manatee;


import ee.cyber.manatee.api.ApplicationApi;
import ee.cyber.manatee.dto.ApplicationDto;
import ee.cyber.manatee.dto.CandidateDto;
import ee.cyber.manatee.dto.InterviewDto;
import ee.cyber.manatee.dto.InterviewTypeDto;
import ee.cyber.manatee.model.Application;
import ee.cyber.manatee.model.Candidate;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@SpringBootApplication
public class Manatee {

    public static void main(String[] args) {
        SpringApplication.run(Manatee.class, args);
    }

    //LOADING INITIAL DATA
    /*
    @Bean
    public CommandLineRunner demoData(ApplicationApi applicationApi){
        return args -> {
            val draftCandidate = CandidateDto
                    .builder().firstName("Herman").lastName("Elu").build();

            val draftApplication = ApplicationDto
                    .builder().candidate(draftCandidate).build();

            val response = applicationApi.addApplication(draftApplication);

            val draftInterview = InterviewDto
                    .builder().firstName("Teet").lastName("Margna").interviewTime(OffsetDateTime.parse("2022-04-15T10:30:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .interviewType(InterviewTypeDto.INFORMAL).build();


            applicationApi.scheduleInterview(response.getBody().getId(), draftInterview);

        };
    }*/

}
