package br.com.corpo.em.acao.academia.mapper.user;

import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
}
