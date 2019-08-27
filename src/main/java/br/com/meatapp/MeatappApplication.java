package br.com.meatapp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meatapp.domain.User;
import br.com.meatapp.repositories.UserRepository;

@SpringBootApplication
public class MeatappApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MeatappApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(1, "Gabriel", "gabrielnunesfariapta@hotmail.com", "123");
		User user2 = new User(2, "Guilherme", "guilhermelnunesfariapta@hotmail.com", "123");
		userRepository.saveAll(Arrays.asList(user1,user2));
	}

}
