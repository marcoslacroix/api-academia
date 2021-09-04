package br.com.corpo.em.acao.academia.dto.phone.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class PhoneCreateDto {

    @ApiModelProperty(name = "Número do telefone", example = "991835224")
    String phoneNumber;

    @ApiModelProperty(name = "DDD", example = "48")
    String ddd;

    @ApiModelProperty(name = "ID do estudante", example = "20")
    Long studentId;
}
