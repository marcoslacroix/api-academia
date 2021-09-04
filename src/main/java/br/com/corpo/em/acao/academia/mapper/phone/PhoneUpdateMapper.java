package br.com.corpo.em.acao.academia.mapper.phone;

import br.com.corpo.em.acao.academia.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneUpdateMapper {

    PhoneUpdateMapper INSTANCE = Mappers.getMapper(PhoneUpdateMapper.class);

    Phone toPhone(PhoneUpdateMapper phoneUpdateDto);
}
