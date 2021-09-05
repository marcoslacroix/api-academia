package br.com.corpo.em.acao.academia.dto.user;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class UserPasswordRequest {

    String newPassword;
    String newPassword2;
    String oldPassword;
    Long id;
}
