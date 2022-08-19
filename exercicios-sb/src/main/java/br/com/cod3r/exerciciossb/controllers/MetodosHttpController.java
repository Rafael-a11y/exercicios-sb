package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Usando a anotação @RequestMapping com o atributo path = /metodos, define esta url como padrão para todos os métodos declarados, cada um irá retornar um valor textual*/
@RestController
@RequestMapping(path = "/metodos")
public class MetodosHttpController 
{
	
	/*Mapeando este método como get*/
	@GetMapping
	public String get()
	{
		return "Requisição GET";
	}
	
	/*Mapeando este método como post*/
	@PostMapping
	public String post()
	{
		return "Requisição POST";
	}
	
	/*Mapeando este método como put*/
	@PutMapping
	public String put()
	{
		return "Requisição PUT";
	}
	
	/*Mapeando este método como patch*/
	@PatchMapping
	public String patch()
	{
		return "Requisição PATCH";
	}
	
	/*Mapeando este método como delete*/
	@DeleteMapping
	public String delete()
	{
		return "Requisição DELETE";
	}
}
