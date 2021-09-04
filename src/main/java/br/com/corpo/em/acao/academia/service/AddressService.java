package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.address.AddressDto;
import br.com.corpo.em.acao.academia.dto.address.create.AddressCreateDto;
import br.com.corpo.em.acao.academia.dto.address.update.AddressUpdateDto;
import br.com.corpo.em.acao.academia.mapper.address.AddressCreateMapper;
import br.com.corpo.em.acao.academia.mapper.address.AddressMapper;
import br.com.corpo.em.acao.academia.model.Address;
import br.com.corpo.em.acao.academia.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final StudentService studentService;

    @Transactional
    public AddressDto create(AddressCreateDto addressCreateDto) {
        studentService.findById(addressCreateDto.getStudentId());
        verifyCepIsValid(addressCreateDto.getPostalCode());
        Address address = AddressCreateMapper.INSTANCE.toAddress(addressCreateDto, addressCreateDto.getStudentId());
        addressRepository.save(address);

        return AddressMapper.INSTANCE.toDto(address);
    }

    @Transactional
    public void delete(Long id) {
        Address address = verifyAddressExists(id);
        address.setDeleted(true);
        addressRepository.save(address);
    }

    private Address verifyAddressExists(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (isNull(address)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço não encontrado.");
        }
        return address;
    }

    public Page<AddressDto> findByStudentId(Long studentId, Pageable pageable) {
        Page<Address> addresses = addressRepository.findByStudentIdAndDeletedFalse(studentId, pageable);
        Page<AddressDto> dtos = addresses.map(AddressMapper.INSTANCE::toDto);
        return dtos;
    }

    static String removeMask(String cnpj) {
        if (cnpj != null && cnpj.length() > 0) {
            return cnpj.replace(".", "").replace("/", "").replace("-", "");
        } else {
            return cnpj;
        }
    }

    @Transactional
    public AddressDto update(AddressUpdateDto addressUpdateDto) {
        Address address = verifyAddressExists(addressUpdateDto.getId());
        verifyCepIsValid(addressUpdateDto.getPostalCode());
        address.setCity(addressUpdateDto.getCity());
        address.setDistrict(addressUpdateDto.getDistrict());
        address.setPostalCode(removeMask(addressUpdateDto.getPostalCode()));
        address.setPublicPlace(addressUpdateDto.getPublicPlace());
        addressRepository.save(address);

        return AddressMapper.INSTANCE.toDto(address);
    }

    private void verifyCepIsValid(String postalCode) {
        postalCode = removeMask(postalCode);
        if (postalCode.length() != 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido");
        }
    }
}
