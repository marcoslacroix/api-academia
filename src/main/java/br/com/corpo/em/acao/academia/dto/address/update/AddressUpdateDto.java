package br.com.corpo.em.acao.academia.dto.address.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class AddressUpdateDto {

    @ApiModelProperty(name = "ID", example = "20")
    Long id;

    @ApiModelProperty(name = "Bairro", example = "Praia Brava")
    String district;

    @ApiModelProperty(name = "Cidade", example = "Florianópolis")
    String city;

    @ApiModelProperty(name = "Logradouro endereço", example = "Av. tom traugott wildi")
    String publicPlace;

    @ApiModelProperty(name = "CEP", example = "88056-800")
    String postalCode;
}
