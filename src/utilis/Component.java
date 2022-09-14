package utilis;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
public interface Component {
	
	public static JTable getJtable() {
		
		JTable table= new JTable(){
			public boolean isCellEditable(int row, int column) {return false;};
			};
	    
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(23);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font(Font.SANS_SERIF,Font.BOLD , 15) );
		table.setFont(new Font(Font.SANS_SERIF,Font.PLAIN , 15) );
		table.setSelectionBackground(Color.BLACK);
		table.setSelectionForeground(Color.WHITE);
		
		return table;
	}

}
