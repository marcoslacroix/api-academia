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

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "deleted")
    @Builder.Default
    private boolean deleted = false;

    @Column(name = "active_enrollment")
    @Builder.Default
    private boolean activeEnrollment = false;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Address> address;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Phone> phones;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Frequency> frequencies;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;
}
