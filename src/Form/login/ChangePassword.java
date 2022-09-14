package Form.login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import Form.LoginUser;
import Form.Template;
import dao.RegisterDao;
import utilis.MD5;
import utilis.Message;
import validation.RegisterFormValidate;

public class ChangePassword extends Template {
	JLabel lcurretPassword,lnewpass,lcpass;
	public JPasswordField curretPassword,newpass,cpass;
	JButton chanagePass;
	
	
	public ChangePassword() {
		super("Change Password");
		intiComponent();
		event();
	}
	
	private void event() {
		chanagePass.addActionListener(e -> {
			
			if(RegisterFormValidate.ChangePassValidate(this) ) {
				String [] emailPassword = {
						LoginUser.getSession().getEmail(),
						new MD5().getMd5(new MD5().getMd5(new MD5().getMd5(this.curretPassword.getText().trim()))),
						new MD5().getMd5(new MD5().getMd5(new MD5().getMd5(this.newpass.getText().trim())))
						};
				if(new RegisterDao().changePassword(emailPassword))
				{
					Message.success(this, "Password change success", "Change Password");
					this.dispose();	
				}else 
					Message.error(this, "Password change unsuccess", "Change Password");
			}
		});
	}
	
	private void intiComponent() {
	lcurretPassword= new JLabel("Enter Current Password");
	lnewpass=new JLabel("Enter New Password");
	lcpass=new JLabel("Confirm Password");
	
	curretPassword= new JPasswordField();
	newpass= new JPasswordField();
	cpass= new JPasswordField();
	chanagePass = new JButton("Change Password");
	
	lcurretPassword.setBounds(50, 20, 200, 30);
	curretPassword.setBounds(250, 20, 200, 30);
	
	lnewpass.setBounds(50, 60, 200, 30);
	newpass.setBounds(250, 60, 200, 30);
	
	lcpass.setBounds(50, 100, 200, 30);
	cpass.setBounds(250, 100, 200, 30);
	chanagePass.setBounds(250, 140, 200, 30);
	this.add(lcurretPassword);
	this.add(curretPassword);
	this.add(lnewpass);
	this.add(newpass);
	this.add(lcpass);
	this.add(cpass);
	this.add(chanagePass);
	this.setSize(500, 280);
	this.setLocation(500, 200);
	}

	public static void main(String[] args) {
	
	new ChangePassword();
}
}
