package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.enrollment.EnrollmentDto;
import br.com.corpo.em.acao.academia.dto.enrollment.create.EnrollmentCreateDto;
import br.com.corpo.em.acao.academia.dto.enrollment.update.EnrollmentUpdateDto;
import br.com.corpo.em.acao.academia.dto.phone.PhoneDto;
import br.com.corpo.em.acao.academia.service.EnrollmentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @ApiOperation(value = "Create enrollment for a student")
    public ResponseEntity<?> create(@RequestBody @Valid EnrollmentCreateDto enrollmentCreateDto) {
        enrollmentService.create(enrollmentCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete enrollment", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ApiOperation(value = "Update enrollment")
    public ResponseEntity<EnrollmentDto> update(@RequestBody @Valid EnrollmentUpdateDto enrollmentUpdateDto) {
        return ResponseEntity.ok(enrollmentService.update(enrollmentUpdateDto));
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Find all enrollment by student id", response = PhoneDto.class)
    public ResponseEntity<Page<EnrollmentDto>> findPhoneByStudentId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.findByStudentId(id, pageable));
    }

}
