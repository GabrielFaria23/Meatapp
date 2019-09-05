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

import br.com.meatapp.domain.MenuItem;
import br.com.meatapp.services.MenuItemService;

@RestController
@RequestMapping(value="menuitens")
public class MenuItemResource {
	
	@Autowired //Inicializa o objeto (Injeção de Variavel /dependência
	private MenuItemService MenuItemService;
	
	@RequestMapping(method=RequestMethod.GET) 
	// direciona as requisições para este metodo
	// se chegar na uri uma solicitação para users e essa solicitação for GET direciona
	//a requisição para este metodo que no caso vai entregar a lista de usuários
	public ResponseEntity<List<MenuItem>> findAll(){ 
		// ResponseEntity serve para converter
		//a lista de usuarios java em uma entidade de resposta. Torna eles mais genericos 
		//para não ter problema no front end
		List<MenuItem> menuItem = MenuItemService.findAll(); 
		// pega lista de usuários que esta 
		// no userService
		return ResponseEntity.ok().body(menuItem); 
		//retorna uma Lista de Usuários que foi pega no users
		// so que essa lista foi modificada para um tipo de arquivo mais generico
		//(JSON) para não ter problemas futuros com o front end e o body serve
		//para colocar a lista de usuarios no corpo
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET) 
	//value é a moldura de como
	// o diretorio (caminho) para acesso ao usuário vai ficar.
	public ResponseEntity<MenuItem> findById(@PathVariable Integer id){ 
		//@Path para vincular
		//que a variavel vai chegar do diretorio com a variavel recebida por parametro 
		MenuItem menuItem = MenuItemService.findByID(id);
		return ResponseEntity.ok().body(menuItem);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<MenuItem> insert(@Valid @RequestBody MenuItem menuItem){
		//o @ serve pra indicar onde que a requisição esta vindo
		//@Valid Valida os campos obrigatórios
		menuItem = MenuItemService.insert(menuItem);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(menuItem.getId())
				.toUri();
				//criando URI
		return ResponseEntity.created(uri).body(menuItem);
		//a linha de coma constroi um caminho para acessar o novo usuario criado, sempre
		// que inserir algo (utilizar o POST) tem que fazer isso 
	}
	
	@RequestMapping(value = "id/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody  MenuItem menuItem){		
		menuItem = MenuItemService.update(menuItem,id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		MenuItemService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
