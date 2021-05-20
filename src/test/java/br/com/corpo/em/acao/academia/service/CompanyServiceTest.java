package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.Tests;
import br.com.corpo.em.acao.academia.dto.company.CompanyDto;
import br.com.corpo.em.acao.academia.dto.company.create.CompanyCreateDto;
import br.com.corpo.em.acao.academia.mapper.company.CompanyCreateMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@Slf4j
@SuppressWarnings("unused")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CompanyServiceTest {

    @Mock
    private CompanyService companyService;

    public CompanyDto createNewCompany() {
        CompanyCreateDto companyCreateDto = CompanyCreateMapper.INSTANCE.toCompanyCreateDto(Tests.createCompany());
        return companyService.create(companyCreateDto);
    }

}
