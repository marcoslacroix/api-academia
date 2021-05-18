package br.com.corpo.em.acao.academia.mapper.student;

import br.com.corpo.em.acao.academia.dto.student.update.StudentUpdateDto;
import br.com.corpo.em.acao.academia.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentUpdateMapper {

    StudentUpdateMapper INSTANCE = Mappers.getMapper(StudentUpdateMapper.class);

    Student toStudent(StudentUpdateDto studentUpdateDto);
}
