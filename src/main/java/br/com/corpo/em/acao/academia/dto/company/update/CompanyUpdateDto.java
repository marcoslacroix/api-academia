package br.com.corpo.em.acao.academia.dto.company.update;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class CompanyUpdateDto {

    String name;

}
