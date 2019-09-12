package br.com.meatapp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatapp.domain.OrderItem;
import br.com.meatapp.domain.Orders;
import br.com.meatapp.services.OrderService;

@RestController
@RequestMapping(value="orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method=RequestMethod.GET) 
	// direciona as requisições para este metodo
	// se chegar na uri uma solicitação para users e essa solicitação for GET direciona
	//a requisição para este metodo que no caso vai entregar a lista de usuários
	public ResponseEntity<List<Orders>> findAll(){ 
		// ResponseEntity serve para converter
		//a lista de usuarios java em uma entidade de resposta. Torna eles mais genericos 
		//para não ter problema no front end
		List<Orders> orders = orderService.findAll(); 
		// pega lista de usuários que esta 
		// no userService
		return ResponseEntity.ok().body(orders); 
		//retorna uma Lista de Usuários que foi pega no users
		// so que essa lista foi modificada para um tipo de arquivo mais generico
		//(JSON) para não ter problemas futuros com o front end e o body serve
		//para colocar a lista de usuarios no corpo
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET) 
	public ResponseEntity<Orders> findById(@PathVariable Integer id){ 
		Orders order = orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Orders> insert(@Valid @RequestBody Orders order){
		//o @ serve pra indicar onde que a requisição esta vindo
		//@Valid Valida os campos obrigatórios
		order = orderService.insert(order);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(order.getId())
				.toUri();
				//criando URI
		return ResponseEntity.created(uri).body(order);
		//a linha de coma constroi um caminho para acessar o novo usuario criado, sempre
		// que inserir algo (utilizar o POST) tem que fazer isso 
	}
	
	@RequestMapping(value = "id/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody  Orders order){		
		order = orderService.update(order,id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
