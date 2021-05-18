package br.com.corpo.em.acao.academia.dto.student.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class StudentUpdateDto {

    @ApiModelProperty(name = "ID", example = "1")
    Long id;

    @ApiModelProperty(name = "Nome", example = "Marcos")
    String name;

    @ApiModelProperty(name = "Cpf", example = "052.426.995-95")
    String cpf;

    @ApiModelProperty(name = "Email", example = "jorge@gmail.com")
    String email;

    @ApiModelProperty(name = "Aniversário", example = "1995-02-20")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birth;

    @ApiModelProperty(name = "Gênero", example = "Masculino")
    String gender;

    @ApiModelProperty(name = "Profissão", example = "Developer")
    String occupation;

    @ApiModelProperty(name = "Objetivo", example = "Ganha de massa muscular")
    String objective;


}
