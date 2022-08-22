package br.com.cod3r.exerciciossb.model.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.cod3r.exerciciossb.model.entities.Produto;
/*Esta interface será responsável por gerenciar a Entidade Produto, a interface extende CrudRepository que usa generics para definir o tipo de Entidade que 
 * será gerenciada, o segundo parâmetro do generics refer-se ao tipo do Id da entidade. Como o tipo do id de Produto é do tipo int, então, o equivalente 
 * à int é o Integer, pois generics não aceita tipos primitivos de parâmetro.*/
public interface ProdutoRepository  extends CrudRepository<Produto, Integer>
{
	
}
