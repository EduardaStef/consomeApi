package br.com.alura.consomeApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    @JsonAlias("id")
    private Integer id;

    @JsonAlias("name")
    private String nome;

    @JsonAlias("moves")
    private List<MovimentoAninhado> movimentos;

    @JsonAlias("types")
    private List<TipoAninhado> tipos;

    public String getNome() {
        return nome;
    }

    public List<MovimentoAninhado> getMovimentos() {
        return movimentos;
    }

    public List<TipoAninhado> getTipos() {
        return tipos;
    }
}
