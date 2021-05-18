package br.com.corpo.em.acao.academia.dto.user.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class UserUpdateDto {

    @ApiModelProperty(name = "ID", example = "1")
    Long id;

    @ApiModelProperty(name = "Nome do usuário", example = "Jose Pedro Rocha")
    String name;

    @ApiModelProperty(name = "Email do usuário", example = "jose@gmail.com")
    String email;
}
