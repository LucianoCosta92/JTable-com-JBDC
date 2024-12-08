package br.com.luciano.app.JTable;

import java.sql.SQLException;

import br.com.luciano.app.dao.DBConnection;
import br.com.luciano.app.form.LivroForm;

public class Application {

    public static void main(String[] args) throws SQLException {
    	DBConnection.createTable();
    	new LivroForm();
    }
}
