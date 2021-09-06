package br.com.corpo.em.acao.academia.mapper.studentFrequency;

import br.com.corpo.em.acao.academia.dto.studentFrequency.update.StudentFrequencyUpdateDto;
import br.com.corpo.em.acao.academia.model.StudentFrequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentFrequencyUpdateMapper {

    StudentFrequencyUpdateMapper INSTANCE = Mappers.getMapper(StudentFrequencyUpdateMapper.class);

    StudentFrequency toFrequency(StudentFrequencyUpdateDto studentFrequencyUpdateDto);
}
