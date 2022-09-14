package Form.employee;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import Form.Template;
import Listeners.employee.EmployeeListenr;
import utilis.Icons;

public class EmployeeForm extends Template {
	
		public JPopupMenu enqueryMenu,exportMenu;
		public JButton addNewEmployee,export;
		public JToolBar bar;
		public JTable table;
		public JMenuItem deleteRecord,updateRecord,printPreview,pdf,csv,html,excel;
		
		public EmployeeForm(){
			super("Manage Employee");
			bar = new JToolBar();
			table = utilis.Component.getJtable();

			bar.setBounds(0, 0, 1000, 50);
			addNewEmployee = new JButton("Add New Employee");
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
			
			export = new JButton("Export");
			
			EmployeeListenr sl =new EmployeeListenr(this) ;
			export .addActionListener(sl);
			deleteRecord.addActionListener(sl );
			updateRecord.addActionListener(sl );
			printPreview.addActionListener(sl );
			export.addActionListener(sl );
			pdf.addActionListener(sl );
			excel.addActionListener(sl );
			html.addActionListener(sl );
			csv.addActionListener(sl );
			table.addMouseListener(sl);
			addNewEmployee.addActionListener(sl);
			bar.add(addNewEmployee);
			bar.add(export);
			bar.setFloatable(false);
			JScrollPane pane = new JScrollPane(table);
			pane.setBounds(50, 100, 880, 450);
			this.add(bar);
			this.add( pane);
			this.setVisible(true);
			
		}
		public static void main(String[] args) {
			new EmployeeForm();
		}



}
