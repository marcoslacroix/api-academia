package br.com.corpo.em.acao.academia.repository.specification;

import br.com.corpo.em.acao.academia.dto.student.filter.EnrollmentStatus;
import br.com.corpo.em.acao.academia.dto.student.filter.StudentFilter;
import br.com.corpo.em.acao.academia.model.Enrollment;
import br.com.corpo.em.acao.academia.model.Student;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.Join;

import java.time.LocalDate;

import static br.com.corpo.em.acao.academia.repository.specification.Specifications.likeExpression;
import static br.com.corpo.em.acao.academia.repository.specification.Specifications.trueExpression;
import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.logging.log4j.util.Strings.isBlank;

public class StudentSpecification {

    @SuppressWarnings("ConstantConditions")
    public static Specification<Student> byFilter(@NonNull StudentFilter filter) {
        return generalSpecification()
                .and(nameLike(filter.getName())
                .and(enrollmentStatus(filter.getStatus()))
                .and(enrollmentLocked(filter.getEnrollmentLocked())));
    }

    private static Specification<Student> enrollmentStatus(EnrollmentStatus status) {
        return (root, query, cb) -> {
            Join<Student, Enrollment> enrollment = root.join("enrollments");
            switch (nonNull(status) ? status : EnrollmentStatus.ALL) {
                case ACTIVE:
                    return cb.greaterThanOrEqualTo(enrollment.get("end"), LocalDate.now());
                case INACTIVE:
                    return cb.lessThanOrEqualTo(enrollment.get("end"), LocalDate.now());
                case ALL:
                    return trueExpression(cb);
                default:
                    throw new RuntimeException(format("Status %s does not exists", status));
            }
        };
    }

    private static Specification<Student> enrollmentLocked(Boolean enrollmentLocked) {
        return (root, query, cb) -> {
            if (isFalse(enrollmentLocked)) {
                return trueExpression(cb);
            }
            Join<Student, Enrollment> enrollment = root.join("enrollments");

            return cb.isTrue(enrollment.get("enrollmentLocked"));
        };
    }

    private static Specification<Student> nameLike(String term) {
        return (root, query, cb) -> {
            if (isBlank(term))
                return trueExpression(cb);

            return likeExpression(term, root, "name", cb);
        };
    }

    private static Specification<Student> generalSpecification() {
        return (root, query, cb) -> {
            return cb.and(
                    cb.isFalse(root.get("deleted"))
            );
        };
    }
}
