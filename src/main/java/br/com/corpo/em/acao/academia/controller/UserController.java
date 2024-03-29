package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.user.UserDto;
import br.com.corpo.em.acao.academia.dto.user.UserPasswordRequest;
import br.com.corpo.em.acao.academia.dto.user.create.UserCreateDto;
import br.com.corpo.em.acao.academia.dto.user.update.UserUpdateDto;
import br.com.corpo.em.acao.academia.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "Create a user", response = UserDto.class)
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserCreateDto userCreateDto) {
        return ResponseEntity.ok(userService.create(userCreateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ApiOperation(value = "Update a user")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.update(userUpdateDto));
    }

    @PutMapping("/updatePassword")
    @ApiOperation(value = "Update password user", response = void.class)
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UserPasswordRequest userPasswordRequest){
        userService.changePassword(userPasswordRequest.getNewPassword(),
                userPasswordRequest.getNewPassword2(),
                userPasswordRequest.getOldPassword(),
                userPasswordRequest.getId()
        );
        return ResponseEntity.noContent().build();
    }
}