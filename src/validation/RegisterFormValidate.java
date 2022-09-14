package validation;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Form.login.ChangePassword;
import Form.login.Login;
import Form.login.RegisterForm1;
import Form.login.RegisterForm2;
import utilis.Message;

public class RegisterFormValidate extends Validation {

	public static boolean isValidate2(RegisterForm2 registerForm) {

		
		
		if(registerForm.userType.getSelectedIndex()==0){
			registerForm.lerror.setText("Error : Select User Type");
			registerForm.userType.requestFocus();
			return false;	
		}
		
		if(registerForm.securityQ.getSelectedIndex()==0){
			registerForm.lerror.setText("Error : Select security qustion");
			registerForm.securityQ.requestFocus();
			return false;	
		}
		if(isEmpy(registerForm.securityA.getText().trim())){
			registerForm.lerror.setText("Error : Enter security Ans");
			registerForm.securityA.requestFocus();
			return false;	
		}
		registerForm.lerror.setText("");
		return true;
	}
	public static boolean isValidate1(RegisterForm1 registerForm) {

		if(isEmpy(registerForm.name.getText().trim())){
			registerForm.lerror.setText("Error : Enter Your Name");
			registerForm.name.requestFocus();
			return false;	
		}
		if(registerForm.name.getText().trim().length()>50){
			registerForm.lerror.setText("Error : Enter name 49 letter");
			registerForm.name.requestFocus();
			return false;	
		}
		
		
		if(isEmpy(registerForm.email.getText().trim())){
			
			registerForm.lerror.setText("Error : Enter Email");
			registerForm.email.requestFocus();
			return false;	
		}
		if(registerForm.email.getText().trim().length()>50){
			
			registerForm.lerror.setText("Error : Enter Email 49 letter");
			registerForm.email.requestFocus();
			return false;
		}
		if(!isEmailCorrect(registerForm.email.getText().trim())){
			
			registerForm.lerror.setText("Error : Enter Correct Email");
			registerForm.email.requestFocus();
			return false;
		}
		
		if(isEmpy(registerForm.mobile.getText().trim())){
			
			registerForm.lerror.setText("Error : Enter Mobile");
			registerForm.mobile.requestFocus();
			return false;	
		}
		if(!isNumeric(registerForm.mobile.getText().trim())){
			
			registerForm.lerror.setText("Error : Enter Correct Mobile Number");
			registerForm.mobile.requestFocus();
			return false;	
		}
		if(registerForm.mobile.getText().trim().length()!=10){
			
			registerForm.lerror.setText("Error : Enter 10 Digit Mobile");
			registerForm.mobile.requestFocus();
			return false;
		}
		
		if(isEmpy(registerForm.password.getText().trim())){
			
			registerForm.lerror.setText("Error : Enter 10 Digit Mobile");
			registerForm.password.requestFocus();
			return false;	
		}
		if(registerForm.password.getText().trim().length()<6){
			registerForm.lerror.setText("Error : Enter your Password 6 digite");
			registerForm.password.requestFocus();
			return false;	
		}
				
		if(isExist(registerForm.mobile.getText().trim(),"mobile","user") ){
			
			registerForm.lerror.setText("Error : This mobile allready exist");
			registerForm.mobile.requestFocus();
			return false;	
		}
		if(isExist(registerForm.email.getText().trim(),"email","user") ){
			registerForm.lerror.setText("Error : This email allready exist");
			registerForm.email.requestFocus();
			return false;	
		}
		registerForm.lerror.setText("");
		return true;
	}

	
	
	
	public static boolean login(Login login) {
		if(isEmpy(login.username.getText())){
			JOptionPane.showMessageDialog(null, "Enter your username");
			login.username.requestFocus();
			return false;	
		}
		if(!isEmailCorrect(login.username.getText().trim())){
			JOptionPane.showMessageDialog(null, "Enter correct username");
			login.username.requestFocus();
			return false;	
		}
		if(isEmpy(login.password.getText().trim())){
			JOptionPane.showMessageDialog(null, "Enter your Password");
			login.password.requestFocus();
			return false;	
		}
		if(login.password.getText().trim().length()<6){
			JOptionPane.showMessageDialog(null, "Enter your Password 6 digite");
			login.password.requestFocus();
			return false;	
		}if(login.selectUser.getSelectedIndex()==0){
			Message.error(login,"Select User Type","Error");
			login.selectUser.requestFocus();
			return false;	
		}
		
		return true;
	}
	public static boolean ChangePassValidate(ChangePassword changePassword) {
		if(isEmpy(changePassword.curretPassword.getText().trim())){
			Message.error(changePassword,"Enter Current Password","Error");
			changePassword.curretPassword.requestFocus();
			return false;	
		}
		if(changePassword.newpass.getText().trim().isEmpty()){
			Message.error(changePassword,"Enter your New Password","Error");
			changePassword.newpass.requestFocus();
			return false;	
		}
		if(changePassword.newpass.getText().trim().length()<6){
			JOptionPane.showMessageDialog(null, "Enter your Password 6 digite");
			Message.error(changePassword,"Enter your Password 6 digite","Error");
			changePassword.newpass.requestFocus();
			return false;	
		}
		if(changePassword.cpass.getText().trim().isEmpty()){
			Message.error(changePassword,"Enter Confirm password","Error");
			changePassword.cpass.requestFocus();
			return false;	
		}
		
		if(!changePassword.newpass.getText().trim().equals(changePassword.cpass.getText().trim())){
			Message.error(changePassword,"New Password and Confirm Password Not Match","Error");
			changePassword.newpass.requestFocus();
			changePassword.newpass.setText("");
			changePassword.cpass.setText("");
			return false;	
		}
		if(changePassword.curretPassword.getText().trim().equals(changePassword.cpass.getText().trim())){
			Message.error(changePassword,"Old Password and New Password Same","Error");
			changePassword.newpass.requestFocus();
			changePassword.newpass.setText("");
			changePassword.cpass.setText("");
			return false;	
		}
		return true;
	}
	

}
