package br.com.corpo.em.acao.academia.dto.studentFrequency.create;

import io.swagger.annotations.ApiModelProperty;
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
public class StudentFrequencyCreateDto {

    @ApiModelProperty(name = "Data de inicio", example = "2021-01-25T17:09:40.411")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDateTime start;

    @ApiModelProperty(name = "Data de fim", example = "2021-01-25T17:10:40.411")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDateTime end;

    @ApiModelProperty(name = "Id do aluno", example = "20")
    Long studentId;

    @ApiModelProperty(name = "Descrição do treino", example = "Treino A")
    String note;
}
