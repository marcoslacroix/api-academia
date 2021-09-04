package br.com.corpo.em.acao.academia.dto.enrollment;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class EnrollmentDto {

    Long id;

    LocalDateTime start;

    LocalDateTime end;

    BigDecimal price;

    boolean enrollmentLocked = false;

    String description;

    Long studentId;
}
