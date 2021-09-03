package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.address.AddressDto;
import br.com.corpo.em.acao.academia.dto.student.create.AddressCreateDto;
import br.com.corpo.em.acao.academia.mapper.address.AddressCreateMapper;
import br.com.corpo.em.acao.academia.mapper.address.AddressMapper;
import br.com.corpo.em.acao.academia.model.Address;
import br.com.corpo.em.acao.academia.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final StudentService studentService;

    @Transactional
    public void createAddress(AddressCreateDto addressCreateDto) {
        studentService.verifyStudentExists(addressCreateDto.getStudentId());
        Address address = AddressCreateMapper.INSTANCE.toAddress(addressCreateDto);
        addressRepository.save(address);
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

    public List<AddressDto> findByStudentId(Long studentId) {
        List<Address> addresses = addressRepository.findByStudentIdAndDeletedFalse(studentId);
        List<AddressDto> addressDtos = addresses.stream()
                .map(AddressMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return addressDtos;
    }
}
