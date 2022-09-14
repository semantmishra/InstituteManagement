package Form.enquery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;

import Listeners.enquery.EnqueryListenr;
import utilis.Icons;

public class ManageEnquery extends JFrame {

	public JPopupMenu enqueryMenu,exportMenu;
	public JButton addNewEnquery,export;
	public JToolBar bar;
	public JTable table;
	public JMenuItem deleteRecord,updateRecord,printPreview,pdf,csv,html,excel;
	
	public ManageEnquery(){
		super("Manage Enquery");
		bar = new JToolBar();
		table = utilis.Component.getJtable();
		
		bar.setBounds(0, 0, 1000, 50);
		table.setBounds(50, 100, 880, 450);
		enqueryMenu = new JPopupMenu();
		
		deleteRecord = new JMenuItem("Delete Record",Icons.smallDeleteIcon());
		updateRecord = new JMenuItem("Update Record");
		printPreview = new JMenuItem("Print Preview");
		enqueryMenu.add(deleteRecord);
		enqueryMenu.addSeparator();
		enqueryMenu.add(updateRecord);
		enqueryMenu.addSeparator();
		enqueryMenu.add(printPreview);
		
		exportMenu = new JPopupMenu();
		csv =  new JMenuItem("CSV");
		html= new JMenuItem("HTML");
		excel = new JMenuItem("Excel");
		pdf =  new JMenuItem("PDF");
		exportMenu .add(pdf);
		exportMenu .add(csv);
		exportMenu .add(html);
		exportMenu .add(excel);
		addNewEnquery = new JButton("New Enquery");
		export = new JButton("Export");
		
		EnqueryListenr el = new EnqueryListenr(this);
		addNewEnquery.addActionListener(el);
		deleteRecord.addActionListener(el);
		updateRecord.addActionListener(el);
		printPreview.addActionListener(el);
		export.addActionListener(el);
		pdf.addActionListener(el);
		excel.addActionListener(el);
		html.addActionListener(el);
		csv.addActionListener(el);
		table.addMouseListener(el);
		bar.add(addNewEnquery);
		bar.add(export);
		bar.setFloatable(false);
		this.add(bar);
		
		JScrollPane jScrollPane= new JScrollPane(table);
		jScrollPane.setBounds(50, 100, 880, 450);
		this.add(jScrollPane);
		this.setLayout(null);
		this.setSize(1000, 600);
		this.setLocation(200, 50);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ManageEnquery();
	}	
}
