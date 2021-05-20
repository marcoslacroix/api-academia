package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.config.PasswordEncoder;
import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.dto.user.create.UserCreateDto;
import br.com.corpo.em.acao.academia.dto.user.update.UserUpdateDto;
import br.com.corpo.em.acao.academia.mapper.user.UserCreateMapper;
import br.com.corpo.em.acao.academia.mapper.user.UserMapper;
import br.com.corpo.em.acao.academia.model.User;
import br.com.corpo.em.acao.academia.repository.CompanyRepository;
import br.com.corpo.em.acao.academia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public UserDto create(UserCreateDto userCreateDto) {
        if (isNull(companyRepository.findById(userCreateDto.getCompanyId()).orElse(null))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa não existe");
        }
        if (nonNull(userRepository.getUserByLogin(userCreateDto.getLogin()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Usuário já cadastrado");
        }
        User user = UserCreateMapper.INSTANCE.toUser(userCreateDto);
        user.setPassword(PasswordEncoder.passwordEncoder().encode(user.getPassword()));

        userRepository.save(user);

        return UserMapper.INSTANCE.toDto(user);

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
        if (newPassword.equals(newPassword2)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The new password do not match");
        } else if (PasswordEncoder.passwordEncoder().matches(oldPassword, user.getPassword())) {
            user.setPassword(PasswordEncoder.passwordEncoder().encode(newPassword));
            user.setUpdatedOn(LocalDateTime.now());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The current password is incorrect.");
        }
        userRepository.save(user);
    }

    private User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (isNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("UUID %s not found ", id));
        }
        return user;
    }

    public List<UserDto> findAllByCompanyId(Long id) {
        List<User> userList = userRepository.findByCompanyId(id);
        return userList.stream()
                .map(it -> UserMapper.INSTANCE.toDto(it))
                .collect(Collectors.toList());
    }
}
