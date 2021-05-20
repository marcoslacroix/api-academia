package br.com.corpo.em.acao.academia;

import br.com.corpo.em.acao.academia.model.Company;
import br.com.corpo.em.acao.academia.model.User;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public abstract class Tests {

    public static PodamFactory podamFactory;

    static {
        podamFactory = new PodamFactoryImpl();
    }

    public static Company createCompany() {
        var company = podamFactory.manufacturePojo(Company.class);
        return company;
    }

    public static User createUser(Long companyId) {
        var user = podamFactory.manufacturePojo(User.class);
        user.setCompanyId(companyId);
        return user;
    }

}
