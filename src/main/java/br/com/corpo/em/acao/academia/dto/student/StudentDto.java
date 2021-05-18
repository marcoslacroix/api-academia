package br.com.corpo.em.acao.academia.dto.student;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class StudentDto {

    Long id;

    String name;

    String cpf;

    String email;

    LocalDate birth;

    String gender;

    String occupation;

    String objective;

    String createdOn;

    String updatedOn;

    boolean deleted;

    boolean activeEnrollment;
}
