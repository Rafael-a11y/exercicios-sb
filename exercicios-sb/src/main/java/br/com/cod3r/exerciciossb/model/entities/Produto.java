package br.com.cod3r.exerciciossb.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@Entity public class Produto 
{
	@Id() @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	
	/* Anotação @NotBlank do pacote javax.validation.constraints, serve para não permitir valor vazio, não nulo,
	é como o nullable = false do JPA, mas é uma anotação fornecida pelo próprio Java. Porém, a biblioteca javax.validation.constraints 
	precisa ser adiquirida pela dependêcia <groupId>javax.validation</groupId><artifactId>validation-api</artifactId> no 
	pom.xml*/
	@NotBlank
	private String nome;
	
	/* Anotação @Min (value = 0) do pacote javax.validation.constraints, serve para declarar valor mínimo, e não nulo
	 * obviamente, é uma anotação fornecida pelo próprio Java. Porém, a biblioteca javax.validation.constraints precisa
	 * ser adiquirida pela dependêcia <groupId>javax.validation</groupId><artifactId>validation-api</artifactId> no pom.xml*/
	@Min(value = 0)
	private double preco;
	
	/* Anotação @Max (value = 1) do pacote javax.validation.constraints, serve para declarar valor máximo, e não nulo
	 * obviamente, é uma anotação fornecida pelo próprio Java. Porém, a biblioteca javax.validation.constraints precisa
	 * ser adiquirida pela dependêcia <groupId>javax.validation</groupId><artifactId>validation-api</artifactId> no pom.xml*/
	@Min(value = 0) 
	@Max(value = 1)
	private double desconto;
	
	public Produto() {}
	
	public Produto(String nome) {
		this.nome = nome;
	}

	public Produto(String nome, double preco, double desconto)
	{
		this.nome = nome;
		this.preco = preco;
		this.desconto = desconto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
}
