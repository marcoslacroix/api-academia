package br.com.corpo.em.acao.academia.repository.specification;

import br.com.corpo.em.acao.academia.dto.student.filter.StudentFilter;
import br.com.corpo.em.acao.academia.model.Student;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Predicate;

import static br.com.corpo.em.acao.academia.repository.specification.Specifications.likeExpression;
import static br.com.corpo.em.acao.academia.repository.specification.Specifications.trueExpression;
import static org.apache.logging.log4j.util.Strings.isBlank;

public class StudentSpecification {

    @SuppressWarnings("ConstantConditions")
    public static Specification<Student> byFilter(@NonNull StudentFilter filter) {
        return generalSpecification()
                .and(nameLike(filter.getName()));
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
