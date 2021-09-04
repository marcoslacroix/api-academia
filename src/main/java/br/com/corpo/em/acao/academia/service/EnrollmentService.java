package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.enrollment.EnrollmentDto;
import br.com.corpo.em.acao.academia.dto.enrollment.create.EnrollmentCreateDto;
import br.com.corpo.em.acao.academia.dto.enrollment.update.EnrollmentUpdateDto;
import br.com.corpo.em.acao.academia.mapper.enrollment.EnrollmentCreateMapper;
import br.com.corpo.em.acao.academia.mapper.enrollment.EnrollmentMapper;
import br.com.corpo.em.acao.academia.model.Enrollment;
import br.com.corpo.em.acao.academia.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final StudentService studentService;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public EnrollmentDto create(EnrollmentCreateDto enrollmentCreateDto) {
        studentService.verifyStudentExists(enrollmentCreateDto.getStudentId());
        Enrollment enrollment = EnrollmentCreateMapper.INSTANCE.toEnrollment(enrollmentCreateDto);
        enrollmentRepository.save(enrollment);
        return EnrollmentMapper.INSTANCE.toDto(enrollment);
    }

    @Transactional
    public void delete(Long id) {
        Enrollment enrollment = verifyEnrollmentExists(id);
        enrollment.setDeleted(true);
        enrollmentRepository.save(enrollment);
    }

    private Enrollment verifyEnrollmentExists(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);
        if (isNull(enrollment)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pagamento não encontrado.");
        }
        return enrollment;
    }

    @Transactional
    public EnrollmentDto update(EnrollmentUpdateDto enrollmentUpdateDto) {
        Enrollment enrollment = verifyEnrollmentExists(enrollmentUpdateDto.getId());
        enrollment.setStart(enrollmentUpdateDto.getStart());
        enrollment.setEnd(enrollmentUpdateDto.getEnd());
        enrollment.setPrice(enrollmentUpdateDto.getPrice());
        enrollment.setDescription(enrollmentUpdateDto.getDescription());

        return EnrollmentMapper.INSTANCE.toDto(enrollment);
    }

    public Page<EnrollmentDto> findByStudentId(Long id, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByStudentIdAndDeletedFalse(id, pageable);
        Page<EnrollmentDto> dtos = enrollments.map(EnrollmentMapper.INSTANCE::toDto);
        return dtos;
    }

    @Transactional
    public void lock(EnrollmentUpdateDto enrollmentUpdateDto) {
        Enrollment enrollment = verifyEnrollmentExists(enrollmentUpdateDto.getId());
        enrollment.setEnrollmentLocked(true);
        enrollment.setDaysLocked(ChronoUnit.DAYS.between(LocalDate.now(), enrollment.getEnd()));
        enrollment.setDescription(enrollmentUpdateDto.getDescription());
        enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unlock(EnrollmentUpdateDto enrollmentUpdateDto) {
        Enrollment enrollment = verifyEnrollmentExists(enrollmentUpdateDto.getId());
        enrollment.setEnrollmentLocked(false);
        if (nonNull(enrollment.getDaysLocked())) {
            enrollment.setEnd(LocalDate.now().plusDays(enrollment.getDaysLocked()));
        }
        enrollment.setDaysLocked(0L);
        enrollmentRepository.save(enrollment);
    }
}
