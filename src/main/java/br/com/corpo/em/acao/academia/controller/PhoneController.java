package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.phone.PhoneDto;
import br.com.corpo.em.acao.academia.dto.phone.create.PhoneCreateDto;
import br.com.corpo.em.acao.academia.dto.phone.update.PhoneUpdateDto;
import br.com.corpo.em.acao.academia.service.PhoneService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @PostMapping
    @ApiOperation(value = "Create address to a student")
    public ResponseEntity<?> create(@RequestBody @Valid PhoneCreateDto phoneCreateDto) {
        return ResponseEntity.ok(phoneService.create(phoneCreateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete phone", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        phoneService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ApiOperation(value = "Update phone")
    public ResponseEntity<PhoneDto> update(@RequestBody @Valid PhoneUpdateDto phoneUpdateDto) {
        return ResponseEntity.ok(phoneService.update(phoneUpdateDto));
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Find all address by student id", response = PhoneDto.class)
    public ResponseEntity<Page<PhoneDto>> findPhoneByStudentId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(phoneService.findByStudentId(id, pageable));
    }
}
