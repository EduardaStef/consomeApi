package br.com.alura.consomeApi;

import br.com.alura.consomeApi.controller.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsomeApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsomeApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();
		menu.executarMenu();
	}
}
