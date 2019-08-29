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
		public User findByEmail(String email) {
			Optional<User> user = userRepository.findByEmail(email);
			return user.orElseThrow(() ->
				new ObjectNotFoundException("Email não encontrado!")) ;
		}
		public User insert(User user) {
			user.setId(null);
			return userRepository.save(user);
		}
		public void delete(User user) {
			userRepository.delete(user);
		}
		public User update(User user) {
			return userRepository.save(user);
		}
}
