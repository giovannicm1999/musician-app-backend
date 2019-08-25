package br.com.musicianapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.musicianapp.daos.AbstractDao;
import br.com.musicianapp.daos.CartaoDao;
import br.com.musicianapp.daos.EnderecoDao;
import br.com.musicianapp.daos.IDAO;
import br.com.musicianapp.daos.PessoaDao;
import br.com.musicianapp.daos.TelefoneDao;
import br.com.musicianapp.daos.UsuarioDao;
import br.com.musicianapp.domain.Cartao;
import br.com.musicianapp.domain.Endereco;
import br.com.musicianapp.domain.EntidadeDominio;
import br.com.musicianapp.domain.Pessoa;
import br.com.musicianapp.domain.Telefone;
import br.com.musicianapp.domain.Usuario;
import br.com.musicianapp.impl.IStyleQuery;

@Service
public class Facade implements IFacade,IStyleQuery{
	
	@Autowired private PessoaDao pessoaDao;
	@Autowired private TelefoneDao telefoneDao;
	@Autowired private CartaoDao cartaoDao;
	@Autowired private EnderecoDao enderecoDao;
	@Autowired private UsuarioDao usuarioDao;
	
	private IDAO dao;
	private Map<String,IDAO> daos;
	private String parametro;
	
	private void startMaps() {
		daos = new HashMap<String, IDAO>();
		daos.put(Pessoa.class.getName(), pessoaDao);
		daos.put(Telefone.class.getName(), telefoneDao);
		daos.put(Cartao.class.getName(), cartaoDao);
		daos.put(Endereco.class.getName(), enderecoDao);
		daos.put(Usuario.class.getName(), usuarioDao);

	}
	private IDAO getDaoInstance(EntidadeDominio entidade) {
		startMaps();
		this.dao =daos.get(entidade.getClass().getName());
		if(parametro!=null) {
			AbstractDao absDao = (AbstractDao)dao;
			absDao.setParametro(parametro);
			parametro=null;
		}
		return this.dao;
	}
	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		return getDaoInstance(entidade).salvar(entidade);
	}

	@Override
	public EntidadeDominio alterar(EntidadeDominio entidade) {
		return getDaoInstance(entidade).alterar(entidade);
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return getDaoInstance(entidade).consultar(entidade);
	}

	@Override
	public void apagar(EntidadeDominio entidade) {
		getDaoInstance(entidade).apagar(entidade);

	}
	@Override
	public String getParametro() {
		AbstractDao absDao = (AbstractDao)dao;
		return absDao.getParametro();
	}
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	
}
