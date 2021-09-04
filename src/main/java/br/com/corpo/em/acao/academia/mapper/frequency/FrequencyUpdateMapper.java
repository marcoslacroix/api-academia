package br.com.corpo.em.acao.academia.mapper.frequency;

import br.com.corpo.em.acao.academia.dto.frequency.update.FrequencyUpdateDto;
import br.com.corpo.em.acao.academia.model.Frequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FrequencyUpdateMapper {

    FrequencyUpdateMapper INSTANCE = Mappers.getMapper(FrequencyUpdateMapper.class);

    Frequency toFrequency(FrequencyUpdateDto frequencyUpdateDto);
}
