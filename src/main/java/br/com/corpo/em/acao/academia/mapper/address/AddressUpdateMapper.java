package br.com.corpo.em.acao.academia.mapper.address;

import br.com.corpo.em.acao.academia.dto.address.update.AddressUpdateDto;
import br.com.corpo.em.acao.academia.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressUpdateMapper {

    AddressUpdateMapper INSTANCE = Mappers.getMapper(AddressUpdateMapper.class);

    Address toAddress(AddressUpdateDto addressUpdateDto);


}
