package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Page<Enrollment> findByStudentIdAndDeletedFalse(Long id, Pageable pageable);
}
