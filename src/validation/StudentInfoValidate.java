package validation;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Form.student.StudentInfo;
import utilis.Icons;

public class StudentInfoValidate extends Validation {

	public static boolean isValidate(StudentInfo info) {

		if (info.name.getText().trim().equals("")) {
			info.name.requestFocus();
			JOptionPane.showMessageDialog(info, "Enter Name", "Error", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(Icons.deleteIcon));
			return false;
		}
		if (info.fname.getText().trim().equals("")) {
			info.fname.requestFocus();
			JOptionPane.showMessageDialog(info, "Enter Father Name", "Error", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(Icons.deleteIcon));
			return false;
		}

		if (info.mname.getText().trim().equals("")) {
			info.mname.requestFocus();
			JOptionPane.showMessageDialog(info, "Enter Mother Name", "Error", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(Icons.deleteIcon));
			return false;
		}
		if (!info.male.isSelected() && !info.female.isSelected()) {
			JOptionPane.showMessageDialog(info, "Select Gender", "Error", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(Icons.deleteIcon));
			return false;
		}
		if (info.mobile1.getText().trim().equals("")) {
			info.mobile1.requestFocus();
			JOptionPane.showMessageDialog(info, "Enter Mobile 1", "Error", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(Icons.deleteIcon));
			return false;
		}
		if (info.mobile1.getText().trim().length() != 10) {
			JOptionPane.showMessageDialog(info, "Enter Mobile 10 digit", "Error", JOptionPane.ERROR_MESSAGE);
			info.mobile1.requestFocus();
			return false;
		}
		if(!info.isUpdate) {
			if ( isExist(info.mobile1.getText().trim(), "mobile1", "students")) {
				JOptionPane.showMessageDialog(info, "Mobile1 Exist", "Error", JOptionPane.ERROR_MESSAGE);
				info.mobile1.requestFocus();
				return false;
			}	
		}
		
		
		
		if (!info.mobile2.getText().trim().equals("")) {
			if (info.mobile2.getText().trim().length() != 10) {
				JOptionPane.showMessageDialog(info, "Enter Mobile2 10 digit", "Error", JOptionPane.ERROR_MESSAGE);
				info.mobile2.requestFocus();
				return false;
			}
		}
		if (info.email.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(info, "Enter Email Address", "Error", JOptionPane.ERROR_MESSAGE);
			info.email.requestFocus();
			return false;
		}
		if (!isEmailCorrect(info.email.getText().trim())) {
			JOptionPane.showMessageDialog(info, "Enter Correct Email Address", "Error", JOptionPane.ERROR_MESSAGE);
			info.email.requestFocus();
			return false;
		}
		
		if(!info.isUpdate) {
			if ( isExist(info.email.getText().trim(), "email", "students")) {
				JOptionPane.showMessageDialog(info, "Email Exist", "Error", JOptionPane.ERROR_MESSAGE);
				info.mobile1.requestFocus();
				return false;
			}
		}
		if (info.course.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(info, "Select Class", "Error", JOptionPane.ERROR_MESSAGE);
			info.course.requestFocus();
			return false;
		}
		if (info.DOB.getDate() == null) {
			JOptionPane.showMessageDialog(info, "Select DOB", "Error", JOptionPane.ERROR_MESSAGE);
			info.DOB.requestFocus();
			return false;
		}
		if (info.DOA.getDate() == null) {
			JOptionPane.showMessageDialog(info, "Select DOA", "Error", JOptionPane.ERROR_MESSAGE);
			info.DOA.requestFocus();
			return false;
		}
		if (info.session.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(info, "Select Session", "Error", JOptionPane.ERROR_MESSAGE);
			info.session.requestFocus();
			return false;
		}
		if (info.address.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(info, "Enter Address", "Error", JOptionPane.ERROR_MESSAGE);
			info.address.requestFocus();
			return false;
		}
		if (info.distric.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(info, "Select Distric", "Error", JOptionPane.ERROR_MESSAGE);
			info.distric.requestFocus();
			return false;
		}
		if (info.state.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(info, "Enter State", "Error", JOptionPane.ERROR_MESSAGE);
			info.state.requestFocus();
			return false;
		}

		return true;
	}

}
