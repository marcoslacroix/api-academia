package br.com.corpo.em.acao.academia.controller;

import br.com.corpo.em.acao.academia.dto.address.AddressDto;
import br.com.corpo.em.acao.academia.dto.address.create.AddressCreateDto;
import br.com.corpo.em.acao.academia.dto.address.update.AddressUpdateDto;
import br.com.corpo.em.acao.academia.dto.phone.PhoneDto;
import br.com.corpo.em.acao.academia.service.AddressService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @ApiOperation(value = "Create address to a student")
    public ResponseEntity<?> create(@RequestBody @Valid AddressCreateDto addressCreateDto) {

        return ResponseEntity.ok(addressService.create(addressCreateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete address", response = void.class)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ApiOperation(value = "Update address")
    public ResponseEntity<AddressDto> update(@RequestBody @Valid AddressUpdateDto addressUpdateDto) {
        return ResponseEntity.ok(addressService.update(addressUpdateDto));
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Find all address by student id", response = PhoneDto.class)
    public ResponseEntity<Page<AddressDto>> findPhoneByStudentId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(addressService.findByStudentId(id, pageable));
    }
}
