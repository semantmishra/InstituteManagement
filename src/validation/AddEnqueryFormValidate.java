package validation;

import javax.swing.JOptionPane;

import Form.enquery.NewEnquery;

public class AddEnqueryFormValidate {

	public static boolean Validate(NewEnquery newEnquery) {

		if (newEnquery.name.getText().trim().equals("")) {
			newEnquery.name.requestFocus();
			JOptionPane.showMessageDialog(null, "Please enter name");
			return false;
		}
		if (newEnquery.fatherName.getText().trim().equals("")) {
			newEnquery.fatherName.requestFocus();
			JOptionPane.showMessageDialog(null, "Please enter father name");
			return false;
		}
//		if (newEnquery.CourseOfEnq.getSelectedItem().equals("Select Course")) {
//			newEnquery.CourseOfEnq.requestFocus();
//			JOptionPane.showMessageDialog(null, "Please select course");
//			return false;
//		}
		if (newEnquery.mobile1.getText().trim().equals("")) {
			newEnquery.mobile1.requestFocus();
			JOptionPane.showMessageDialog(null, "Please enter Mobile 1");
			return false;
		}

		if (newEnquery.address.getText().trim().equals("")) {
			newEnquery.address.requestFocus();
			JOptionPane.showMessageDialog(null, "Please enter address");
			return false;
		}
		if (!newEnquery.pincode.getText().trim().equals("")) {
			if (newEnquery.pincode.getText().length() > 6) {
				JOptionPane.showMessageDialog(null, "Please enter pincode 6 digit");
				return false;
			}
		}

		return true;
	}

}
