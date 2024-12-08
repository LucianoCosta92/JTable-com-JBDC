package br.com.luciano.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.luciano.app.entity.Livro;

public class LivroDAO implements ILivroDAO{
	
	private static final String SQL_INSERT = "insert into Biblioteca(EDITORA, TITULO, ISBN) values(?,?,?)";
	
	private static final String SQL_UPDATE = "update Biblioteca set EDITORA = ?, TITULO = ?, ISBN = ? where ID = ?";
	
	private static final String SQL_DELETE = "delete from Biblioteca where ID = ?";
	
	private static final String SQL_FIND_ALL = "select * from Biblioteca";

	@Override
	public int save(Livro livro) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(SQL_INSERT);
			pstm.setString(1, livro.getEditora());
			pstm.setString(2, livro.getTitulo());
			pstm.setString(3, livro.getIsbn());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			try {
				if (conn!=null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(conn, pstm, null);
			}
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Livro livro) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, livro.getEditora());
			pstm.setString(2, livro.getTitulo());
			pstm.setString(3, livro.getIsbn());
			pstm.setLong(4, livro.getId());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			try {
				if (conn!=null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(conn, pstm, null);
			}
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int remove(Long id) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int result = 0;
		try {
			pstm = conn.prepareStatement(SQL_DELETE);
			pstm.setLong(1, id);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			try {
				if (conn!=null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(conn, pstm, null);
			}
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Livro> findAll() throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Livro> livros = new ArrayList<>();
		try {
			pstm = conn.prepareStatement(SQL_FIND_ALL);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getLong("ID"));
				livro.setEditora(rs.getString("EDITORA"));
				livro.setTitulo(rs.getString("TITULO"));
				livro.setIsbn(rs.getString("ISBN"));
				livros.add(livro);
			}
		} catch (SQLException e) {
			try {
				if (conn!=null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				DBConnection.close(conn, pstm, rs);
			}
			e.printStackTrace();
		}
		return livros;
	}

}
