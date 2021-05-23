package br.com.corpo.em.acao.academia.repository;

import br.com.corpo.em.acao.academia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String login);

    List<User> findByCompanyId(Long id);
}
