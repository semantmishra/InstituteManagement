package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.LoginDao;
import utilis.MD5;
import beans.RegisterBean;
import beans.UserType;
import Form.Dashbord;
import Form.LoginUser;
import Form.login.ForgatePassword;
import Form.login.Login;
import Form.login.RegisterForm1;

public class LoginListenr implements ActionListener {
	Login login;
	public LoginListenr(Login login) {
		this.login = login;
	}


	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
		case "Login":
			login();
			break;
			
		case "Reset":
			reset();
			break;
			
		case "Forget":
			new ForgatePassword();
			login.dispose();
			break;
		}		
	}


	private void reset() {
		login.username.setText("");
		login.password.setText("");
		login.selectUser.setSelectedIndex(0);
	}


	private void login() {
		if(validation.RegisterFormValidate.login(login)) {
			RegisterBean bean = new RegisterBean();
			bean .setEmail(login.username.getText().trim());
			bean .setPassword(new MD5().getMd5(new MD5().getMd5(new MD5().getMd5(login.password.getText().trim()))));
			bean .setUserType(((UserType)login.selectUser.getSelectedItem()).getId());
			if( new LoginDao().login(bean))
			{
				LoginUser.setSession(bean);
				Dashbord dashbord =  new Dashbord(login);
				dashbord.setBean(bean);
				
				login.setVisible(false);
			}else
			{
				JOptionPane.showMessageDialog(null, "Login Faild");
			}
		}
				
	}
}
