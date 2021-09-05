package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.frequency.FrequencyDto;
import br.com.corpo.em.acao.academia.dto.frequency.create.FrequencyCreateDto;
import br.com.corpo.em.acao.academia.dto.frequency.update.FrequencyUpdateDto;
import br.com.corpo.em.acao.academia.service.FrequencyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/frequency")
public class FrequencyController {

    private final FrequencyService frequencyService;

    @PostMapping
    @ApiOperation(value = "Create frequency to a student")
    public ResponseEntity<?> create(@RequestBody @Valid FrequencyCreateDto frequencyCreateDto) {
        return ResponseEntity.ok(frequencyService.create(frequencyCreateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete frequency", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        frequencyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ApiOperation(value = "Update frequency")
    public ResponseEntity<FrequencyDto> update(@RequestBody @Valid FrequencyUpdateDto frequencyUpdateDto) {
        return ResponseEntity.ok(frequencyService.update(frequencyUpdateDto));
    }

}
