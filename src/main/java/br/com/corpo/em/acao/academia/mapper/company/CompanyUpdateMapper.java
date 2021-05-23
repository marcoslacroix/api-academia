package br.com.corpo.em.acao.academia.mapper.company;

import br.com.corpo.em.acao.academia.dto.company.update.CompanyUpdateDto;
import br.com.corpo.em.acao.academia.dto.student.update.StudentUpdateDto;
import br.com.corpo.em.acao.academia.mapper.student.StudentUpdateMapper;
import br.com.corpo.em.acao.academia.model.Company;
import br.com.corpo.em.acao.academia.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface CompanyUpdateMapper {

    CompanyUpdateMapper INSTANCE = Mappers.getMapper(CompanyUpdateMapper.class);

    Company toCompany(CompanyUpdateDto companyUpdateDto);


}
