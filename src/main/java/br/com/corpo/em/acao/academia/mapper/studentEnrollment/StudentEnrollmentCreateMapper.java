package br.com.corpo.em.acao.academia.mapper.studentEnrollment;

import br.com.corpo.em.acao.academia.dto.studentEnrollment.create.StudentEnrollmentCreateDto;
import br.com.corpo.em.acao.academia.model.StudentEnrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentEnrollmentCreateMapper {

    StudentEnrollmentCreateMapper INSTANCE = Mappers.getMapper(StudentEnrollmentCreateMapper.class);

    StudentEnrollment toEnrollment(StudentEnrollmentCreateDto studentEnrollmentCreateDto);
}
