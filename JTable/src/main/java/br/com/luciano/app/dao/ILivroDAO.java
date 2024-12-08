package br.com.luciano.app.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.luciano.app.entity.Livro;

public interface ILivroDAO {
	int save(Livro livro) throws SQLException;
	int update(Livro livro) throws SQLException;
	int remove(Long id) throws SQLException;
	List<Livro> findAll() throws SQLException;
}
