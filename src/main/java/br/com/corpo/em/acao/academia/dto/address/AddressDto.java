package br.com.corpo.em.acao.academia.dto.address;

import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class AddressDto {

    Long id;

    String district;

    String city;

    String publicPlace;

    String postalCode;

    boolean deleted;
}
