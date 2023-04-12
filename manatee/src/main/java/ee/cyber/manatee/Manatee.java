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
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Manatee {

    public static void main(String[] args) {
        SpringApplication.run(Manatee.class, args);
    }

}
