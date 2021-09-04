package br.com.corpo.em.acao.academia.mapper.frequency;

import br.com.corpo.em.acao.academia.dto.frequency.create.FrequencyCreateDto;
import br.com.corpo.em.acao.academia.model.Frequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FrequencyCreateMapper {

    FrequencyCreateMapper INSTANCE = Mappers.getMapper(FrequencyCreateMapper.class);

    Frequency toFrequency(FrequencyCreateDto frequencyCreateDto);
}
