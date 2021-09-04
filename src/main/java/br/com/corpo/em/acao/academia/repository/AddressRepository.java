package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Page<Address> findByStudentIdAndDeletedFalse(Long studentId, Pageable pageable);
}
