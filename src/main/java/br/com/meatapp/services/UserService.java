package br.com.meatapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.User;
import br.com.meatapp.repositories.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		public List <User> findAll(){
			return userRepository.findAll();
		}
}
