package br.com.corpo.em.acao.academia.mapper.studentFrequency;

import br.com.corpo.em.acao.academia.dto.studentFrequency.create.StudentFrequencyCreateDto;
import br.com.corpo.em.acao.academia.model.StudentFrequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentFrequencyCreateMapper {

    StudentFrequencyCreateMapper INSTANCE = Mappers.getMapper(StudentFrequencyCreateMapper.class);

    StudentFrequency toFrequency(StudentFrequencyCreateDto studentFrequencyCreateDto);
}
