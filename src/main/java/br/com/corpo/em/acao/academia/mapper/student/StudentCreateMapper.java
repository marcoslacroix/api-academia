package br.com.corpo.em.acao.academia.mapper.student;

import br.com.corpo.em.acao.academia.dto.student.create.StudentCreateDto;
import br.com.corpo.em.acao.academia.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class})
public interface StudentCreateMapper {

    StudentCreateMapper INSTANCE = Mappers.getMapper(StudentCreateMapper.class);

    @Named("removeMask")
    static String removeMask(String cnpj) {
        if (cnpj != null && cnpj.length() > 0) {
            return cnpj.replace(".", "").replace("/", "").replace("-", "");
        } else {
            return cnpj;
        }
    }

    @Mappings({
            @Mapping(target = "createdOn", expression = "java(LocalDateTime.now())"),
            @Mapping(target = "cpf", source = "studentDto.cpf", qualifiedByName = "removeMask")
    })
    Student toStudent(StudentCreateDto studentDto);
}
