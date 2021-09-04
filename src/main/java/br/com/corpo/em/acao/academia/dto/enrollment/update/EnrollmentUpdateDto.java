package br.com.corpo.em.acao.academia.dto.enrollment.update;

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
public class EnrollmentUpdateDto {

    @ApiModelProperty(name = "ID do pagamento", example = "1")
    Long id;

    @ApiModelProperty(name = "Data inicio do pagamento", example = "2021-03-12")
    LocalDateTime start;

    @ApiModelProperty(name = "Data inicio do pagamento", example = "2021-04-12")
    LocalDateTime end;

    @ApiModelProperty(name = "Preço", example = "85.00")
    BigDecimal price;

    @ApiModelProperty(name = "Descrição", example = "Estudante pagou mês 03")
    String description;
}
