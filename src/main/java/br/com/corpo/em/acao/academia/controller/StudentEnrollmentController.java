package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.studentEnrollment.StudentEnrollmentDto;
import br.com.corpo.em.acao.academia.dto.studentEnrollment.create.StudentEnrollmentCreateDto;
import br.com.corpo.em.acao.academia.dto.studentEnrollment.update.StudentEnrollmentUpdateDto;
import br.com.corpo.em.acao.academia.dto.phone.PhoneDto;
import br.com.corpo.em.acao.academia.service.StudentEnrollmentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/enrollment")
public class StudentEnrollmentController {

    private final StudentEnrollmentService studentEnrollmentService;

    @PostMapping
    @ApiOperation(value = "Create enrollment for a student")
    public ResponseEntity<?> create(@RequestBody @Valid StudentEnrollmentCreateDto studentEnrollmentCreateDto) {
        return ResponseEntity.ok(studentEnrollmentService.create(studentEnrollmentCreateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete enrollment", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentEnrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ApiOperation(value = "Update enrollment")
    public ResponseEntity<StudentEnrollmentDto> update(@RequestBody @Valid StudentEnrollmentUpdateDto studentEnrollmentUpdateDto) {
        return ResponseEntity.ok(studentEnrollmentService.update(studentEnrollmentUpdateDto));
    }

    @PostMapping(value = "/lock")
    @ApiOperation(value = "Lock enrollment", response = void.class)
    public ResponseEntity<Void> lock(@RequestBody @Valid StudentEnrollmentUpdateDto studentEnrollmentUpdateDto) {
        studentEnrollmentService.lock(studentEnrollmentUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/unlock")
    @ApiOperation(value = "Unlock enrollment", response = void.class)
    public ResponseEntity<Void> unlock(@RequestBody @Valid StudentEnrollmentUpdateDto studentEnrollmentUpdateDto) {
        studentEnrollmentService.unlock(studentEnrollmentUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Find all enrollment by student id", response = PhoneDto.class)
    public ResponseEntity<Page<StudentEnrollmentDto>> findPhoneByStudentId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(studentEnrollmentService.findByStudentId(id, pageable));
    }

}
