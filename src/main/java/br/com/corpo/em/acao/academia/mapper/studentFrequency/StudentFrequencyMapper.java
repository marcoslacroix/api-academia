package br.com.corpo.em.acao.academia.mapper.studentFrequency;

import br.com.corpo.em.acao.academia.dto.studentFrequency.StudentFrequencyDto;
import br.com.corpo.em.acao.academia.model.StudentFrequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentFrequencyMapper {

    StudentFrequencyMapper INSTANCE = Mappers.getMapper(StudentFrequencyMapper.class);

    StudentFrequencyDto toDto(StudentFrequency frequency);
}
