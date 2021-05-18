package br.com.corpo.em.acao.academia.mapper.student;

import br.com.corpo.em.acao.academia.dto.student.StudentDto;
import br.com.corpo.em.acao.academia.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toStudentDto(Student student);
}
