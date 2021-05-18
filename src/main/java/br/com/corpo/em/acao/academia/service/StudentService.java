package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.student.StudentDto;
import br.com.corpo.em.acao.academia.dto.student.create.StudentCreateDto;
import br.com.corpo.em.acao.academia.dto.student.update.StudentUpdateDto;
import br.com.corpo.em.acao.academia.mapper.student.StudentCreateMapper;
import br.com.corpo.em.acao.academia.mapper.student.StudentMapper;
import br.com.corpo.em.acao.academia.model.Student;
import br.com.corpo.em.acao.academia.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
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
        if (nonNull(studentRepository.findByDeletedFalseAndCpf(removeMask(studentCreateDto.getCpf())))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cpf já cadastrado.");
        }
        Student student = StudentCreateMapper.INSTANCE.toStudent(studentCreateDto);
        studentRepository.save(student);
        return StudentMapper.INSTANCE.toStudentDto(student);
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
        Student student = studentRepository.findById(studentUpdateDto.getId()).orElse(null);
        if (isNull(student)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }
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
        Student student = studentRepository.findById(id).orElse(null);
        if (isNull(student)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not found.");
        }
        student.setUpdatedOn(LocalDateTime.now());
        student.setDeleted(true);
        studentRepository.save(student);
    }

    public List<StudentDto> findAll() {
        List<Student> student = studentRepository.findAll();
        List<StudentDto> dtos = new ArrayList<>();
        for (Student st : student) {
            dtos.add(StudentMapper.INSTANCE.toStudentDto(st));
        }
        return dtos;
    }
}
