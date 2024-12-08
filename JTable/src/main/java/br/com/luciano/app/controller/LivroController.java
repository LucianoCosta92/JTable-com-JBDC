package br.com.luciano.app.controller;

import java.sql.SQLException;
import java.util.List;
import br.com.luciano.app.entity.Livro;
import br.com.luciano.app.facade.LivroFacade;

public class LivroController {
	private LivroFacade facade;

	public LivroController() {
		this.facade = new LivroFacade();
	}
	
	public int addLivro(Livro livro) throws SQLException {
		return facade.save(livro);
	}
	
	public int alterarLivro(Livro livro) throws SQLException {
		return facade.update(livro);
	}
	
	public int excluirLivro(Long id) throws SQLException {
		return facade.remove(id);
	}
	
	public List<Livro> findLivros() throws SQLException{
		return facade.findAll();
	}
}
