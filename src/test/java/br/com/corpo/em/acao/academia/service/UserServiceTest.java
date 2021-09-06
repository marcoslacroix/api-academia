package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.dto.user.update.UserUpdateDto;
import br.com.corpo.em.acao.academia.mapper.user.UserMapper;
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


import java.time.LocalDateTime;

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
        return UserDto.builder()
                .id(10L)
                .name("Tester Test")
                .login("tester")
                .email("test@gmail.com")
                .createdOn(LocalDateTime.now())
                .deleted(false)
                .build();
    }

    @Test
    public void souldDeleteUser() {
        // given
        User user = getUser();

        // when
        userService.delete(user.getId());
        user = userRepository.findById(user.getId()).get();

        // then
        Assertions.assertTrue(user.isDeleted());
    }

    @Test
    public void souldNotFoundUser() {
        // given
        User userDto = getUser();
        userDto.setId(20L);

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
        var user = getUser();

        // when
        User u = userService.findById(user.getId());

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
    public void shouldHaveUserRegisteredWithLogin() {
        // given
        User user = getUser();
        // when
        boolean userFound = false;
        try {
            userService.verifyUserLoginExists(user.getUsername());
        } catch (ResponseStatusException ex) {
            ex.getMessage();
            userFound = true;
        }
        // then
        Assertions.assertTrue(userFound);
    }

    private User getUser() {
        var user = User.builder()
                .name("Tester Test")
                .username("tester")
                .email("test@gmail.com")
                .createdOn(LocalDateTime.now())
                .deleted(false)
                .build();
        userRepository.save(user);
        return user;
    }


}
