package br.com.alura.consomeApi.controller;

import br.com.alura.consomeApi.model.Movimento;
import br.com.alura.consomeApi.model.MovimentoAninhado;
import br.com.alura.consomeApi.model.Pokemon;
import br.com.alura.consomeApi.service.ApiService;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    ApiService apiService = new ApiService();
    Scanner teclado = new Scanner(System.in);

    public void executarMenu() {
        Boolean continuar = true;
        while(continuar) {
            logarMenu("Escolha um pokémon (nome ou número)");
            Pokemon pokemon = buscaPokemon();

            estatisticasMovimentos(pokemon);
            logarMenu("Listar TODOS os movimentos? (true ou false)");
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
           * envolvendo o teclado de forma mais dinâmica
           * e protegida */
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

    public void estatisticasMovimentos(Pokemon pokemon) {
        IntSummaryStatistics estatisticas = pokemon.getMovimentos().stream()
                .filter(movimentoAninhado -> movimentoAninhado.getMovimento().getForca() != null)
                .collect(Collectors.summarizingInt(mov -> mov.getMovimento().getForca()));

        System.out.println("Movimentos mais fortes: ");
        logaAtaquePorForca(pokemon, estatisticas.getMax());
        System.out.println("--------------------------------");
        System.out.println("Movimentos mais fracos: ");
        logaAtaquePorForca(pokemon, estatisticas.getMin());
        System.out.println("--------------------------------");
        System.out.println("Quantidade de movimentos que inflingem dano: " + estatisticas.getCount());
        System.out.println("--------------------------------");
    }

    public void logaAtaquePorForca(Pokemon pokemon, Integer filtro) {
        pokemon.getMovimentos().stream()
                .filter(movimentoAninhado -> movimentoAninhado.getMovimento().getForca() != null && movimentoAninhado.getMovimento().getForca().equals(filtro))
                .forEach(movimentoAninhado ->
                        System.out.println("\nNome: " + movimentoAninhado.getMovimento().getNome() + "\n"
                                         + "Força: " + movimentoAninhado.getMovimento().getForca()));
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
