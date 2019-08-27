package br.com.meatapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meatapp.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
}
