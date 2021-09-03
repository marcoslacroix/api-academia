package br.com.corpo.em.acao.academia;

import br.com.corpo.em.acao.academia.model.User;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public abstract class Tests {

    public static PodamFactory podamFactory;

    static {
        podamFactory = new PodamFactoryImpl();
    }

}
