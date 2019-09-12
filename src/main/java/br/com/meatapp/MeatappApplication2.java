package br.com.meatapp;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.domain.OrderItem;
import br.com.meatapp.domain.OrderItemPK;
import br.com.meatapp.domain.Orders;
import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.domain.User;
import br.com.meatapp.repositories.MenuItemRepository;
import br.com.meatapp.repositories.OrderItemRepository;
import br.com.meatapp.repositories.OrderRepository;
import br.com.meatapp.repositories.RestaurantRepository;
import br.com.meatapp.repositories.UserRepository;

@SpringBootApplication
public class MeatappApplication2 implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(MeatappApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(1, "Gabriel", "gabrielnunesfariapta@hotmail.com", "123");
		User user2 = new User(2, "Guilherme", "guilhermelnunesfariapta@hotmail.com", "123");
		userRepository.saveAll(Arrays.asList(user1,user2));
		
		Restaurant r1 = new Restaurant(1, "Tasty Treats", "Padaria", "40-65m", 4.5, "assets/img/restaurant/tasty.png", "...", "....");
		restaurantRepository.saveAll(Arrays.asList(r1));
		
		MenuItem m1 = new MenuItem(1, "Cup Cake", "Cup Cake recheado de Doce de Leite", 10.0, "null",r1);
		MenuItem m2 = new MenuItem(2, "Sorvete", "Açai", 10.0, "null",r1);
		MenuItem m3 = new MenuItem(3, "Sorvete", "Flocos", 8.0, "null",r1);
		MenuItem m4 = new MenuItem(4, "Cachorro Quente", "Padrão Gurmee", 7.5, "null",r1);
		menuItemRepository.saveAll(Arrays.asList(m1,m2,m3,m4));
		
		Orders o1 = new Orders(1, LocalDateTime.now(), user1, r1, "Rua A", "123", null, "Dinheiro");
		Orders o2 = new Orders(2, LocalDateTime.now(), user2, r1, "Rua B", "12", null, "Cartão de Crédito");
		orderRepository.saveAll(Arrays.asList(o1,o2));
		
		OrderItem oi1 = new OrderItem(new OrderItemPK(o1,1),1,10.0,m2);
		OrderItem oi2 = new OrderItem(new OrderItemPK(o1,2),2,10.0,m1);
		OrderItem oi3 = new OrderItem(new OrderItemPK(o2,1),2,8.0,m3);
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));
	}

}
