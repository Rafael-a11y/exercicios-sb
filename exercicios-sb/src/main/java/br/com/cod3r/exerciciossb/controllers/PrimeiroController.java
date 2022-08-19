package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/*A anotação @RestController serve para dizer ao Spring que esta é uma classe controladora que irá conter os métodos mapeados para url's*/
@RestController
public class PrimeiroController 
{
//	//Por padrão, o atributo method dentro de @RequestMapping é definido como get.
//	@RequestMapping(method = RequestMethod.GET, path = 	{"/ola", "/saudacao"})
	/*A anotação @GetMapping mapeia uma requisição http do tipo get, é mais específica. Assim como o @RequestMapping, seu atributo path aceita um array de strings para especificar
	 * um caminho para que o método retorne o valor para a requisição get*/
	@GetMapping(path = {"/ola", "/saudacao"})
	public String ola()
	{
		return "Olá Spring Boot, seu filha da puta corno!";
	}
}
