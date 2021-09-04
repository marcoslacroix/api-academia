package br.com.corpo.em.acao.academia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "objective")
    private String objective;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "deleted")
    @Builder.Default
    private boolean deleted = false;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Address> address;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Phone> phones;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Frequency> frequencies;

    @PodamExclude
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return deleted == student.deleted && Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(cpf, student.cpf) && Objects.equals(email, student.email) && Objects.equals(birth, student.birth) && Objects.equals(gender, student.gender) && Objects.equals(occupation, student.occupation) && Objects.equals(objective, student.objective) && Objects.equals(createdOn, student.createdOn) && Objects.equals(updatedOn, student.updatedOn) && Objects.equals(address, student.address) && Objects.equals(phones, student.phones) && Objects.equals(frequencies, student.frequencies) && Objects.equals(enrollments, student.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, email, birth, gender, occupation, objective, createdOn, updatedOn, deleted, address, phones, frequencies, enrollments);
    }
}
