package br.com.corpo.em.acao.academia.dto.student.create;

import br.com.corpo.em.acao.academia.model.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class AddressCreateDto {

    @ApiModelProperty(name = "")
    String district;

    String city;

    Long studentId;

    String publicPlace;

    String postCode;

    Student student;

}
