package br.com.cod3r.exerciciossb.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;	

import br.com.cod3r.exerciciossb.model.entities.Produto;
/*Esta interface será responsável por gerenciar a Entidade Produto, a interface extende CrudRepository que usa generics para definir o tipo de Entidade que 
 * será gerenciada, o segundo parâmetro do generics refer-se ao tipo do Id da entidade. Como o tipo do id de Produto é do tipo int, então, o equivalente 
 * à int é o Integer, pois generics não aceita tipos primitivos de parâmetro.
 * Alterando a interface pai em 25/08/2022 para PagingAndSortRepository<T, ID>, se trata de uma versão mais robusta de CrudRepository<T, ID>. A nova interface pai
 * possui todos os métodos que CrudRepository<T, ID> possui, além de ter outros métodos à mais, o que torna a manutenção mais interessante pois não irá causar
 * efeito colateral nas outras clases (implementadoras) nem na interface ProdutoRepository<Produto, Integer> que agora extende PagingAndSortingRepository<T, ID>*/
public interface ProdutoRepository  extends  PagingAndSortingRepository<Produto, Integer>
{
	
}
