package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.dto.user.create.UserCreateDto;
import br.com.corpo.em.acao.academia.dto.user.update.UserUpdateDto;
import br.com.corpo.em.acao.academia.mapper.user.UserCreateMapper;
import br.com.corpo.em.acao.academia.mapper.user.UserMapper;
import br.com.corpo.em.acao.academia.model.User;
import br.com.corpo.em.acao.academia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public UserDto create(UserCreateDto userCreateDto) {
        verifyUserLoginExists(userCreateDto.getUsername());
        verifyEmailExists(userCreateDto.getEmail());
        verifyLengthPassword(userCreateDto.getPassword());

        User user = UserCreateMapper.INSTANCE.toUser(userCreateDto);
        user.setPassword(encoder.encode(userCreateDto.getPassword()));
        userRepository.save(user);

        return UserMapper.INSTANCE.toDto(user);
    }

    private void verifyLengthPassword(String password) {
        if (password.length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha deve conter no mínimo 8 caracteres.");
        }
    }

    private void verifyEmailExists(String email) {
        User user = userRepository.getUserByEmail(email);
        if (nonNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }
    }

    public void verifyUserLoginExists(String userLogin) {
        User user = userRepository.getUserByUsername(userLogin);
        if (nonNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        user.setDeleted(true);
        user.setUpdatedOn(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public UserDto update(UserUpdateDto userUpdateDto) {
        User user = findById(userUpdateDto.getId());
        user.setName(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());
        userRepository.save(user);

        return UserMapper.INSTANCE.toDto(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String newPassword, String newPassword2, String oldPassword, Long id) {
        User user = findById(id);
        verifyLengthPassword(newPassword);
        if (newPassword.equals(newPassword2)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Senha mesma que a antiga.");
        } else if (encoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(encoder.encode(newPassword));
            user.setUpdatedOn(LocalDateTime.now());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Suas senhas não são iguais.");
        }
        userRepository.save(user);
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (isNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Usuário com o ID %s não encontrado ", id));
        }
        return user;
    }
}
