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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatapp.domain.User;
import br.com.meatapp.services.UserService;

@RestController // End point
@RequestMapping(value="users") //mapeamento de onde os dados serão buscados
//o value serve pra mostrar por qual porta a entrada sera feita na API,
//todo diretorio com /users vem pra essa classe
public class UserResource {
	
	@Autowired //Inicializa o objeto (Injeção de Variavel /dependência
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET) 
	// direciona as requisições para este metodo
	// se chegar na uri uma solicitação para users e essa solicitação for GET direciona
	//a requisição para este metodo que no caso vai entregar a lista de usuários
	public ResponseEntity<List<User>> findAll(){ 
		// ResponseEntity serve para converter
		//a lista de usuarios java em uma entidade de resposta. Torna eles mais genericos 
		//para não ter problema no front end
		List<User> users = userService.findAll(); 
		// pega lista de usuários que esta 
		// no userService
		return ResponseEntity.ok().body(users); 
		//retorna uma Lista de Usuários que foi pega no users
		// so que essa lista foi modificada para um tipo de arquivo mais generico
		//(JSON) para não ter problemas futuros com o front end e o body serve
		//para colocar a lista de usuarios no corpo
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET) 
	//value é a moldura de como
	// o diretorio (caminho) para acesso ao usuário vai ficar.
	public ResponseEntity<User> findById(@PathVariable Integer id){ 
		//@Path para vincular
		//que a variavel vai chegar do diretorio com a variavel recebida por parametro 
		User user = userService.findByID(id);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = "/email", method=RequestMethod.GET)
	public ResponseEntity<User> findByEmail(@RequestParam(value = "email") String email){
		//@Request Param é um parametro da aplicação, não faz parte da url muda no value de cima
		//http://localhost:8082/users/email?email=gabrielnunesfariapta@hotmail.com
		User user = userService.findByEmail(email);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> insert(@Valid @RequestBody User user){
		//o @ serve pra indicar onde que a requisição esta vindo
		//@Valid Valida os campos obrigatórios
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id/{id}")
				.buildAndExpand(user.getId())
				.toUri();
				//criando URI
		return ResponseEntity.created(uri).body(user);
		//a linha de coma constroi um caminho para acessar o novo usuario criado, sempre
		// que inserir algo (utilizar o POST) tem que fazer isso 
	}
	
	@RequestMapping(value = "id/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody  User user){		
		user = userService.update(user,id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
