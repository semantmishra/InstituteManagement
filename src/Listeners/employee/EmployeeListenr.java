package Listeners.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Form.employee.AddNewEmployee;
import Form.employee.EmployeeForm;
import Form.enquery.NewEnquery;
import beans.Address;
import beans.Cities;
import beans.EmployeeBean;
import beans.State;
import dao.EmployeeDao;
import dao.EnqueryDao;
import dao.MyDao;
import utilis.Exports;
import utilis.Gender;
import utilis.Icons;

public class EmployeeListenr implements ActionListener,MouseListener {
	EmployeeForm employeeForm;
	public EmployeeListenr(EmployeeForm employeeForm) {
		this.employeeForm = employeeForm;
		loadDataDatabase();
	}

	private void loadDataDatabase() {
		List<EmployeeBean> beans = new EmployeeDao().getAllEmployees();
		loadJTable(beans );
	}
	
	private void loadJTable(List<EmployeeBean> beans) {
		DefaultTableModel model = new DefaultTableModel();
		employeeForm.table.setModel(model);
		model .addColumn("id");
		model .addColumn("name");
		model .addColumn("fatherName");
		model .addColumn("motherName");
		model .addColumn("gender");
		model .addColumn("email");
		model .addColumn("mobile1");
		model .addColumn("Address");
		for (EmployeeBean employeeBean : beans) {
			Object o[]= {employeeBean .getId(),
					employeeBean .getName(),
					employeeBean .getFatherName(),
					employeeBean .getMothername(),
					Gender.getGender(employeeBean .getGender()),
					employeeBean.getEmail(),
					employeeBean.getMobile1(),
					employeeBean.getAddress(),
					};
			model.addRow(o);
		}
		model=null;
		beans=null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Add New Employee":
			new AddNewEmployee(false,0);
			break;
		case "Delete Record":
		if(new MyDao().delete("dao.EmployeeDao", employeeForm.table,"delete from employees where id =?"))
			loadDataDatabase();
			break;
		case "Update Record":
			int row = employeeForm.table.getSelectedRow();
			int emp_id = Integer.parseInt(employeeForm.table.getValueAt(row, 0).toString());
			new AddNewEmployee(true,emp_id);
			break;
		case "Print Preview":
			System.out.println("Print Preview");
			break;
		case "Export":
			showExportMenu();
			break;
		case "PDF":
			new Exports().pdf(employeeForm.table);
			break;
		case "HTML":
			new Exports().csvOrHtml(employeeForm.table, Exports.HTML);
			break;
		case "Excel":
			new Exports().excel(this.employeeForm.table,"Employee Details");
			break;
		case "CSV":
			new Exports().csvOrHtml(employeeForm.table,Exports.CSV);
			break;
			
		}
		
	}

	private void showExportMenu() {
		employeeForm.exportMenu.show(employeeForm.export,employeeForm.export.HEIGHT-2, employeeForm.export.getWidth()-20);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==3 && employeeForm.table.getSelectedRowCount()>0 ) {		
			employeeForm.enqueryMenu.show(employeeForm.table, e.getX(), e.getY());
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {	}

}
