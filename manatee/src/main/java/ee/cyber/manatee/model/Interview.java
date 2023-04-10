package ee.cyber.manatee.model;


import ee.cyber.manatee.statemachine.InterviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private OffsetDateTime interviewTime;

    @Enumerated(EnumType.STRING)
    private InterviewType interviewType;


}
