package br.com.musicianapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicianapp.domain.EntidadeDominio;
import br.com.musicianapp.domain.Pessoa;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private Facade facade;
	
	@Autowired
	private Pessoa pessoa;
	
	@CrossOrigin
	@GetMapping("{id}")
	public List<Pessoa> consultarPessoas(@PathVariable int id){
		this.pessoa.setId(id);
		List<EntidadeDominio> entidades = facade.consultar(this.pessoa);
		return null;	
	}
	@CrossOrigin
	@PostMapping
	public Pessoa salvarPessoa(@RequestBody Pessoa pessoa) {
		System.out.println("requisição");
		EntidadeDominio e = facade.salvar(pessoa);
		if(e!=null)
			return (Pessoa) e;
		else 
			return null;
	}
	
	@PutMapping("{id}")
	public EntidadeDominio alterarPessoa(@PathVariable int id, @RequestBody Pessoa pessoa) {
		pessoa.setId(id);
		return this.facade.alterar(pessoa);
	}
	
	@DeleteMapping
	public void deletarPessoa(@PathVariable int id) {
		this.pessoa.setId(id);
		this.facade.apagar(pessoa);
		
	}
}
