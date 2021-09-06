package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.StudentFrequency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFrequencyRepository extends JpaRepository<StudentFrequency, Long> {
    Page<StudentFrequency> findByStudentIdAndDeletedFalse(Long id, Pageable pageable);
}
