package br.com.cod3r.exerciciossb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exerciciossb.model.entities.Produto;
import br.com.cod3r.exerciciossb.model.repositories.ProdutoRepository;
/*Definindo a url que acessará esta classe controladora, dentro da anotação @RequestMapping*/
@RestController @RequestMapping("api/produtos")	public class ProdutoController 
{
	/*A anotação @Autowired serve para que o Spring, no momento do uso da variável produtoRepository for 
	 * usada, uma instância seja criada para esta variável.*/
	@Autowired private ProdutoRepository produtoRepository;
	
	/*A anotação @PostMapping serve para que o método novoProduto seja invocado caso uma requisição http post
	 * seja enviada.
	 * O ProdutoRepository save(S entity) irá salvar o objeto produto no banco de dados de forma devidamente
	 * mapeada, e o JSON retornado (Objetos retornados para o browser são convertidos em JSON) terá id
	 * equivalente ao id do novo registro gerado no banco de dados springbhoot dentro do MySql Workbnech.*/
	@PostMapping @ResponseBody public Produto novoProduto(@RequestParam String nome)
	{
		Produto produto = new Produto(nome);
		produtoRepository.save(produto);
		return produto;
	}
}
