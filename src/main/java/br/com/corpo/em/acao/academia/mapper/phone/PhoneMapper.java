package br.com.corpo.em.acao.academia.mapper.phone;

import br.com.corpo.em.acao.academia.dto.phone.PhoneDto;
import br.com.corpo.em.acao.academia.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    PhoneDto toDto(Phone phone);
}
