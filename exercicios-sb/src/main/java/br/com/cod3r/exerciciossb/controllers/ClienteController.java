package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exerciciossb.model.entities.Cliente;
/*A anotação @RestController serve para dizer ao Spring que esta é uma classe controladora que irá conter os métodos mapeados para url's*/
@RestController
@RequestMapping("/clientes")
public class ClienteController 
{
	/*Este método irá retornar um objeto para a web service, objeto este que aparecerá no browser como json. Para que a requisição get seja executada para este método, deve-se
	 * usar a url localhost:8080/qualquer/*/
	@GetMapping(path = "/qualquer") 
	public Cliente getCliente()
	{
		return new Cliente(1111, "Pedrovisk", "123.456.789-00");
	}
	
	/*O valor da anotação @GetMapping ('/{nome}') serve para definir a url como 'localhost:8080/clientes/o atributo nome que será usado como parâmetro da url'. A anotação
	 * @PathVariable serve para mapear o parâmetro java para o parâmetro da url. Após o devido mapeamento, use a url 'localhost:8080/clientes/qualquer id' pois neste mmomento
	 * o parâmetro da url estará mapeado para o parâmetro do método Java, sendo assim, a requisição get é feita e o valor devolvido é um json com o valor da variável sendo
	 * usada para preencher o atributo de id do objeto Cliente (Valores complexos como objetos são retornados como Json nas requisições). */
	@GetMapping("/{id}")
	public Cliente obterClientePorId1(@PathVariable	int id)
	{
		return new Cliente(id, "Maria Madalena", "987.654.321.-00");
	}
	
	/*Diferente do método acima, este segundo método mapeado como http get (definido como @GetMapping), mas diferente do método anterior, este ão usa @PathVariable, mas sim
	 * @RequestParam para atender as conveções de url, a anotação (@RequestParam(name = 'id') int id) define que o parâmetro id recebido pelo método irá preencher o parâmetro
	 * de url id. Nas convenções de url, os parâmetros devem ser destacados a partir de uma interrogação, o E comercial serve para separar os parâmetros, exemplo: localhost:8080/
	 *clientes?id=123. Sem a anotação @GetMapping, a requisição get não é executada para este método. O atributo defaultValue da anotação @RequestParam define um valor padrão
	 *caso nenhum parâmetro seja passado para a url*/
	@GetMapping
	public Cliente obterClientePorId2(@RequestParam(name = "id", defaultValue = "1") int id)
	{
		return new Cliente(id, "João Augusto", "111.222.333-44");
	}
}	
