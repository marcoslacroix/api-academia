package br.com.corpo.em.acao.academia.dto.user;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class UserDto {

    Long id;

    String name;

    String login;

    String email;

    LocalDateTime createdOn;

    LocalDateTime updatedOn;

    boolean deleted;
}
