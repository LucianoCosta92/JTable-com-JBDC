package br.com.luciano.app.facade;

import java.sql.SQLException;
import java.util.List;

import br.com.luciano.app.dao.ILivroDAO;
import br.com.luciano.app.dao.LivroDAO;
import br.com.luciano.app.entity.Livro;

public class LivroFacade {
	
	private ILivroDAO dao;

	public LivroFacade() {
		this.dao = new LivroDAO();
	}
	
	public int save(Livro livro) throws SQLException {
		return dao.save(livro);
	}
	
	public int update(Livro livro) throws SQLException {
		return dao.update(livro);
	}
	
	public int remove(Long id) throws SQLException {
		return dao.remove(id);
	}
	
	public List<Livro> findAll() throws SQLException{
		return dao.findAll();
	}
	
	
}
