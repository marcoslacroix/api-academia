package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.studentEnrollment.StudentEnrollmentDto;
import br.com.corpo.em.acao.academia.dto.studentEnrollment.create.StudentEnrollmentCreateDto;
import br.com.corpo.em.acao.academia.dto.studentEnrollment.update.StudentEnrollmentUpdateDto;
import br.com.corpo.em.acao.academia.mapper.studentEnrollment.StudentEnrollmentCreateMapper;
import br.com.corpo.em.acao.academia.mapper.studentEnrollment.StudentEnrollmentMapper;
import br.com.corpo.em.acao.academia.model.StudentEnrollment;
import br.com.corpo.em.acao.academia.repository.StudentEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentService {

    private final StudentService studentService;
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    @Transactional
    public StudentEnrollmentDto create(StudentEnrollmentCreateDto studentEnrollmentCreateDto) {
        studentService.findById(studentEnrollmentCreateDto.getStudentId());
        StudentEnrollment enrollment = StudentEnrollmentCreateMapper.INSTANCE.toEnrollment(studentEnrollmentCreateDto);
        studentEnrollmentRepository.save(enrollment);
        return StudentEnrollmentMapper.INSTANCE.toDto(enrollment);
    }

    @Transactional
    public void delete(Long id) {
        StudentEnrollment enrollment = verifyEnrollmentExists(id);
        enrollment.setDeleted(true);
        studentEnrollmentRepository.save(enrollment);
    }

    private StudentEnrollment verifyEnrollmentExists(Long id) {
        StudentEnrollment enrollment = studentEnrollmentRepository.findById(id).orElse(null);
        if (isNull(enrollment)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pagamento não encontrado.");
        }
        return enrollment;
    }

    @Transactional
    public StudentEnrollmentDto update(StudentEnrollmentUpdateDto studentEnrollmentUpdateDto) {
        StudentEnrollment enrollment = verifyEnrollmentExists(studentEnrollmentUpdateDto.getId());
        enrollment.setStart(studentEnrollmentUpdateDto.getStart());
        enrollment.setEnd(studentEnrollmentUpdateDto.getEnd());
        enrollment.setPrice(studentEnrollmentUpdateDto.getPrice());
        enrollment.setDescription(studentEnrollmentUpdateDto.getDescription());
        return StudentEnrollmentMapper.INSTANCE.toDto(enrollment);
    }

    public Page<StudentEnrollmentDto> findByStudentId(Long id, Pageable pageable) {
        Page<StudentEnrollment> enrollments = studentEnrollmentRepository.findByStudentIdAndDeletedFalse(id, pageable);
        Page<StudentEnrollmentDto> dtos = enrollments.map(StudentEnrollmentMapper.INSTANCE::toDto);
        return dtos;
    }

    @Transactional
    public void lock(StudentEnrollmentUpdateDto studentEnrollmentUpdateDto) {
        StudentEnrollment enrollment = verifyEnrollmentExists(studentEnrollmentUpdateDto.getId());
        enrollment.setEnrollmentLocked(true);
        enrollment.setDaysLocked(ChronoUnit.DAYS.between(LocalDate.now(), enrollment.getEnd()));
        enrollment.setDescription(studentEnrollmentUpdateDto.getDescription());
        studentEnrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unlock(StudentEnrollmentUpdateDto studentEnrollmentUpdateDto) {
        StudentEnrollment enrollment = verifyEnrollmentExists(studentEnrollmentUpdateDto.getId());
        enrollment.setEnrollmentLocked(false);
        enrollment.setStart(LocalDate.now());
        if (nonNull(enrollment.getDaysLocked())) {
            enrollment.setEnd(LocalDate.now().plusDays(enrollment.getDaysLocked()));
        }
        enrollment.setDaysLocked(0L);
        studentEnrollmentRepository.save(enrollment);
    }
}
