package br.com.corpo.em.acao.academia.dto.address.create;

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

    @ApiModelProperty(name = "Bairro", example = "Praia Brava")
    String district;

    @ApiModelProperty(name = "Cidade", example = "Florianópolis")
    String city;

    @ApiModelProperty(name = "Id do estudante", example = "3")
    Long studentId;

    @ApiModelProperty(name = "Logradouro endereço", example = "Av. tom traugott wildi")
    String publicPlace;

    @ApiModelProperty(name = "CEP", example = "88056-800")
    String postalCode;

}
