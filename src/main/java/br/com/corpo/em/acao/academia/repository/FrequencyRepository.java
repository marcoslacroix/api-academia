package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.Frequency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequencyRepository extends JpaRepository<Frequency, Long> {
    Page<Frequency> findByStudentIdAndDeletedFalse(Long id, Pageable pageable);
}
