package br.com.corpo.em.acao.academia.mapper.address;

import br.com.corpo.em.acao.academia.dto.address.create.AddressCreateDto;
import br.com.corpo.em.acao.academia.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class})
public interface AddressCreateMapper {

    AddressCreateMapper INSTANCE = Mappers.getMapper(AddressCreateMapper.class);


    @Mappings({
            @Mapping(target = "postalCode", source = "addressCreateDto.postalCode", qualifiedByName = "removeMask"),
            @Mapping(target = "studentId", source = "studentId")
    })
    Address toAddress(AddressCreateDto addressCreateDto, Long studentId);

    @Named("removeMask")
    static String removeMask(String cnpj) {
        if (cnpj != null && cnpj.length() > 0) {
            return cnpj.replace(".", "").replace("/", "").replace("-", "");
        } else {
            return cnpj;
        }
    }
}
