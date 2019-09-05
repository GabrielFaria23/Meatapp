package br.com.meatapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.exception.DataIntegrityException;
import br.com.meatapp.exception.ObjectNotFoundException;
import br.com.meatapp.repositories.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> findAll(){
		return restaurantRepository.findAll();
	}
	
	public Restaurant findByID(Integer id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		return restaurant.orElseThrow(() ->
			new ObjectNotFoundException("Restaurante não encontrado! ID: "+ id)) ;
	}
	public Restaurant insert(Restaurant restaurant) {
		restaurant.setId(null);
		return restaurantRepository.save(restaurant);
	}
	public Restaurant update(Restaurant restaurant, Integer id) {
		restaurant.setId(id);
		return restaurantRepository.save(restaurant);
	}
	public void delete(Integer id) {
		this.findByID(id);
		try {
			restaurantRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Ocorreu um erro de integridade! O usuário já possui pedidos.");
		}
		
	}
}
