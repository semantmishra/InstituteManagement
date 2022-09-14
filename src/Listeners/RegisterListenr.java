package Listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import Form.login.RegisterForm1;
import Form.login.RegisterForm2;
import dao.LoadDataDao;
import dao.RegisterDao;
import utilis.Gender;
import utilis.MD5;
import utilis.Message;
import validation.RegisterFormValidate;
import validation.Validation;
import beans.RegisterBean;
import beans.SecurityQustion;
import beans.UserType;

public class RegisterListenr implements ActionListener {
	RegisterForm1 registerForm;
	RegisterForm2 registerForm2;
	public RegisterListenr(RegisterForm1 registerForm) {
		this.registerForm = registerForm;
	}
	public RegisterListenr(RegisterForm2 registerForm2) {
		this.registerForm2 = registerForm2;
		new LoadDataDao().securityQustionComboBox().forEach(a->registerForm2 .securityQ.addItem(a));
	}
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "Register":
			register();
			break;
		case "Next":
			next();
			break;
		case "Reset":
			registerForm.email.setText("");
			registerForm.password.setText("");
			registerForm.mobile.setText("");
			break;
		}
		
	}
	private void register() {
		if(RegisterFormValidate.isValidate2(registerForm2)) {
			RegisterBean rb = new RegisterBean();			
			rb .setUserType(((UserType)registerForm2.userType.getSelectedItem()).getId());
			rb .setIsAction(registerForm2.isActive.isSelected()?1:0 );
			rb .setSecurityQustion((SecurityQustion)registerForm2.securityQ.getSelectedItem());
			rb .setName(registerForm2.securityA.getText().trim());
			rb.setEmail(registerForm2.email);
			
			if(new RegisterDao().register2(rb))
				{Message.success(registerForm, "Register Success full", "Success");
				registerForm2.dispose();
				}
			else
				Message.error(registerForm, "Register Faild try again", "Error");
		}

	}
	private void next() {
		if(RegisterFormValidate.isValidate1(registerForm)) {
			RegisterBean rb = new RegisterBean();
			rb .setName(registerForm.name.getText().trim());
			
			rb .setEmail(registerForm.email.getText().trim());
			rb .setMobile(registerForm.mobile.getText().trim());
			rb .setPassword(new MD5().getMd5(new MD5().getMd5(new MD5().getMd5(registerForm.password.getText().trim()))));
			
			if(new RegisterDao().register1(rb))
				{
				new RegisterForm2(rb.getEmail());
				registerForm.dispose();
				
				}
			else
				Message.error(registerForm, "Register Faild try again", "Error");
		}

	}
}
