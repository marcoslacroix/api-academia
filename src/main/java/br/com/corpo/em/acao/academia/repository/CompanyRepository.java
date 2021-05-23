package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);
}
