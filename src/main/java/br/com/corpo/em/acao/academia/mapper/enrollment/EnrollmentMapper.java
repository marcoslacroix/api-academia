package br.com.corpo.em.acao.academia.mapper.enrollment;

import br.com.corpo.em.acao.academia.dto.enrollment.EnrollmentDto;
import br.com.corpo.em.acao.academia.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    EnrollmentDto toDto(Enrollment enrollment);
}
