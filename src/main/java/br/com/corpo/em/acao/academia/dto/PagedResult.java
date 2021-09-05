package br.com.corpo.em.acao.academia.dto;


import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.domain.Page;

import java.util.List;

@With
@Value
@Getter
@Builder
@Jacksonized
@RequiredArgsConstructor
public class PagedResult<T> {
    List<T> content;
    int page;
    int size;
    long numberOfElements;
    long totalElements;
    int totalPages;

    public PagedResult(Page<T> page) {
        this.content = page.getContent();
        this.page = page.getPageable().getPageNumber();
        this.size = page.getPageable().getPageSize();
        this.numberOfElements = page.getNumberOfElements();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
