package br.com.corpo.em.acao.academia.mapper.studentEnrollment;

import br.com.corpo.em.acao.academia.dto.studentEnrollment.update.StudentEnrollmentUpdateDto;
import br.com.corpo.em.acao.academia.model.StudentEnrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentEnrollmentUpdateMapper {

    StudentEnrollmentUpdateMapper INSTANCE = Mappers.getMapper(StudentEnrollmentUpdateMapper.class);

    StudentEnrollment toEnrollment(StudentEnrollmentUpdateDto studentEnrollmentUpdateDto);
}
