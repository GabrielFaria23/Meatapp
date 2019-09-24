package br.com.meatapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.meatapp.services.DbService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DbService dbService;
	
	@Bean //eu consigo executar esse objeto como se fosse um javabean
	public void instanciaTeste() {
		dbService.instanciaDatabase();
	}
}
