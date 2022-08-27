package br.com.cod3r.exerciciossb.controllers;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * @RequestParam double desconto), agora sua assinatura é novoProduto(@Valid Produto produto), e note também que dentro do método, eu não preciso mais criar
	 * um objeto Produto passando os parâmetros, eu sisplesmente declarei que irei receber um produto e salvar este produto na linha 29. O Spring Boot é
	 * suficientemente inteligente para pegar os parâmetros da requisição post e inseri-los no objeto de forma automática.
	 * A anotação @Valid serve para aplicar as validações (dentro de javax.validation.constraints) declaradas na classe 
	 * Produto em cima de seus atributos. Caso a requisição post não esteja de acordo com as validações declaradas. Ocorrerá
	 * uma BadRequest, que acontece no lado cliente. E caso o @Valid não seja declarado em cima do parâmetro Produto produto, ocorrerá um erro 500 e
	 * a aplicação irá quebrar e ficar fora do ar. Agora o método @PostMapping public Produto novoProduto(@Valid Produto produto) foi alterado para 
	 * @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}) public Produto salvarProduto(@Valid Produto produto) para suportar reuisições http Post e Put
	 * num mesmo método Java*/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}) @ResponseBody public Produto salvarProduto(@Valid Produto produto)
	{
		produtoRepository.save(produto);
		return produto;
	}
	
	/*Uma requisição http get para a url api/produtos irá chamar este método que irá listar todos os produtos da tabela produtos no banco de dados..*/
	@GetMapping
	public Iterable<Produto> obterProdutos()
	{
		return produtoRepository.findAll();
	}
	
	/*Fazendo uma consulta por página, o objeto Pageable page é construído a partir do método PageRequest.of(numeroPagina, qtdePagina), onde o primeiro parâmetro representa o 
	 * número da página, já o segundo representa o total de elementos da página. Para este método ser acessado, é necessário que a url seja pelo caminho http://localhost:8080/api/produtos/
	 * pagina/numeroPagina/qtdePagina. O método também limita o atributo qtdePagina, que representa a quantidade de elementos por página, isso acontece porque há o risco de 
	 * o usuário requisitar um número muito alto de elementos por página, sendo assim, o valor máximo que esta variável pode portar é 5, ou seja, no máximo 5 elementos por
	 * página */
	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}") // O parâmetro da url deve ter o mesmo nome do parâmetro do método
	public Iterable<Produto> obterProdutosPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina)
	{
		if(qtdePagina > 5) qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return produtoRepository.findAll(page);
	}
	
	/*http://localhost:8080/api/produtos/número inteiro. Esse método é ativado por uma requisição get, porém com um / seguido
	 * do parâmetro inteiro para achar o objeto pesquisado pelo id. Caso o id passado de parâmetro não esteja associado à 
	 * nenhum registro da tabela dentro do banco de dados, o JSON retornado será vazio, mas a aplicação não irá quebrar, pois
	 * o objeto Optional<T> serve justamente para este tipo de situação, para evitar NullPointerException. Lembrando que
	 * ao especificar o path do método http get, é preciso usar a anotação @PathVariable sobre o parâmetro passado.*/
	@GetMapping(path = "/{id}") // O parâmetro da url deve ter o mesmo nome do parâmetro do método
	public Optional<Produto> obterProdutoPorId(@PathVariable int id)
	{	
		return produtoRepository.findById(id);
	}
	
	/*Aqui nós temos mais um maravilhoso exemplo de injeção de dependência que o Spring nos oferece. Acontece que ao ser declarado na interface ProdutoRepository<Produto, 
	 * Integer>, o método public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome); o Spring automaticamente fornece uma implementação do método (repare 
	 * dentro do método, o return produtoRepository.findByNomeContainingIgnoreCase(parteNome)). Isso acontece porque existe uma convenção de injeção de dependêcias para 
	 * métodos, para que a injeção funcione, é preciso que o nome do método siga uma convenção, se você tentar usar um nome que não existe na convenção, o Spring não con
	 * segue fornecer uma implementação, exemplo: findByNomeContainingIgnoreCaso já não funciona, pois a convenção de nomes para métodos de interface é em inglês, no caso
	 * do método  findByNomeContainingIgnoreCase, o atributo nome (que está em portugês, ao invés de name, em inglês) não atrapalha na implementação pois nome é o nome
	 * do atributo, como o desenvolvedor pode dar qualquer nome para qualquer atributo, o nome do atributo e muito menos o seu idioma não interfere na nomenclatura. 
	 * Obviamente, esse nome (findByNomeIgnoreCase) não funciona se o parâmetro for um inteiro ao invés de uma String, já que  a nomenclatura findBy****IgnoreCase serve para
	 * Strings, sendo assim, o atributo referenciado deve ser uma string*/
	@RequestMapping(path = "/nome/{parteNome}") // O parâmetro da url deve ter o mesmo nome do parâmetro do método
	public Iterable<Produto> obterProdutoPorNome(@PathVariable String parteNome)
	{
		return produtoRepository.searchByNameLike(parteNome);
	}
	
	/*Este método exclui um produto no banco de dados a partir de um @Path Variable int id. Lembrando que é necessário usar @DeleteMapping(path = "/{id}") para especificar 
	 * que a exclusão se dará por um id de parâmetro na url, e também para diferenciar a url do outro método mais abaixo também mapeado com @DeleteMapping*/
	@DeleteMapping(path = "/{id}") // O parâmetro da url deve ter o mesmo nome do parâmetro do método
	public void excluirProdutoPorId(@PathVariable int id)
	{
		produtoRepository.deleteById(id);
	}
	
	/*Este método também serve para atender uma requisição http delete, mas diferente do método anterior, oferece de parâmetro um @Valid Produto produto*/
	@DeleteMapping
	public void excluirProduto(@Valid Produto produto)
	{
		produtoRepository.delete(produto);
	}
	
	/*A requisição https put tem a função de alterar, no caso do CrudRepository<T, K> void save(S entity), este método também
	 * serve para requisições de tipo put, mas para diferenciar o método de uma requisição https post, usa-se a anotação
	 * @PutMapping ao invés de @PostMapping. Uma requisição http put serve de maneira ideal para o tipo de situação em que deseja-se alterar
	 * por completo o objeto, mas também serve para casos em que a alteração é feita de forma parcial. */
	
//	@PutMapping
//	public Produto alterarProduto(@Valid Produto produto)
//	{
//		produtoRepository.save(produto);
//		return produto;
//	}
}
