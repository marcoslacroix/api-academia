package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.address.AddressDto;
import br.com.corpo.em.acao.academia.dto.phone.PhoneDto;
import br.com.corpo.em.acao.academia.dto.phone.create.PhoneCreateDto;
import br.com.corpo.em.acao.academia.dto.phone.update.PhoneUpdateDto;
import br.com.corpo.em.acao.academia.mapper.phone.PhoneCreateMapper;
import br.com.corpo.em.acao.academia.mapper.phone.PhoneMapper;
import br.com.corpo.em.acao.academia.mapper.phone.PhoneUpdateMapper;
import br.com.corpo.em.acao.academia.model.Phone;
import br.com.corpo.em.acao.academia.repository.PhoneRepository;
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
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final StudentService studentService;

    @Transactional
    public PhoneDto create(PhoneCreateDto phoneCreateDto) {
        studentService.verifyStudentExists(phoneCreateDto.getStudentId());
        Phone phone = PhoneCreateMapper.INSTANCE.toPhone(phoneCreateDto, phoneCreateDto.getStudentId());
        phoneRepository.save(phone);
        return PhoneMapper.INSTANCE.toDto(phone);
    }

    @Transactional
    public void delete(Long id) {
        Phone phone = verifyPhoneExists(id);
        phone.setDeleted(true);
        phoneRepository.save(phone);
    }

    private Phone verifyPhoneExists(Long id) {
        Phone phone = phoneRepository.findById(id).orElse(null);
        if (isNull(phone)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone não encontrado.");
        }
        return phone;
    }

    @Transactional
    public PhoneDto update(PhoneUpdateDto phoneUpdateDto) {
        Phone phone = verifyPhoneExists(phoneUpdateDto.getId());
        phone.setPhoneNumber(phoneUpdateDto.getPhoneNumber());
        phone.setDdd(phoneUpdateDto.getDdd());
        phoneRepository.save(phone);

        return PhoneMapper.INSTANCE.toDto(phone);
    }

    public Page<PhoneDto> findByStudentId(Long id, Pageable pageable) {
        return null;
    }
}
