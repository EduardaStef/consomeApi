package br.com.alura.consomeApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tipo {

    @JsonAlias("name")
    private String nome;

    public String getNome() {
        return nome;
    }
}
