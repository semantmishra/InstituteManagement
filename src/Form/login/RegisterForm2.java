package Form.login;

import java.awt.Color;
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

public class RegisterForm2 extends JFrame {
	public JButton register;
	public JTextField securityA ;
	public JComboBox userType,securityQ;
	public JCheckBox isActive;
	JLabel luserType,lsecurityQ,lsecuritya;
	public JLabel lerror;
	public String email;
	public RegisterForm2(String email){
	super("Register New User");
	this.email = email;
	register = new JButton("Register");
	
	userType = new JComboBox();
	userType .addItem("Select User Type");
	userType.addItem( new UserType(UserType.ADMIN, "Admin") );
	userType.addItem(new UserType(UserType.SUPER_ADMIN, "Super Admin"));
	securityQ = new JComboBox();
	securityQ.addItem("Select Qustion");
	securityA = new JTextField();
	lerror = new JLabel("");
	isActive = new JCheckBox("isActive");
	lerror.setForeground(Color.RED);
	
	luserType= new JLabel("Select User Type");
	lsecurityQ = new JLabel("Select Security Qustion");
	lsecuritya= new JLabel("Enter Security Answer ");
	lerror.setBounds(200, 48, 300, 20);
	lerror.setFont(new Font(Font.SANS_SERIF,Font.BOLD ,13));
	
	
	luserType.setBounds(40, 80, 100, 20);
	userType.setBounds(200, 80, 200, 20);
	isActive.setBounds(410, 80, 200, 20);
	
	lsecurityQ.setBounds(40, 110, 150, 20);
	securityQ.setBounds(200, 110, 200, 20);
	
	lsecuritya.setBounds(40, 140, 150, 20);
	securityA.setBounds(200, 140, 200, 20);
	register.setBounds(200, 170, 100, 30);
	this.add(lerror);
	
	this.add(luserType);
	this.add(userType);
	this.add(isActive);
	this.add(lsecurityQ);
	this.add(securityQ);
	this.add(lsecuritya);
	this.add(securityA);
	this.add(register);
	RegisterListenr rl = new RegisterListenr(this);
	register.addActionListener(rl);
	this.setSize(550, 400);
	this.setLocation(500, 100);
	this.setLayout(null);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	}

}
