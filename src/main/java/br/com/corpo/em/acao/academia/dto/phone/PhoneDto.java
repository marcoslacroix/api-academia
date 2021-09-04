package br.com.corpo.em.acao.academia.dto.phone;

import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class PhoneDto {

    Long id;

    String phoneNumber;

    String ddd;

}
