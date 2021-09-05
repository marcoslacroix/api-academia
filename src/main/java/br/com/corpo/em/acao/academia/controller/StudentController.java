package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.PagedResult;
import br.com.corpo.em.acao.academia.dto.student.StudentDto;
import br.com.corpo.em.acao.academia.dto.student.create.StudentCreateDto;
import br.com.corpo.em.acao.academia.dto.student.filter.StudentFilter;
import br.com.corpo.em.acao.academia.dto.student.update.StudentUpdateDto;
import br.com.corpo.em.acao.academia.mapper.student.StudentMapper;
import br.com.corpo.em.acao.academia.service.StudentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.var;
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

    @GetMapping("/{id}")
    @ApiOperation(value = "Find student by id")
    public ResponseEntity<StudentDto> findStudent(@PathVariable Long id) {
        return ResponseEntity.ok(
                StudentMapper.INSTANCE.toStudentDto(studentService.findById(id))
        );
    }

    @GetMapping("/findStudents")
    @ApiOperation(value = "Find students", response = StudentDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", example = "1", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "size", example = "25", defaultValue = "25", required = true)
    })
    public ResponseEntity<PagedResult<StudentDto>> findAll(@RequestBody StudentFilter studentFilter,
                                                           Pageable pageable) {
        var students = studentService.findByFilter(studentFilter, pageable)
                .map(StudentMapper.INSTANCE::toStudentDto);
        var studentsPage = new PagedResult<>(students);
        return ResponseEntity.ok(studentsPage);
    }
}
