package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.address.AddressDto;
import br.com.corpo.em.acao.academia.dto.student.create.AddressCreateDto;
import br.com.corpo.em.acao.academia.service.AddressService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @ApiOperation(value = "Create address to a student")
    public ResponseEntity<?> createAddress(@RequestBody @Valid AddressCreateDto addressCreateDto) {
        addressService.createAddress(addressCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete student", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Find all address by student id", response = AddressDto.class)
    public ResponseEntity<List<AddressDto>> findAddresByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findByStudentId(id));
    }
}
