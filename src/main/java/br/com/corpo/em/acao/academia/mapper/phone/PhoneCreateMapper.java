package br.com.corpo.em.acao.academia.mapper.phone;

import antlr.StringUtils;
import br.com.corpo.em.acao.academia.dto.phone.create.PhoneCreateDto;
import br.com.corpo.em.acao.academia.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {StringUtils.class})
public interface PhoneCreateMapper {

    PhoneCreateMapper INSTANCE = Mappers.getMapper(PhoneCreateMapper.class);
    @Mappings({

            @Mapping(target = "phoneNumber", source = "phoneCreateDto.phoneNumber"),
            @Mapping(target = "studentId", source = "studentId")
    })
    Phone toPhone(PhoneCreateDto phoneCreateDto, Long studentId);

}
