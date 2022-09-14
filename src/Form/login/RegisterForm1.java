package Form.login;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Listeners.RegisterListenr;
import beans.UserType;

public class RegisterForm1 extends JFrame{
	public JButton login ,reset;
	public JTextField name,email,mobile ;

	public JPasswordField password;
	JLabel lname,lemail,lmobile,lgender,lpassword;
	
	public JLabel lerror;
	public RegisterForm1(){
	super("Register New User");
	login = new JButton("Next");
	reset = new JButton("Reset");
	
	
	name= new JTextField(20);
	email = new JTextField(20);
	password = new JPasswordField(20);
	mobile = new JTextField(20);
	
	lerror = new JLabel("");
	
	lerror.setForeground(Color.RED);
	lname = new JLabel("Enter Name");
	lemail = new JLabel("Enter Email");
	lpassword = new JLabel("Enter  Password");
	lmobile = new JLabel("Enter  Mobile No.");
	lpassword= new JLabel("Enter Password");
	lerror.setBounds(200, 48, 300, 20);
	lerror.setFont(new Font(Font.SANS_SERIF,Font.BOLD ,13));
	lname.setBounds(40, 70, 100, 20);
	name.setBounds(200, 70, 200, 20);
	
	lemail.setBounds(40, 130-30, 100, 20);
	email.setBounds(200, 130-30, 200, 20);
	
	lmobile.setBounds(40, 160-30, 100, 20);
	mobile.setBounds(200, 160-30, 200, 20);
	
	lpassword.setBounds(40, 190-30, 100, 20);
	password.setBounds(200, 190-30, 200, 20);
	
	login.setBounds(200, 220-30, 100, 30);
	reset.setBounds(300, 220-30, 100, 30);
	this.add(lerror);
	this.add(lname);
	this.add(name);

	this.add(lmobile);
	this.add(mobile);
	this.add(lemail);
	this.add(email);
	this.add(lpassword);
	this.add(password);
	
	this.add(login);
	this.add(reset);
	RegisterListenr rl = new RegisterListenr(this);
	login.addActionListener(rl);
	reset.addActionListener(rl);
	
	this.setSize(550, 400);
	this.setLocation(500, 100);
	this.setLayout(null);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	}
	public static void main(String[] args) {
		new RegisterForm1();
	}
}
