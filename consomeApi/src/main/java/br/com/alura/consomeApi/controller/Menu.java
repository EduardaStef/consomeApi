package br.com.alura.consomeApi.controller;

import br.com.alura.consomeApi.model.Movimento;
import br.com.alura.consomeApi.model.Pokemon;
import br.com.alura.consomeApi.service.ApiService;

import java.util.Scanner;

public class Menu {

    ApiService apiService = new ApiService();
    Scanner teclado = new Scanner(System.in);

    public void executarMenu() {
        System.out.println("Testando aplicação \n \n");

        System.out.println("Insira o id ou o nome do pokemon:");
        String pesquisaPokemon = teclado.next();

        Pokemon pokemon = apiService.converterJson(apiService.chamaApi("https://pokeapi.co/api/v2/pokemon/" + pesquisaPokemon + "/"), Pokemon.class);
        System.out.println(pokemon.getNome());
        for (int i = 0; i < pokemon.getTipos().size(); i++) {
            System.out.println( (i+1) + "° tipo: " + pokemon.getTipos().get(i).getTipo().getNome());
        }
        listarMovimentos(pokemon);

        System.out.println("teste");
    }

    public void listarMovimentos(Pokemon pokemon) {
        for (int i = 0; i < pokemon.getMovimentos().size(); i++) {
            System.out.println("\n-------------------------------------\n");
            String nome = pokemon.getMovimentos().get(i).getMovimento().getNome();
            Movimento movimento = apiService.converterJson(apiService.chamaApi("https://pokeapi.co/api/v2/move/" + nome + "/"), Movimento.class);
            System.out.println(movimento.getNome());
            System.out.println("força: " + movimento.getForca());
            System.out.println("precisão: " + movimento.getPrecisao());
            System.out.println("tipo: " + movimento.getTipo().getNome());

        }
    }
}
