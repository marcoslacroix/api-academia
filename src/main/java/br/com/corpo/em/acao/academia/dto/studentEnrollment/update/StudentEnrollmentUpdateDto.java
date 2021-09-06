package br.com.corpo.em.acao.academia.dto.studentEnrollment.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@With
@AllArgsConstructor
@Getter
@Jacksonized
@Builder
public class StudentEnrollmentUpdateDto {

    @ApiModelProperty(name = "ID do pagamento", example = "1")
    Long id;

    @ApiModelProperty(name = "Data inicio do pagamento", example = "2021-03-12")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate start;

    @ApiModelProperty(name = "Data inicio do pagamento", example = "2021-04-12")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate end;

    @ApiModelProperty(name = "Preço", example = "85.00")
    BigDecimal price;

    @ApiModelProperty(name = "Descrição", example = "Estudante pagou mês 03")
    String description;
}
