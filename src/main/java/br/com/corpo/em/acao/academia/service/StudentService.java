package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.student.StudentDto;
import br.com.corpo.em.acao.academia.dto.student.create.AddressCreateDto;
import br.com.corpo.em.acao.academia.dto.student.create.StudentCreateDto;
import br.com.corpo.em.acao.academia.dto.student.update.StudentUpdateDto;
import br.com.corpo.em.acao.academia.mapper.address.AddressCreateMapper;
import br.com.corpo.em.acao.academia.mapper.student.StudentCreateMapper;
import br.com.corpo.em.acao.academia.mapper.student.StudentMapper;
import br.com.corpo.em.acao.academia.model.Address;
import br.com.corpo.em.acao.academia.model.Student;
import br.com.corpo.em.acao.academia.model.User;
import br.com.corpo.em.acao.academia.repository.AddressRepository;
import br.com.corpo.em.acao.academia.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public StudentDto create(StudentCreateDto studentCreateDto) {
        verifyCpfAlreadyRegistered(studentCreateDto.getCpf());
        Student student = StudentCreateMapper.INSTANCE.toStudent(studentCreateDto);
        studentRepository.save(student);
        return StudentMapper.INSTANCE.toStudentDto(student);
    }

    public void verifyCpfAlreadyRegistered(String cpf) {
        Student student = studentRepository.findByDeletedFalseAndCpf(removeMask(cpf));
        if (nonNull(student)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cpf já cadastrado.");
        }
    }

    private String removeMask(String cnpj) {
        if (cnpj != null && cnpj.length() > 0) {
            return cnpj.replace(".", "").replace("/", "").replace("-", "");
        } else {
            return cnpj;
        }
    }

    @Transactional
    public StudentDto update(StudentUpdateDto studentUpdateDto) {
        Student student = verifyStudentExists(studentUpdateDto.getId());
        student.setName(studentUpdateDto.getName());
        student.setCpf(removeMask(studentUpdateDto.getCpf()));
        student.setEmail(studentUpdateDto.getEmail());
        student.setBirth(studentUpdateDto.getBirth());
        student.setGender(studentUpdateDto.getGender());
        student.setOccupation(studentUpdateDto.getOccupation());
        student.setObjective(studentUpdateDto.getObjective());
        student.setUpdatedOn(LocalDateTime.now());

        studentRepository.save(student);

        return StudentMapper.INSTANCE.toStudentDto(student);
    }

    @Transactional
    public void delete(Long id) {
        Student student = verifyStudentExists(id);
        student.setUpdatedOn(LocalDateTime.now());
        student.setDeleted(true);
        studentRepository.save(student);
    }

    public Page<StudentDto> findAll(Pageable pageable) {
        Page<Student> student = studentRepository.findAll(pageable);
        Page<StudentDto> dtos = student.map(StudentMapper.INSTANCE::toStudentDto);
        return dtos;
    }

    public Student verifyStudentExists(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (isNull(student)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não encontrado.");
        }
        return student;
    }
}
