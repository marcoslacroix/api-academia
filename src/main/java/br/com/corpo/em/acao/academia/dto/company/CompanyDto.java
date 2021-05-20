package br.com.corpo.em.acao.academia.dto.company;

import br.com.corpo.em.acao.academia.dto.student.StudentDto;
import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.model.Student;
import br.com.corpo.em.acao.academia.model.User;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class CompanyDto {

    Long id;

    String name;

    boolean deleted;

    LocalDateTime createdOn;

    LocalDateTime updatedOn;

    List<UserDto> users;

    List<StudentDto> students;
}
