package br.com.alura.consomeApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoAninhado {

    @JsonAlias("type")
    private Tipo tipo;

    public Tipo getTipo() {
        return tipo;
    }
}
