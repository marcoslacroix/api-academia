package br.com.corpo.em.acao.academia.mapper.frequency;

import br.com.corpo.em.acao.academia.dto.frequency.FrequencyDto;
import br.com.corpo.em.acao.academia.model.Frequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FrequencyMapper {

    FrequencyMapper INSTANCE = Mappers.getMapper(FrequencyMapper.class);

    FrequencyDto toDto(Frequency frequency);
}
