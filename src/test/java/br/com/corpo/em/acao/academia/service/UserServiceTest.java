package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.Tests;
import br.com.corpo.em.acao.academia.dto.company.CompanyDto;
import br.com.corpo.em.acao.academia.dto.company.create.CompanyCreateDto;
import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.dto.user.create.UserCreateDto;
import br.com.corpo.em.acao.academia.mapper.company.CompanyCreateMapper;
import br.com.corpo.em.acao.academia.mapper.user.UserCreateMapper;
import br.com.corpo.em.acao.academia.model.Company;
import br.com.corpo.em.acao.academia.model.User;
import br.com.corpo.em.acao.academia.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@SuppressWarnings("unused")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyRepository companyRepository;

    private UserDto createNewUser() {
        Company company = Tests.createCompany();
        User user = Tests.createUser(company.getId());
        Company com = companyRepository.save(company);
        UserCreateDto userCreateDto = UserCreateMapper.INSTANCE.toUserCreateDto(user).withCompanyId(com.getId());
        return userService.create(userCreateDto);

    }

    @Test
    public void souldDeleteUser() {
        // when
        UserDto userDto = createNewUser();
        userDto.getId();

//        userService.delete(Tests.createUser().getId());
    }
}
