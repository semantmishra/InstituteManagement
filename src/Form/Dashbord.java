package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagLayoutInfo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JToolBar;

import Form.employee.EmployeeForm;
import Form.enquery.ManageEnquery;
import Form.login.ChangePassword;
import Form.login.Login;
import Form.login.ManageUser;
import Form.student.FeePaymentForm;
import Form.student.StudentFrom;
import Listeners.DashbordListenr;
import beans.RegisterBean;
import beans.UserType;

public class Dashbord extends Template {
	public JButton enquery,student,feePayament,employee,manageuser ,changePass,logout;
	public JTable dataTable;
	public JLabel label;
	public JToolBar bar;
	private RegisterBean bean;
	public Dashbord(Login login)
	{
		super("Dashbord");
		
		initComponet(login);
		
		
	}
	private void initComponet(Login login) {
		bar = new JToolBar();
		bar.setFloatable(false);
//		this.login = login;
		label = new JLabel("Wellcome");
		dataTable = new JTable();
		student = new JButton("Manage Student");
		enquery = new JButton("Manage Enquery");
		employee= new JButton("Manage Employee");
		feePayament= new JButton("Fee Status");
		manageuser= new JButton("Manage User");
		changePass= new JButton("Change Password");
		logout = new JButton("Logout");
		bar.add(enquery);
		bar.add(employee);
		bar.add(student);
		bar.add(feePayament);
		bar.add(manageuser);
		bar.add(changePass);
		bar.add(logout);
		bar.setBounds(0, 0, 10000, 40);
		label.setBounds(500, 45, 300, 40);
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setForeground(Color.RED);
		
		this.add(bar);
		this.add(label);
		this.add(dataTable);
		this.setVisible(true);
		label.setText("Wellcome "+LoginUser.getSession().getName());
		//DashbordListenr dl =  new DashbordListenr(this,login);

		enquery.addActionListener(e ->new ManageEnquery() );
		student.addActionListener(e ->new StudentFrom() );
		feePayament.addActionListener(e ->new FeePaymentForm() );
		employee.addActionListener(e -> new EmployeeForm());
		manageuser.addActionListener(e -> new ManageUser());
		changePass.addActionListener(e -> new ChangePassword());
		logout.addActionListener(e -> {
			LoginUser.setSession(null);
			this.setVisible(false);
			login.setVisible(true);});	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public RegisterBean getBean() {
		return bean;
	}
	public void setBean(RegisterBean bean) {
		this.bean = bean;
		if(this.bean.getUserType()==UserType.ADMIN)
			this.manageuser.setVisible(false);
		
	}	
}