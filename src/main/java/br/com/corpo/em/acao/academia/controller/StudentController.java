package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.student.StudentDto;
import br.com.corpo.em.acao.academia.dto.student.create.StudentCreateDto;
import br.com.corpo.em.acao.academia.dto.student.update.StudentUpdateDto;
import br.com.corpo.em.acao.academia.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ApiOperation(value = "Create student", response = StudentDto.class)
    public ResponseEntity<StudentDto> create(@RequestBody @Valid StudentCreateDto studentCreateDto) {
        return ResponseEntity.ok(studentService.create(studentCreateDto));
    }

    @PutMapping
    @ApiOperation(value = "Update student", response = StudentDto.class)
    public ResponseEntity<StudentDto> update(@RequestBody @Valid StudentUpdateDto studentUpdateDto) {
        return ResponseEntity.ok(studentService.update(studentUpdateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete student", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ApiOperation(value = "Get all students", response = StudentDto.class)
    public ResponseEntity<Page<StudentDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(studentService.findAll(pageable));
    }
}
