package Listeners.enquery;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Form.enquery.ManageEnquery;
import Form.enquery.NewEnquery;
import beans.EnqueryBean;
import dao.EnqueryDao;
import dao.MyDao;
import utilis.Exports;
import utilis.Icons;

public class EnqueryListenr implements ActionListener, MouseListener {
	ManageEnquery enquery;
	public EnqueryListenr( ManageEnquery enquery) {
		this.enquery =enquery;
		loadDataIntoDatabase();
	}
	
	private void loadDataIntoDatabase() {
		loadDataIntoJTable();
	}

	private void loadDataIntoJTable() {

		DefaultTableModel model = (DefaultTableModel)enquery.table.getModel();
		List<EnqueryBean> enquery = getEnquery();
		Vector<String> culomn = new Vector<String>();
		culomn.add("Id");
		culomn.add("Name");
		culomn.add("F Name");
		culomn.add("DOE");
		culomn.add("Course");
		culomn.add("Mobile");
		culomn.add("Email");
		culomn.add("Address");
		model.setColumnIdentifiers(culomn);

	for (EnqueryBean enqueryBean : enquery) {
		
		Object o[] = new Object[8];
		o[0] = enqueryBean .getId();
		o[1] = enqueryBean .getName();
		o[2] = enqueryBean .getFatherName();
		o[3] = enqueryBean .getDOB();
		o[4] = enqueryBean .getCourse().getName();
		o[5] = enqueryBean .getMobile1();
		o[6] = enqueryBean .getEmail();
		o[7] = enqueryBean .getAddress().getAdderss();
		model.addRow(o);
	}	
		
		
	}
	
	private List<EnqueryBean> getEnquery() {
		
		return new EnqueryDao().getEnquery() ;
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "New Enquery":
		new NewEnquery(false);
			break;
		case "Delete Record":
			new MyDao().delete("dao.EnqueryDao",enquery.table,"");
			break;
		case "Update Record":
			new NewEnquery(true);
			break;
		case "Print Preview":
			System.out.println("Print Preview");
			break;
		case "Export":
			showExportMenu();
			break;
		case "PDF":
			new Exports().pdf(enquery.table);
			break;
		case "HTML":
			new Exports().csvOrHtml(enquery.table, Exports.HTML);
			break;
		case "Excel":
			new Exports().excel(enquery.table,"Student Details");
			break;
		case "CSV":
			new Exports().csvOrHtml(enquery.table,Exports.CSV);
			break;
			
		}
	}
	
	private void showExportMenu() {
		enquery.exportMenu.show(enquery.export,enquery.export.HEIGHT-2, enquery.export.getWidth()-20);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==3 && enquery.table.getSelectedRowCount()>0 ) {		
			enquery.enqueryMenu.show(enquery.table, e.getX(), e.getY());
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {	}
	
}
