package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByDeletedFalseAndCpf(String cpf);

    Student findByEmailAndDeletedFalse(String email);
}
