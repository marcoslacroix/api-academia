package br.com.corpo.em.acao.academia.dto.user.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class UserCreateDto {

    @ApiModelProperty(name = "Nome do usuário", example = "Jose Pedro Rocha")
    String name;

    @ApiModelProperty(name = "Password do usuário", example = "95123649465")
    String password;

    @ApiModelProperty(name = "Login do usuário", example = "jose02")
    String login;

    @ApiModelProperty(name = "Email do usuário", example = "jose@gmail.com")
    String email;

    @ApiModelProperty(name = "Id da empresa", example = "1")
    Long companyId;

}
