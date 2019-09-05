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

import br.com.meatapp.domain.Restaurant;
import br.com.meatapp.services.RestaurantService;

@RestController
@RequestMapping(value="restaurants")
public class RestaurantResource {
		
		@Autowired //Inicializa o objeto (Injeção de Variavel /dependência
		private RestaurantService restaurantService;
		
		@RequestMapping(method=RequestMethod.GET) 
		// direciona as requisições para este metodo
		// se chegar na uri uma solicitação para users e essa solicitação for GET direciona
		//a requisição para este metodo que no caso vai entregar a lista de usuários
		public ResponseEntity<List<Restaurant>> findAll(){ 
			// ResponseEntity serve para converter
			//a lista de usuarios java em uma entidade de resposta. Torna eles mais genericos 
			//para não ter problema no front end
			List<Restaurant> restaurantes = restaurantService.findAll(); 
			// pega lista de usuários que esta 
			// no userService
			return ResponseEntity.ok().body(restaurantes); 
			//retorna uma Lista de Usuários que foi pega no users
			// so que essa lista foi modificada para um tipo de arquivo mais generico
			//(JSON) para não ter problemas futuros com o front end e o body serve
			//para colocar a lista de usuarios no corpo
		}
		
		@RequestMapping(value="id/{id}" ,method=RequestMethod.GET) 
		//value é a moldura de como
		// o diretorio (caminho) para acesso ao usuário vai ficar.
		public ResponseEntity<Restaurant> findById(@PathVariable Integer id){ 
			//@Path para vincular
			//que a variavel vai chegar do diretorio com a variavel recebida por parametro 
			Restaurant restaurant = restaurantService.findByID(id);
			return ResponseEntity.ok().body(restaurant);
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Restaurant> insert(@Valid @RequestBody Restaurant restaurant){
			//o @ serve pra indicar onde que a requisição esta vindo
			//@Valid Valida os campos obrigatórios
			restaurant = restaurantService.insert(restaurant);
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/id/{id}")
					.buildAndExpand(restaurant.getId())
					.toUri();
					//criando URI
			return ResponseEntity.created(uri).body(restaurant);
			//a linha de coma constroi um caminho para acessar o novo usuario criado, sempre
			// que inserir algo (utilizar o POST) tem que fazer isso 
		}
		
		@RequestMapping(value = "id/{id}",method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody  Restaurant restaurant){		
			restaurant = restaurantService.update(restaurant,id);
			return ResponseEntity.noContent().build();
		}
		@RequestMapping(value = "id/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			restaurantService.delete(id);
			return ResponseEntity.noContent().build();
		}
}
