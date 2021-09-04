package br.com.corpo.em.acao.academia.dto.enrollment.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class EnrollmentCreateDto {

    @ApiModelProperty(name = "Data inicio do pagamento", example = "2021-03-12")
    LocalDateTime start;

    @ApiModelProperty(name = "Data inicio do pagamento", example = "2021-04-12")
    LocalDateTime end;

    @ApiModelProperty(name = "Preço", example = "85.00")
    BigDecimal price;

    @ApiModelProperty(name = "Descrição", example = "Estudante pagou mês 03")
    String description;

    @ApiModelProperty(name = "ID do estudante", example = "20")
    Long studentId;
}
