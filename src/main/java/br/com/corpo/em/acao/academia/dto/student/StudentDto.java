package br.com.corpo.em.acao.academia.dto.student;

import br.com.corpo.em.acao.academia.dto.address.AddressDto;
import br.com.corpo.em.acao.academia.dto.studentEnrollment.StudentEnrollmentDto;
import br.com.corpo.em.acao.academia.dto.studentFrequency.StudentFrequencyDto;
import br.com.corpo.em.acao.academia.dto.phone.PhoneDto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.List;

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

    List<AddressDto> address;

    List<PhoneDto> phones;

    List<StudentFrequencyDto> frequencies;

    List<StudentEnrollmentDto> enrollments;
}
