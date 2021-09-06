package br.com.corpo.em.acao.academia.dto.studentFrequency;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class StudentFrequencyDto {

    Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDateTime start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDateTime end;

    Long studentId;

    String note;
}
