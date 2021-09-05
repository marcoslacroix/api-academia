package br.com.corpo.em.acao.academia.dto.student.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentFilterOrderProperty {
    NAME("name");

    private final String propertyName;
}
