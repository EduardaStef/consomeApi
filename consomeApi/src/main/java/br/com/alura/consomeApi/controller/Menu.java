package br.com.alura.consomeApi.controller;

import br.com.alura.consomeApi.model.Movimento;
import br.com.alura.consomeApi.model.MovimentoAninhado;
import br.com.alura.consomeApi.model.Pokemon;
import br.com.alura.consomeApi.service.ApiService;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    ApiService apiService = new ApiService();
    Scanner teclado = new Scanner(System.in);

    public void executarMenu() {
        Boolean continuar = true;
        while(continuar) {
            logarMenu("Escolha um pokémon (nome ou número)");
            Pokemon pokemon = buscaPokemon();

            logarMenu("Listar movimentos? (true ou false)");
            Boolean listarMovimentos = teclado.nextBoolean();
            if(listarMovimentos) {
                pokemon.getMovimentos().stream()
                        .sorted(Comparator.comparing(movimentoAninhado -> movimentoAninhado.getMovimento().getNome()))
                        .forEach(movimentoAninhado -> listarMovimento(movimentoAninhado.getMovimento()));
            }

            logarMenu("Deseja pesquisar outro pokémon? (true ou false)");
            continuar = teclado.nextBoolean();
        }
    }

    public void insereValor() {
           /* TO DO:
           * Método para tratar a inserção de valores
           * envolvendo o teclado de forma mais dinâmica */
    }

    public Pokemon buscaPokemon() {
        String pesquisaPokemon = teclado.next();
        Pokemon pokemon = apiService.converterJson(apiService.chamaApi("https://pokeapi.co/api/v2/pokemon/" + pesquisaPokemon + "/"), Pokemon.class);
        pokemon.getMovimentos().forEach(movimentoAninhado -> {
            movimentoAninhado.setMovimento(apiService.converterJson(
                    apiService.chamaApi("https://pokeapi.co/api/v2/move/" + movimentoAninhado.getMovimento().getNome() + "/"),
                    Movimento.class));
        });
        System.out.println("--------------------------------\n" + pokemon.getId() + " - " + pokemon.getNome() + "\n\nTipos:");
        pokemon.getTipos().forEach(tipoAninhado -> System.out.println(tipoAninhado.getTipo().getNome()));
        System.out.println("--------------------------------");
        return pokemon;
    }

    public void logarMenu(String acao) {
        System.out.println("\n-  Consome API  -\n" +
                "----------------------------------\n" +
                "|  " + acao + "\n" +
                "----------------------------------");
    }

    public void listarMovimento(Movimento movimento) {
        System.out.println("\n-------------------------------------\n" +
                movimento.getNome() + "\n" +
                "força: " + movimento.getForca() + "\n" +
                "precisão: " + movimento.getPrecisao() + "\n" +
                "tipo: " + movimento.getTipo().getNome() + "\n" +
                "-------------------------------------\n");
    }
}
