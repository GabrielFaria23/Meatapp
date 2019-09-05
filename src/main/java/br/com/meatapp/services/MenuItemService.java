package br.com.meatapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.exception.DataIntegrityException;
import br.com.meatapp.exception.ObjectNotFoundException;
import br.com.meatapp.repositories.MenuItemRepository;

@Service	
public class MenuItemService {
	
	@Autowired
	private MenuItemRepository MenuItemRepository;
	
	public List<MenuItem> findAll(){
		return MenuItemRepository.findAll();
	}
	
	public MenuItem findByID(Integer id) {
		Optional<MenuItem> menuItem = MenuItemRepository.findById(id);
		return menuItem.orElseThrow(() ->
			new ObjectNotFoundException("Menu Item não encontrado! ID: "+ id)) ;
	}
	public MenuItem insert(MenuItem menuItem) {
		menuItem.setId(null);
		return MenuItemRepository.save(menuItem);
	}
	public MenuItem update(MenuItem menuItem, Integer id) {
		menuItem.setId(id);
		return MenuItemRepository.save(menuItem);
	}
	public void delete(Integer id) {
		this.findByID(id);
		try {
			MenuItemRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Ocorreu um erro de integridade! O Menu Item já possui pedidos.");
		}
		
	}
}
