package br.com.corpo.em.acao.academia.dto.phone.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class PhoneUpdateDto {

    @ApiModelProperty(name = "Id do telene", example = "1")
    Long id;

    @ApiModelProperty(name = "Número do telefone", example = "991835224")
    String phoneNumber;

    @ApiModelProperty(name = "DDD", example = "48")
    String ddd;
}
