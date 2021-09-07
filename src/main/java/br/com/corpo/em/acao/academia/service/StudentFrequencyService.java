package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.studentFrequency.StudentFrequencyDto;
import br.com.corpo.em.acao.academia.dto.studentFrequency.create.StudentFrequencyCreateDto;
import br.com.corpo.em.acao.academia.dto.studentFrequency.update.StudentFrequencyUpdateDto;
import br.com.corpo.em.acao.academia.mapper.studentFrequency.StudentFrequencyCreateMapper;
import br.com.corpo.em.acao.academia.mapper.studentFrequency.StudentFrequencyMapper;
import br.com.corpo.em.acao.academia.model.StudentFrequency;
import br.com.corpo.em.acao.academia.repository.StudentFrequencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class StudentFrequencyService {

    private final StudentService studentService;
    private final StudentFrequencyRepository studentFrequencyRepository;

    @Transactional
    public StudentFrequencyDto create(StudentFrequencyCreateDto studentFrequencyCreateDto) {
        studentService.findById(studentFrequencyCreateDto.getStudentId());
        StudentFrequency frequency = StudentFrequencyCreateMapper.INSTANCE.toFrequency(studentFrequencyCreateDto);
        studentFrequencyRepository.save(frequency);
        return StudentFrequencyMapper.INSTANCE.toDto(frequency);
    }

    @Transactional
    public void delete(Long id) {
        StudentFrequency frequency = verifyFrequencyExists(id);
        frequency.setDeleted(true);
        studentFrequencyRepository.save(frequency);
    }

    private StudentFrequency verifyFrequencyExists(Long id) {
        StudentFrequency frequency = studentFrequencyRepository.findById(id).orElse(null);
        if (isNull(frequency)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Frequencia não encontrada.");
        }
        return frequency;
    }

    @Transactional
    public StudentFrequencyDto update(StudentFrequencyUpdateDto studentFrequencyUpdateDto) {
        StudentFrequency frequency = verifyFrequencyExists(studentFrequencyUpdateDto.getId());
        frequency.setStart(studentFrequencyUpdateDto.getStart());
        frequency.setEnd(studentFrequencyUpdateDto.getEnd());
        frequency.setNote(studentFrequencyUpdateDto.getNote());
        return StudentFrequencyMapper.INSTANCE.toDto(frequency);
    }
}
