package br.com.corpo.em.acao.academia.mapper.enrollment;

import br.com.corpo.em.acao.academia.dto.enrollment.update.EnrollmentUpdateDto;
import br.com.corpo.em.acao.academia.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnrollmentUpdateMapper {

    EnrollmentUpdateMapper INSTANCE = Mappers.getMapper(EnrollmentUpdateMapper.class);

    Enrollment toEnrollment(EnrollmentUpdateDto enrollmentUpdateDto);
}
