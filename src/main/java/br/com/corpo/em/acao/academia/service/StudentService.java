package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.student.StudentDto;
import br.com.corpo.em.acao.academia.dto.student.create.StudentCreateDto;
import br.com.corpo.em.acao.academia.dto.student.filter.StudentFilter;
import br.com.corpo.em.acao.academia.dto.student.filter.StudentFilterOrderProperty;
import br.com.corpo.em.acao.academia.dto.student.update.StudentUpdateDto;
import br.com.corpo.em.acao.academia.mapper.address.AddressCreateMapper;
import br.com.corpo.em.acao.academia.mapper.phone.PhoneCreateMapper;
import br.com.corpo.em.acao.academia.mapper.student.StudentCreateMapper;
import br.com.corpo.em.acao.academia.mapper.student.StudentMapper;
import br.com.corpo.em.acao.academia.model.Address;
import br.com.corpo.em.acao.academia.model.Phone;
import br.com.corpo.em.acao.academia.model.Student;
import br.com.corpo.em.acao.academia.repository.AddressRepository;
import br.com.corpo.em.acao.academia.repository.PhoneRepository;
import br.com.corpo.em.acao.academia.repository.StudentRepository;
import br.com.corpo.em.acao.academia.repository.specification.StudentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;

    @Transactional
    public StudentDto create(StudentCreateDto studentCreateDto) {
        verifyCpfAlreadyRegistered(studentCreateDto.getCpf());
        verifyCpfIsValid(studentCreateDto.getCpf());
        verifyEmailIsAlreadyResgistered(studentCreateDto.getEmail(), null);
        Student student = StudentCreateMapper.INSTANCE.toStudent(studentCreateDto);
        studentRepository.save(student);

        createStudentAddresss(student, studentCreateDto);
        createStudentPhone(student, studentCreateDto);

        return StudentMapper.INSTANCE.toStudentDto(student);
    }

    private void createStudentPhone(Student student, StudentCreateDto studentCreateDto) {
        List<Phone> phones = new ArrayList<>();
        if (!studentCreateDto.getPhones().isEmpty()) {
            studentCreateDto.getPhones().forEach(
                    it -> phones.add(PhoneCreateMapper.INSTANCE.toPhone(it, student.getId())));
            phoneRepository.saveAll(phones);
        }
    }

    private void verifyEmailIsAlreadyResgistered(String email, Student studentUpdate) {
        Student student = studentRepository.findByEmailAndDeletedFalse(email);
        if (nonNull(student) && !student.equals(studentUpdate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }
    }

    private void createStudentAddresss(Student student, StudentCreateDto studentCreateDto) {
        List<Address> addresses = new ArrayList<>();
        if (!studentCreateDto.getAddress().isEmpty()) {
            studentCreateDto.getAddress().forEach(
                    it -> addresses.add(AddressCreateMapper.INSTANCE.toAddress(it, student.getId())));
            student.setAddress(addresses);
            addressRepository.saveAll(addresses);
        }
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
        verifyCpfIsValid(studentUpdateDto.getCpf());
        Student student = findById(studentUpdateDto.getId());
        verifyEmailIsAlreadyResgistered(studentUpdateDto.getEmail(), student);
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

    private void verifyCpfIsValid(String cpf) {
        cpf = removeMask(cpf);
        if (cpf.length() != 11) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
    }

    @Transactional
    public void delete(Long id) {
        Student student = findById(id);
        student.setUpdatedOn(LocalDateTime.now());
        student.setDeleted(true);
        studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public Page<Student> findByFilter(StudentFilter studentFilter, Pageable pageable) {
        return studentRepository.findAll(
                StudentSpecification.byFilter(studentFilter),
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        ofNullable(studentFilter.getOrderDirection()).orElse(DESC),
                        ofNullable(studentFilter.getOrderBy()).orElse(StudentFilterOrderProperty.NAME).getPropertyName()
                )
        );
    }

    public Student findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (isNull(student)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não encontrado.");
        }
        return student;
    }
}
