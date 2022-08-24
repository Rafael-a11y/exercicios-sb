package br.com.cod3r.exerciciossb.controllers;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * equivalente ao id do novo registro gerado no banco de dados springboot dentro do MySql Workbnech.
	 * Veja que agora, eu sisplesmente retirei os vários parâmetros do método novoProduto(@RequestParam Strin nome, @RequestParam double preco, 
	 * @RequestParam double desconto), agora sua assinatura é novoProduto(Produto produto), e note também que dentro do método, eu não preciso mais criar
	 * um objeto Produto passando os parâmetros, eu sisplesmente declarei que irei receber um produto e salvar este produto na linha 29. O Spring Boot é
	 * suficientemente inteligente para pegar os parâmetros da requisição post e inseri-los no objeto de forma automática.
	 * A anotação @Valid serve para aplicar as validações (dentro de javax.validation.constraints) declaradas na classe 
	 * Produto em cima de seus atributos. Caso a requisição post não esteja de acordo com as validações declaradas. Ocorrerá
	 * uma BadRequest, que acontece no lado cliente. E caso o @Valid não seja declarado em cima do parâmetro Produto produto, ocorrerá um erro 500 e
	 * a aplicação irá quebrar e ficar fora do ar*/
	@PostMapping @ResponseBody public Produto novoProduto(@Valid Produto produto)
	{
		produtoRepository.save(produto);
		return produto;
	}
	
	/*Uma reqiosição get para a url api/produtos irá chamar este método.*/
	@GetMapping
	public Iterable<Produto> obterProdutos()
	{
		return produtoRepository.findAll();
	}
	
	/*http://localhost:8080/api/produtos/número inteiro. Esse método é ativado por uma requisição get, porém com um / seguido
	 * do parâmetro inteiro para achar o objeto pesquisado pelo id. Caso o id passado de parâmetro não esteja associado à 
	 * nenhum registro da tabela dentro do banco de dados, o JSON retornado será vazio, mas a aplicação não irá quebrar, pois
	 * o objeto Optional<T> serve justamente para este tipo de situação, para evitar NullPointerException. Lembrando que
	 * ao especificar o path do método http get, é preciso usar a anotação @PathVariable sobre o parâmetro passado.*/
	@GetMapping(path = "/{id}")
	public Optional<Produto> obterProdutoPorId(@PathVariable int id)
	{
		return produtoRepository.findById(id);
	}
}
