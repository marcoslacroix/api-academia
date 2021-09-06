package br.com.corpo.em.acao.academia.dto.studentEnrollment;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class StudentEnrollmentDto {

    Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate end;

    BigDecimal price;

    boolean enrollmentLocked = false;

    String description;

    Long studentId;
}
