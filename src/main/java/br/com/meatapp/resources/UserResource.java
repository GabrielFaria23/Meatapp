package br.com.meatapp.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.meatapp.domain.User;
import br.com.meatapp.services.UserService;

@RestController // End point
@RequestMapping(value="users") //mapeamento de onde os dados serão buscados
//o value serve pra mostrar por qual porta a entrada sera feita na API,
//todo diretorio com /users vem pra essa classe
public class UserResource {
	
	@Autowired //Inicializa o objeto (Injeção de Variavel /dependência
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET) // direciona as requisições para este metodo
	// se chegar na uri uma solicitação para users e essa solicitação for GET direciona
	//a requisição para este metodo que no caso vai entregar a lista de usuários
	public ResponseEntity<List<User>> findAll(){ // ResponseEntity serve para converter
		//a lista de usuarios java em uma entidade de resposta. Torna eles mais genericos 
		//para não ter problema no front end
		List<User> users = userService.findAll(); // pega lista de usuários que esta 
		// no userService
		return ResponseEntity.ok().body(users); //retorna uma Lista de Usuários que foi pega no users
		// so que essa lista foi modificada para um tipo de arquivo mais generico
		//(JSON) para não ter problemas futuros com o front end e o body serve
		//para colocar a lista de usuarios no corpo
	}
	
	@RequestMapping(value="id/{id}" ,method=RequestMethod.GET) //value é a moldura de como
	// o diretorio (caminho) para acesso ao usuário vai ficar.
	public ResponseEntity<User> findById(@PathVariable Integer id){ //@Path para informar
		//que a variavel vai chegar no meio do caminho.
		User user = userService.findByID(id);
		return ResponseEntity.ok().body(user);
	}
	
}
