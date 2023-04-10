package ee.cyber.manatee.model;


import java.time.OffsetDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import ee.cyber.manatee.statemachine.ApplicationState;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ApplicationState applicationState;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Candidate candidate;

    @NotNull
    private OffsetDateTime updatedOn;

    @OneToOne
    private Interview interview;


    public void setInterview(Interview interview) {
        this.interview = interview;
    }
}
