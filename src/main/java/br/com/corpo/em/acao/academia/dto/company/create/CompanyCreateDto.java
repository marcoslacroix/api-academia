package br.com.corpo.em.acao.academia.dto.company.create;


import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class CompanyCreateDto {

    String name;
}
