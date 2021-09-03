package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByStudentIdAndDeletedFalse(Long studentId);
}
