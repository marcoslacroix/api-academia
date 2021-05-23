package br.com.corpo.em.acao.academia.mapper.company;

import br.com.corpo.em.acao.academia.dto.company.CompanyDto;
import br.com.corpo.em.acao.academia.dto.company.create.CompanyCreateDto;
import br.com.corpo.em.acao.academia.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class})
public interface CompanyCreateMapper {

    CompanyCreateMapper INSTANCE = Mappers.getMapper(CompanyCreateMapper.class);

    @Mapping(target = "createdOn", expression = "java(LocalDateTime.now())")
    Company toCompany(CompanyCreateDto companyCreateDto);

    CompanyCreateDto toCompanyCreateDto(Company company);
}
