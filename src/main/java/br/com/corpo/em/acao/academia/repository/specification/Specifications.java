package br.com.corpo.em.acao.academia.repository.specification;

import lombok.NonNull;
import org.hibernate.criterion.MatchMode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import static org.apache.commons.lang3.StringUtils.trim;

public class Specifications {

    public static Predicate trueExpression(CriteriaBuilder cb) {
        return cb.equal(cb.literal(1), 1);
    }

    public static Predicate likeExpression(@NonNull String term,
                                           @NonNull Expression<String> expression,
                                           @NonNull CriteriaBuilder cb) {
        return cb.like(
                cb.lower(expression),
                MatchMode.ANYWHERE.toMatchString(trim(term)).toLowerCase()
        );
    }

    public static Predicate likeExpression(@NonNull String term,
                                           @NonNull From<?, ?> root,
                                           @NonNull String rootAttribute,
                                           @NonNull CriteriaBuilder cb) {
        return likeExpression(trim(term), root.get(rootAttribute), cb);
    }
}
