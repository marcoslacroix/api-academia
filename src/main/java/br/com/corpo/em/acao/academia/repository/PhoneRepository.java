package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
