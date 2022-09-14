package Form.login;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import beans.RegisterBean;
import dao.RegisterDao;
import utilis.Icons;
import utilis.MD5;
import utilis.Message;

public class ForgatePassword extends JFrame {
	private JLabel lemail;
	private JLabel lsq;
	private JLabel lsa;
	private JLabel sq;
	private JLabel lpassword;
	private JLabel lcpassword;
	private JTextField email;
	private JTextField password;
	private JTextField cpassword;
	private JTextField sa;
	private JButton search;
	private JButton createPass;

	public	ForgatePassword (){
		intiComponent();
		event();
	}

	private void event() {
		search.addActionListener(e -> {
			if(isValidate()) {
				if(getUserByEmail())
					showComponent(true);
				else
					Message.error(this, "Email does not exist", "Error");
			}
		});
		// end search function 
		
		createPass.addActionListener(e -> {
			if(isValidateForgate()) {
				RegisterBean bean= new RegisterBean();
				bean.setEmail(email.getText().trim() );
				bean.setSecurityA(sa.getText().trim());
				bean.setPassword(new MD5().getMd5(new MD5().getMd5(new MD5().getMd5(password.getText().trim()))));
				if( new RegisterDao().resetPassword(bean) ) {
					Message.success(this, "Password Reset Success", "Success");
					this.dispose();
					new Login();
				}else {
					
					Message.error(this, "Password Reset faild", "Error");
				}
				
			}
		});
	}

	private boolean isValidateForgate() {
		if(validation.Validation.isEmpy(sa.getText().trim())) {
			Message.error(this, "Enter Security Ans", "Error");
			sa.requestFocus();
			return false;
		}
		if(validation.Validation.isEmpy(password.getText().trim())) {
			Message.error(this, "Enter Password", "Error");
			password.requestFocus();
			return false;
		}
		if(validation.Validation.isEmpy(cpassword.getText().trim())) {
			cpassword.requestFocus();
			return false;
		}
		if(password.getText().trim().length()<6){
			Message.error(this,"Error : Enter your Password 6 digite","Error");
			password.requestFocus();
			return false;	
		}
		if(!password.getText().trim().equals(cpassword.getText().trim()))
		{
			Message.error(this, "Password does not Match", "Error");
			password.setText("");
			cpassword.setText("");
			password.requestFocus();
			return false;
		}
		return true;
	}

	private boolean getUserByEmail() {
		RegisterBean bean = new RegisterDao().getUseryEmail(this.email.getText().trim());
		if(bean!=null) {
			sq.setText(bean.getSecurityQustion().getQustion());
			return true;
		}
		else
			return false;
	}

	private boolean isValidate() {
		if(validation.Validation.isEmpy(email.getText().trim())) {
			Message.error(this,"Enter Email	","Error");
			email.requestFocus();
			return false;
		}
		if(!validation.Validation.isEmailCorrect(email.getText().trim())) {
			Message.error(this,"Enter Correct Email	","Error");
			email.requestFocus();
			return false;
		}
		return true;
	}

	private void intiComponent() {
		
		search = new JButton(Icons.getImage(Icons.search));
		createPass = new JButton("Create Password");
		
		lemail= new JLabel("Enter Email");
		lsq= new JLabel("Security Qustion");
		sq= new JLabel("And");
		lsa= new JLabel("Enter Security Ans");
		lpassword = new JLabel("Create Password");
		lcpassword = new JLabel("Confirm Password");
		
		email = new JTextField();
		password = new JTextField();
		cpassword = new JTextField();
		sa = new JTextField();
		lemail.setBounds(100, 70, 200, 30);
		email.setBounds(300, 70, 200, 30);
		search.setBounds(505, 70, 20, 30);
		
		lsq.setBounds(100, 100, 200, 30);
		sq.setBounds(300, 100, 200, 30);
		
		lsa.setBounds(100, 130, 200, 30);
		sa.setBounds(300, 130, 200, 30);
		
		lpassword.setBounds(100, 160, 200, 30);
		password.setBounds(300, 160, 200, 30);
		
		lcpassword.setBounds(100, 190, 200, 30);
		cpassword.setBounds(300, 190, 200, 30);
		createPass.setBounds(300, 220, 200, 30);
		
		showComponent(false );
		
		this.add(lemail);
		this.add(email);
		this.add(search);
		this.add(lsq);
		this.add(sq);
		this.add(sa);
		this.add(lsa);
		this.add(lpassword);
		this.add(password);
		this.add(lcpassword);
		this.add(cpassword);
		this.add(createPass);
		this.setLayout(null);
		this.setSize(700, 500);
		this.setVisible(true);
		this.setLocation(400, 100);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	private void showComponent(boolean b) {
		lsq.setVisible(b);
		sq.setVisible(b);
		sa.setVisible(b);
		lsa.setVisible(b);
		lpassword.setVisible(b);
		password.setVisible(b);
		lcpassword.setVisible(b);
		cpassword.setVisible(b);
		createPass.setVisible(b);
		email.enable(!b);
		search.setVisible(!b);
	}

	public static void main(String[] args) {
		new ForgatePassword();
	}

}
