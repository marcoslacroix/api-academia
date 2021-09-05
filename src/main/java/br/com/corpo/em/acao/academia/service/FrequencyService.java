package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.frequency.FrequencyDto;
import br.com.corpo.em.acao.academia.dto.frequency.create.FrequencyCreateDto;
import br.com.corpo.em.acao.academia.dto.frequency.update.FrequencyUpdateDto;
import br.com.corpo.em.acao.academia.mapper.frequency.FrequencyCreateMapper;
import br.com.corpo.em.acao.academia.mapper.frequency.FrequencyMapper;
import br.com.corpo.em.acao.academia.model.Frequency;
import br.com.corpo.em.acao.academia.repository.FrequencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class FrequencyService {

    private final StudentService studentService;
    private final FrequencyRepository frequencyRepository;

    @Transactional
    public FrequencyDto create(FrequencyCreateDto frequencyCreateDto) {
        studentService.findById(frequencyCreateDto.getStudentId());
        Frequency frequency = FrequencyCreateMapper.INSTANCE.toFrequency(frequencyCreateDto);
        frequencyRepository.save(frequency);
        return FrequencyMapper.INSTANCE.toDto(frequency);
    }

    @Transactional
    public void delete(Long id) {
        Frequency frequency = verifyFrequencyExists(id);
        frequency.setDeleted(true);
        frequencyRepository.save(frequency);
    }

    private Frequency verifyFrequencyExists(Long id) {
        Frequency frequency = frequencyRepository.findById(id).orElse(null);
        if (isNull(frequency)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Frequencia não encontrada.");
        }
        return frequency;
    }

    @Transactional
    public FrequencyDto update(FrequencyUpdateDto frequencyUpdateDto) {
        Frequency frequency = verifyFrequencyExists(frequencyUpdateDto.getId());
        frequency.setStart(frequencyUpdateDto.getStart());
        frequency.setEnd(frequencyUpdateDto.getEnd());
        frequency.setNote(frequencyUpdateDto.getNote());
        return FrequencyMapper.INSTANCE.toDto(frequency);
    }
}
