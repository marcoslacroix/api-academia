package br.com.corpo.em.acao.academia.mapper.studentEnrollment;

import br.com.corpo.em.acao.academia.dto.studentEnrollment.StudentEnrollmentDto;
import br.com.corpo.em.acao.academia.model.StudentEnrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentEnrollmentMapper {

    StudentEnrollmentMapper INSTANCE = Mappers.getMapper(StudentEnrollmentMapper.class);

    StudentEnrollmentDto toDto(StudentEnrollment enrollment);
}
