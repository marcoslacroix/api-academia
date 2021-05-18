package br.com.corpo.em.acao.academia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "deleted")
    @Builder.Default
    private boolean deleted = false;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updatedOn")
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Student> students;
}
