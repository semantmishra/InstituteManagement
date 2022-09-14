package Form.login;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import Listeners.LoginListenr;
import beans.UserType;
public class Login extends JFrame {

	public JButton login ,reset,newuser, pwd;
	public	JTextField username;
	public JPasswordField password;
	public JComboBox selectUser;
	public Button field = new Button("Test");
	
	JLabel lusername,lpassword,luser;
	private JButton forget;
	Login(){
		super("Login User");
	
			
		initComponent();
		
	}
	
	private void initComponent() {
		forget = new JButton("Forget");
		login = new JButton("Login");
		reset = new JButton("Reset");
		luser = new JLabel("Select User Type");
		
		selectUser = new JComboBox();
		selectUser .addItem("Select User Type");
		selectUser.addItem( new UserType(UserType.ADMIN, "Admin") );
		selectUser.addItem(new UserType(UserType.SUPER_ADMIN, "Super Admin"));
		
		username = new JTextField(20);
		password = new JPasswordField(20);
		lusername = new JLabel("Enter Email address");
		lpassword = new JLabel("Enter  Password");
		
		lusername .setBounds(20, 10, 150, 25);
		username .setBounds(220, 10, 200, 25);
		
		lpassword .setBounds(20, 38, 150, 25);
		password .setBounds(220, 40, 200, 25);
		
		luser.setBounds(20, 65, 100, 25);
		selectUser.setBounds(220, 70, 200, 25);
		
		login.setBounds(100, 110, 100, 25);
		reset.setBounds(201, 110 , 100, 25);
		forget.setBounds(302, 110 , 100, 25);
		
		this.add(lusername);
		this.add(username);
		this.add(lpassword);
		this.add(password);
		this.add(luser);
		this.add(selectUser);
		this.add(login);
		this.add(reset);
		this.add(forget);
		
		this.add(field);
		LoginListenr ll = new LoginListenr(this);
		
		login.addActionListener(ll);
		reset.addActionListener(ll);
		forget.addActionListener(ll);
		this.setSize(500, 300);
		this.setLocation(500, 100);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
		}
		new Login();

	}

}
