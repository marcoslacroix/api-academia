package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.Tests;
import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.dto.user.create.UserCreateDto;
import br.com.corpo.em.acao.academia.dto.user.update.UserUpdateDto;
import br.com.corpo.em.acao.academia.mapper.user.UserCreateMapper;
import br.com.corpo.em.acao.academia.model.User;
import br.com.corpo.em.acao.academia.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.nonNull;
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
    private UserRepository userRepository;


    private UserDto createNewUser() {
        return null;
    }

    @Test
    public void souldDeleteUser() {
        // given
        var userDto = createNewUser();

        // when
        userService.delete(userDto.getId());
        User user = userRepository.findById(userDto.getId()).get();

        // then
        Assertions.assertTrue(user.isDeleted());
    }

    @Test
    public void souldNotFoundUser() {
        // given
        var userDto = createNewUser()
                .withId(20L);

        // when
        boolean notFound = false;
        try {
            userService.findById(userDto.getId());
        } catch (ResponseStatusException ex) {
            ex.getMessage();
            notFound = true;
        }
        // then
        Assertions.assertTrue(notFound);
    }

    @Test
    public void souldFindUser() {
        // given
        var userDto = createNewUser();

        // when
        User u = userService.findById(userDto.getId());

        // then
        Assertions.assertTrue(nonNull(u));
    }

    @Test
    public void shouldUpdateUser() {
        // given
        UserDto userDto = userService.update(userUpdateDtoBuilder(createNewUser()).build());

    }

    private UserUpdateDto.UserUpdateDtoBuilder userUpdateDtoBuilder(UserDto userDto) {
        return UserUpdateDto.builder()
                .id(userDto.getId())
                .name("teste")
                .email(userDto.getEmail());
    }

    @Test
    public void shouldHaveUserRegisteredWithLogin(){
        // given
        var userDto = createNewUser();

        // when
        boolean userFound = false;
        try {
            userService.verifyUserLoginExists(userDto.getLogin());
        } catch (ResponseStatusException ex) {
            ex.getMessage();
            userFound = true;
        }
        // then
        Assertions.assertTrue(userFound);
    }

    @Test
    public void shouldNotFoundCompany() {
        // when
        boolean companyNotFound = false;
        try {
        } catch (ResponseStatusException ex) {
            ex.getMessage();
            companyNotFound = true;
        }
        // then
        Assertions.assertTrue(companyNotFound);
    }

}
