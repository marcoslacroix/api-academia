package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.StudentEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
    Page<StudentEnrollment> findByStudentIdAndDeletedFalse(Long id, Pageable pageable);
}
