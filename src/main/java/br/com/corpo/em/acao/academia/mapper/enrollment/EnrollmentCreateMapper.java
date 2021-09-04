package br.com.corpo.em.acao.academia.mapper.enrollment;

import br.com.corpo.em.acao.academia.dto.enrollment.create.EnrollmentCreateDto;
import br.com.corpo.em.acao.academia.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnrollmentCreateMapper {

    EnrollmentCreateMapper INSTANCE = Mappers.getMapper(EnrollmentCreateMapper.class);

    Enrollment toEnrollment(EnrollmentCreateDto enrollmentCreateDto);
}
