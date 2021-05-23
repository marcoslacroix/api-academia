package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.dto.user.create.UserCreateDto;
import br.com.corpo.em.acao.academia.dto.user.update.UserUpdateDto;
import br.com.corpo.em.acao.academia.mapper.user.UserCreateMapper;
import br.com.corpo.em.acao.academia.mapper.user.UserMapper;
import br.com.corpo.em.acao.academia.model.Company;
import br.com.corpo.em.acao.academia.model.User;
import br.com.corpo.em.acao.academia.repository.CompanyRepository;
import br.com.corpo.em.acao.academia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public UserDto create(UserCreateDto userCreateDto) {
        verifyCompanyExists(userCreateDto.getCompanyId());
        verifyUserLoginExists(userCreateDto.getUsername());

        User user = UserCreateMapper.INSTANCE.toUser(userCreateDto);
        user.setPassword(encoder.encode(userCreateDto.getPassword()));

        userRepository.save(user);

        return UserMapper.INSTANCE.toDto(user);

    }

    public void verifyUserLoginExists(String userLogin) {
        User user = userRepository.getUserByUsername(userLogin);
        if (nonNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Usuário já cadastrado");
        }
    }

    public void verifyCompanyExists(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (isNull(company)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa não existe");
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
        if (newPassword.equals(newPassword2)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The new password do not match");
        } else if (encoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(encoder.encode(newPassword));
            user.setUpdatedOn(LocalDateTime.now());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The current password is incorrect.");
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

    public List<UserDto> findAllByCompanyId(Long id) {
        List<User> userList = userRepository.findByCompanyId(id);
        return userList.stream()
                .map(it -> UserMapper.INSTANCE.toDto(it))
                .collect(Collectors.toList());
    }
}
