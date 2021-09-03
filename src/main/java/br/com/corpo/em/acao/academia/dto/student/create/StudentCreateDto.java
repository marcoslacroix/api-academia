package br.com.corpo.em.acao.academia.dto.student.create;

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
public class StudentCreateDto {

    @ApiModelProperty(name = "Nome", example = "Jorge", required = true)
    String name;

    @ApiModelProperty(name = "CPF", example = "052.451.225-95")
    String cpf;

    @ApiModelProperty(name = "Email", example = "jorge@gmail.com")
    String email;

    @ApiModelProperty(name = "Data de nascimento", example = "1995-02-20")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birth;

    @ApiModelProperty(name = "Gênero", example = "Masculino")
    String gender;

    @ApiModelProperty(name = "Profissão", example = "Desenvolvedor")
    String occupation;

    @ApiModelProperty(name = "Objetivo", example = "Perda de peso")
    String objective;
}
