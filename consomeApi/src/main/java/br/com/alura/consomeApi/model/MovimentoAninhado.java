package br.com.alura.consomeApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovimentoAninhado {

    @JsonAlias("move")
    private Movimento movimento;

    public Movimento getMovimento() {
        return movimento;
    }
}
