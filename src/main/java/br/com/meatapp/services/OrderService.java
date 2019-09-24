package br.com.meatapp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatapp.domain.OrderItem;
import br.com.meatapp.domain.Orders;
import br.com.meatapp.exception.ObjectNotFoundException;
import br.com.meatapp.repositories.OrderItemRepository;
import br.com.meatapp.repositories.OrderRepository;

@Service
public class OrderService {
	
		@Autowired
		private UserService userService;
	
		@Autowired
		private OrderRepository orderRepository;
		
		@Autowired
		private OrderItemRepository orderItemRepository;
		
		@Autowired
		private RestaurantService restaurantService;
		
		@Autowired
		private MenuItemService menuItemService;

		public List<Orders> findAll(){
			return orderRepository.findAll();
		}
		
		public Orders findById(Integer id) {
			Optional<Orders> order = orderRepository.findById(id);
			return order.orElseThrow(()->
		    	new ObjectNotFoundException("Pedido não encontrado! ID:" + id));
		}
		
		public Orders insert(Orders order) {
			List <OrderItem> items = order.getOrderItems();
			
			order.setId(null);
			order.setData(LocalDateTime.now());
			order.setUser(userService.findByID(order.getUser().getId()));
			order.setRestaurant(restaurantService.findByID(order.getRestaurant().getId()));
			order.setOrderItems(null);
			order = orderRepository.save(order);
			
			int item = 1;
			for (OrderItem it: items) {
				it.setOrders(order);
				it.setOrderItemId(item);
				it.setMenuItem(menuItemService.findByID(it.getMenuItem().getId()));
				
				orderItemRepository.save(it);
				item ++;
			}
			order.setOrderItems(items);
			return order;
		}
		
		public Orders update(Orders order, Integer id) {
			order.setId(id);
			order.setData(LocalDateTime.now());
			order.setUser(userService.findByID(order.getUser().getId()));
			order.setRestaurant(restaurantService.findByID(order.getRestaurant().getId()));
			order = orderRepository.save(order);
			
			List <OrderItem> items = order.getOrderItems();
			int item = 1;
			for (OrderItem it: items) {
				it.setOrders(order);
				it.setOrderItemId(item);
				it.setMenuItem(menuItemService.findByID(it.getMenuItem().getId()));
				
				orderItemRepository.save(it);
				item ++;
			}
			
			return order;
		}
		
		public void delete(Integer id) {
			this.findById(id);
			try {
				orderRepository.deleteById(id);
			}catch(DataIntegrityViolationException e) {
				throw new DataIntegrityViolationException("Ocorreu um erro de integridade."
						+ "Esta ordem já possui pedidos");
			}
		}
		
}
