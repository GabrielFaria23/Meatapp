package br.com.meatapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.User;
import br.com.meatapp.exception.ObjectNotFoundException;
import br.com.meatapp.repositories.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		public List <User> findAll(){
			return userRepository.findAll();
		}
		
		public User findByID(Integer id) {
			Optional<User> user = userRepository.findById(id);
			return user.orElseThrow(() ->
				new ObjectNotFoundException("Usuário não encontrado! ID: "+ id)) ;
		}
}
