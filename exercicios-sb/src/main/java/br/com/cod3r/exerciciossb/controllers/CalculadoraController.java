package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*Esta classe cria uma web service que soma e subtrai, a função de soma está mapeada com variáveis de caminho (@pathVariable) e
 * a função de soma está mapeada com parâmetros de requisições (@RequestParam). Esta claasse também é um controller REST (@Rest
 * Controler).*/

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController 
{
	/*/calculadora/somar/a/b*/
	@GetMapping("/somar/{a}/{b}")
	public int somar(@PathVariable int a, @PathVariable int b)
	{
		return a + b;
	}
	
	/*/calculadora/subtrair?a=num&b=num**/
	@GetMapping("/subtrair")
	public int subtrair
		(@RequestParam(name = "a") int a,
		@RequestParam(name = "b") int b)
	{
		return a - b;
	}
}
