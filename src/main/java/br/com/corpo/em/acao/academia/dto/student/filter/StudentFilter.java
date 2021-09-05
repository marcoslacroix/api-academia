package br.com.corpo.em.acao.academia.dto.student.filter;

import br.com.corpo.em.acao.academia.model.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.domain.Sort;

@With
@Value
@Getter
@Builder
@AllArgsConstructor
@Jacksonized
public class StudentFilter {

    @ApiModelProperty(value = "Nome do estudante", example = "Pedro")
    String name;

    @ApiModelProperty(value = "Atributo a ser ordenado", example = "NAME")
    StudentFilterOrderProperty orderBy;
    @ApiModelProperty(value = "Ordenação ascendente ou descendente", example = "DESC")
    Sort.Direction orderDirection;
}
