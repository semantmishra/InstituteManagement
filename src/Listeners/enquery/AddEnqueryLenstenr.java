package Listeners.enquery;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JDateChooser;

import Form.enquery.NewEnquery;
import beans.Address;
import beans.Cities;
import beans.Course;
import beans.EnqueryBean;
import beans.SessionBean;
import beans.State;
import dao.EnqueryDao;
import utilis.Gender;
import utilis.LoadData;
import validation.AddEnqueryFormValidate;

public class AddEnqueryLenstenr implements ActionListener {
	NewEnquery newEnquery;
	public AddEnqueryLenstenr(NewEnquery newEnquery) {
		this.newEnquery = newEnquery;
		loadDataIntoComBoBox();
}
	void loadDataIntoComBoBox(){
		LoadData data= new LoadData();
		data.courseComboBox(newEnquery.CourseOfEnq);
		data.sessionComboBox(newEnquery.session);
		data.stateComboBox(newEnquery.state);
	}

	public void actionPerformed(ActionEvent e) {
		if( e.getSource()==newEnquery.save ) {
			saveButtonClick();
		}else if( e.getSource()==newEnquery.cancel) {
			cancelButtonClick();
		}else if( e.getSource()==newEnquery.state) {
			new LoadData().citiesComboBox(newEnquery.distric,((State)newEnquery.state.getSelectedItem()).getId());
		}
		
	}

	private void cancelButtonClick() {	
		System.out.println("Cancel");
	}
	
	private void saveButtonClick() {
		if( AddEnqueryFormValidate.Validate(this.newEnquery)) {
		
			EnqueryBean bean = new EnqueryBean();
			bean.setName(newEnquery.name.getText().trim());
			bean.setFatherName(newEnquery.fatherName.getText().trim());
			
			bean.setCourse((Course)newEnquery.CourseOfEnq.getSelectedItem());
			
			bean.setMobile1(newEnquery.mobile1.getText().trim());
			bean.setMobile2(newEnquery.mobile2.getText().trim());
			bean.setEmail(newEnquery.email.getText().trim());
			bean.setDOB( new Date(newEnquery.DateOfEnq.getDate().getTime() ) );
			bean.setSession((SessionBean) newEnquery.session.getSelectedItem() );
			bean.setGender(newEnquery.male.isSelected()?Gender.MALE:Gender.FEMALE);
			
			Address address = new Address("add", "pin", (State)newEnquery.state.getSelectedItem(),(Cities)newEnquery.distric.getSelectedItem());

			bean.setAddress(address);
			
			if(new EnqueryDao().save(bean))
			{
				JOptionPane.showMessageDialog(null, "Save success", "Enquery", JOptionPane.INFORMATION_MESSAGE);
				newEnquery.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Faild", "Enquery", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	private int getGender() {
		if(newEnquery.male.isSelected()) {
			return Gender.MALE;
		}
		return Gender.FEMALE;
	}



}
