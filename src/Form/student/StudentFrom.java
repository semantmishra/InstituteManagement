package Form.student;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Listeners.enquery.EnqueryListenr;
import Listeners.student.StudentListenr;
import utilis.Icons;

public class StudentFrom extends JFrame {
	public JButton addNewStudent,export,search;
	public JPopupMenu studentMenu,exportMenu;
	//public JLabel lsearchById,lsearchByName,lsearchBycourse,lsearchSession;
    public JComboBox session;
    public JLabel nameOrIdCombo;
	public JTextField nameOrId;
	public JToolBar bar;
	public JTable table;
	public JMenuItem deleteRecord,updateRecord,view,pdf,csv,html,excel,feePay;
	
	public StudentFrom(){
		super("Manage Student");
		intiComponent();
		
	}
	private void intiComponent() {
		bar = new JToolBar();
		search = new JButton("Search");
		nameOrIdCombo = new JLabel("Enter Roll No.");
		
		session = new JComboBox();
		session.setActionCommand("session");
		
		nameOrId = new JTextField();
		
		nameOrIdCombo.setBounds(40, 55, 120, 25);
		nameOrId.setBounds(170, 55, 200, 25);
		
		session.setBounds(585, 55, 200, 25);
		search.setBounds(787, 55, 100, 25);
		
		table =utilis.Component.getJtable();
		bar.setBounds(0, 0, 1000, 50);
		addNewStudent = new JButton("Add Student");
		table.setBounds(50, 100, 880, 450);
		studentMenu = new JPopupMenu();
		deleteRecord = new JMenuItem("Delete Record",Icons.smallDeleteIcon());
		updateRecord = new JMenuItem("Update Record");
		feePay = new JMenuItem("Fee Payment");
		view = new JMenuItem("View Record");
		studentMenu.add(deleteRecord);
		studentMenu.addSeparator();
		studentMenu.add(updateRecord);
		studentMenu.addSeparator();
		studentMenu.add(view);
		studentMenu.addSeparator();
		studentMenu.add(feePay);
		
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
		
		StudentListenr sl =new StudentListenr(this) ;
		nameOrId.addKeyListener(sl);
		search.addActionListener(sl);
		
		session.addActionListener(sl);
		addNewStudent.addActionListener(sl);
		export .addActionListener(sl);
		deleteRecord.addActionListener(sl );
		updateRecord.addActionListener(sl );
		view.addActionListener(sl );
		feePay.addActionListener(sl);
		export.addActionListener(sl );
		pdf.addActionListener(sl);
		excel.addActionListener(sl);
		html.addActionListener(sl );
		csv.addActionListener(sl );
		table.addMouseListener(sl);
		
		bar.add(addNewStudent);
		bar.add(export);
		bar.setFloatable(false);
	
		this.add(bar);
		this.add(nameOrIdCombo);
		this.add(nameOrId);
		this.add(session);
		this.add(search);
		this.add(table);
		JScrollPane p=	new JScrollPane(table);
		
		p.setBounds(40, 90, 900, 300);
		
		this.add(p);
		this.setLayout(null);	
		this.setSize(1000, 500);
		this.setLocation(200, 50);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new StudentFrom();
	}

}
