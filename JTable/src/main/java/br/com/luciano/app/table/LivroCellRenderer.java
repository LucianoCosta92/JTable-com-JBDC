package br.com.luciano.app.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LivroCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (row % 2 == 0) {
			setBackground(Color.YELLOW);
		} else {
			setBackground(null);
		}
		
		if (isSelected) {
			setBackground(Color.ORANGE);
		}
		
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(400);
		table.getColumnModel().getColumn(2).setMaxWidth(500);
		table.getColumnModel().getColumn(3).setMaxWidth(200);
		
		
		return this;
	}
	
	
	
}
