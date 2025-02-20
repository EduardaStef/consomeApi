package br.com.alura.consomeApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movimento {

    @JsonAlias("name")
    private String nome;

    @JsonAlias("power")
    private Integer forca;

    @JsonAlias("accuracy")
    private Integer precisao;

    @JsonAlias("type")
    private Tipo tipo;

    public String getNome() {
        return nome;
    }

    public Integer getForca() {
        return forca;
    }

    public Integer getPrecisao() {
        return precisao;
    }

    public Tipo getTipo() {
        return tipo;
    }
}

