package validation;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Form.employee.AddNewEmployee;
import utilis.Icons;
import utilis.Message;

public class AddEmployeeValidation {

	public static boolean isValidate(AddNewEmployee addNewEmployee) {		
		if(addNewEmployee.name.getText().trim().equals("") ) {
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Name", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon(Icons.deleteIcon));
			addNewEmployee.name.requestFocus();
			return false;
		}
		if(addNewEmployee.fname .getText().trim().equals("") ) {
			addNewEmployee.fname.requestFocus();
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Father Name", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon(Icons.deleteIcon));
			return false;
		}
		
		if(addNewEmployee.mname.getText().trim().equals("") ) {
			addNewEmployee.mname.requestFocus();
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Mother Name", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon(Icons.deleteIcon));
			return false;
		}
		if(!addNewEmployee.male.isSelected() && !addNewEmployee.female.isSelected()) {
			JOptionPane.showMessageDialog(addNewEmployee, "Select Gender", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon(Icons.deleteIcon));
			return false;
		}
		if(addNewEmployee.mobile1.getText().trim().equals("") ) {
			addNewEmployee.mobile1.requestFocus();
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Mobile 1", "Error", JOptionPane.ERROR_MESSAGE,new ImageIcon(Icons.deleteIcon));
			return false;
		}
		if(addNewEmployee.mobile1.getText().trim().length()!=10) {
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Mobile 10 digit","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.mobile1.requestFocus();
			return false;
		}
		if(!addNewEmployee.mobile2.getText().trim().equals("") ) {
			if(addNewEmployee.mobile2.getText().trim().length()!=10) {
				JOptionPane.showMessageDialog(addNewEmployee, "Enter Mobile2 10 digit","Error",JOptionPane.ERROR_MESSAGE);
				addNewEmployee.mobile2.requestFocus();
				return false;
			}
		}
		if(addNewEmployee.email.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Email Address","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.email.requestFocus();
			return false;
		}
		if(!Validation.isEmailCorrect(addNewEmployee.email.getText().trim())) {
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Correct Email","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.email.requestFocus();
			return false;
		}
		
		if(addNewEmployee.DOB.getDate()==null) {
			JOptionPane.showMessageDialog(addNewEmployee, "Select DOB","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.DOB.requestFocus();
			return false;
		}
		if(addNewEmployee.DOJ.getDate()==null) {
			JOptionPane.showMessageDialog(addNewEmployee, "Select DOA","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.DOJ.requestFocus();
			return false;
		}
		
		if(addNewEmployee.address.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(addNewEmployee, "Enter Address","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.address.requestFocus();
			return false;
		}
		if(addNewEmployee.distric.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(addNewEmployee, "Select Distric","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.distric.requestFocus();
			return false;
		}
		if(addNewEmployee.state.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(addNewEmployee, "Enter State","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.state.requestFocus();
			return false;
		}
		if(Validation.isExist(addNewEmployee.mobile1.getText().trim(),"mobile1","employees" )) {
			JOptionPane.showMessageDialog(addNewEmployee, "Mobile1 Exist","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.mobile1.setText("");
			addNewEmployee.mobile1.requestFocus();
			return false;
		}
		
		if(!addNewEmployee.mobile2.getText().trim().equals("") ) {
			if(Validation.isExist(addNewEmployee.mobile2.getText().trim(),"mobile",addNewEmployee.mobile2.getText().trim())) {
				JOptionPane.showMessageDialog(addNewEmployee, "mobile2 Exist","Error",JOptionPane.ERROR_MESSAGE);
				addNewEmployee.mobile2.setText("");
				addNewEmployee.mobile2.requestFocus();
				return false;
			}
		}
		if(Validation.isExist(addNewEmployee.email.getText().trim(),"email","employees")) {
			JOptionPane.showMessageDialog(addNewEmployee, "Email Address Exist","Error",JOptionPane.ERROR_MESSAGE);
			addNewEmployee.email.setText("");
			addNewEmployee.email.requestFocus();
			return false;
		}
		
		return true;
	}


}
