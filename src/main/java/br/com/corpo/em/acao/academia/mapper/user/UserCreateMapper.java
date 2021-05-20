package br.com.corpo.em.acao.academia.mapper.user;

import br.com.corpo.em.acao.academia.dto.user.create.UserCreateDto;
import br.com.corpo.em.acao.academia.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class})
public interface UserCreateMapper {

    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

    @Mappings({
            @Mapping(target = "createdOn", expression = "java(LocalDateTime.now())")
    })
    User toUser(UserCreateDto userCreateDto);

    UserCreateDto toUserCreateDto(User user);
}
