package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.studentFrequency.StudentFrequencyDto;
import br.com.corpo.em.acao.academia.dto.studentFrequency.create.StudentFrequencyCreateDto;
import br.com.corpo.em.acao.academia.dto.studentFrequency.update.StudentFrequencyUpdateDto;
import br.com.corpo.em.acao.academia.service.StudentFrequencyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/frequency")
public class StudentFrequencyController {

    private final StudentFrequencyService studentFrequencyService;

    @PostMapping
    @ApiOperation(value = "Create frequency to a student")
    public ResponseEntity<?> create(@RequestBody @Valid StudentFrequencyCreateDto studentFrequencyCreateDto) {
        return ResponseEntity.ok(studentFrequencyService.create(studentFrequencyCreateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete frequency", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentFrequencyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ApiOperation(value = "Update frequency")
    public ResponseEntity<StudentFrequencyDto> update(@RequestBody @Valid StudentFrequencyUpdateDto studentFrequencyUpdateDto) {
        return ResponseEntity.ok(studentFrequencyService.update(studentFrequencyUpdateDto));
    }

}
