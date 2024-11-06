package com.app.cadastroClientesAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.app.cadastroClientesAPI.Domain.Repository")
public class CadastroClientesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroClientesApiApplication.class, args);
	}

}
