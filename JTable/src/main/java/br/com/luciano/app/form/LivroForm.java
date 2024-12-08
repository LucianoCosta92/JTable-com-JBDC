package br.com.luciano.app.form;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.luciano.app.controller.LivroController;
import br.com.luciano.app.entity.Livro;
import br.com.luciano.app.table.LivroCellRenderer;
import br.com.luciano.app.table.LivroTabelModel;
import net.miginfocom.swing.MigLayout;

public class LivroForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel lbEditora, lbTitulo, lbISBN;
	private JTextField txtEditora, txtTitulo, txtISBN;
	private JPanel panelAdd, panelTable, panelButtons;
	private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel; 
	private JTable table;
	private JScrollPane scrollPane;
	private List<Livro> livroList;
	private Long idLivro;
	
	public LivroForm() throws HeadlessException, SQLException {
		super("Cadastro de Livros");
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Livros"));
		panelAdd.setBounds(5,0,480,100);
		
		lbEditora =new JLabel("Editora");
		lbTitulo = new JLabel("Título");
		lbISBN = new JLabel("ISBN");
		
		txtEditora = new JTextField(50);
		txtTitulo = new JTextField(50);
		txtISBN = new JTextField(15);
		
		panelAdd.add(lbEditora);
		panelAdd.add(txtEditora, "span, growx");
		
		panelAdd.add(lbTitulo);
		panelAdd.add(txtTitulo, "span, growx");
		
		panelAdd.add(lbISBN);
		panelAdd.add(txtISBN, "wrap para");
		
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5,105,480,40);
		
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton(new ImageIcon(loader.getResource("img/new.png")));
		btnSave = new JButton(new ImageIcon(loader.getResource("img/save.png")));
		btnCancel = new JButton(new ImageIcon(loader.getResource("img/cancel.png")));
		btnRemove = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
		btnUpdate = new JButton(new ImageIcon(loader.getResource("img/edit.png")));
		
		panelButtons.add(btnNew, "gapleft 90");
		panelButtons.add(btnCancel);
		panelButtons.add(btnSave, "gap unrelated");
		panelButtons.add(btnUpdate, "gap unrelated");
		panelButtons.add(btnRemove);
		
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Lista de livros"));
		panelTable.setBounds(5,150,480,240);
		
		table = new JTable();
		
		scrollPane = new JScrollPane(table);
		
		panelTable.add(scrollPane);
		
		
		refreshTable();
		enableFields(false);
		
		
		add(panelAdd);
		add(panelButtons);
		add(panelTable);
		setMinimumSize(new Dimension(500, 420));
		setVisible(true);
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					onSaveLivro();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancelar();
			}
		});
		
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onNovoLivro();
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					onRemoverLivro();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAlterarLivro();
			}
		});
		
	}
	
	private void onRemoverLivro() throws SQLException {
		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o livro a ser removido!");
			return;
		}
		Livro livro = new LivroTabelModel(livroList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?", "Excluir Livro", JOptionPane.YES_NO_OPTION);
		if (confirm!=0) {
			return;
		}
		int result = new LivroController().excluirLivro(livro.getId());
		if (result == 1) {
			JOptionPane.showMessageDialog(this, "Removido com sucesso!");
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Tente novamente!");
		}
	}
	
	private void onAlterarLivro() {
		int rowIndex = table.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Selecione o livro a ser alterado!");
			return;
		}
		Livro livro = new LivroTabelModel(livroList).get(rowIndex);
		idLivro = livro.getId();
		txtEditora.setText(livro.getEditora());
		txtTitulo.setText(livro.getTitulo());
		txtISBN.setText(livro.getIsbn());
		
		enableFields(true);
	}
	
	private void onNovoLivro() {
		enableFields(true);
	}
	
	private void onSaveLivro() throws SQLException {
		Livro livro = new Livro();
		if (txtEditora.getText().length() > 0 && txtTitulo.getText().length() > 0 && txtISBN.getText().length() > 0) {
			livro.setEditora(txtEditora.getText());
			livro.setTitulo(txtTitulo.getText());
			livro.setIsbn(txtISBN.getText());
		} else {
			JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
			return;
		}
		
		int result = 0;
		if (idLivro == null) {
			result = new LivroController().addLivro(livro);
		} else {
			livro.setId(idLivro);
			result = new LivroController().alterarLivro(livro);
			idLivro = null;
		}
		
		if (result == 1) {
			JOptionPane.showMessageDialog(this, "Livro inserido com sucesso!");
			enableFields(false);
			onCancelar();
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Tente novamente!");
		}
	}
	
	private void onCancelar() {
		txtEditora.setText("");
		txtTitulo.setText("");
		txtISBN.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean b) {
		txtEditora.setEnabled(b);
		txtTitulo.setEnabled(b);
		txtISBN.setEnabled(b);
	}
	
	
	// reinicializa a lista
	private void refreshTable() throws SQLException {
		livroList = new LivroController().findLivros();
		if (livroList != null) {
			table.setModel(new LivroTabelModel(livroList));
			table.setDefaultRenderer(Object.class, new LivroCellRenderer());
		}
	
	}
	
	

}
