package br.com.brunohenrique.desafiocartas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesafioCartasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCartasApplication.class, args);
	}

}
