package Form.enquery;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import Form.Template;
import Listeners.enquery.AddEnqueryLenstenr;

public class NewEnquery extends Template {
	
	public JButton save,cancel;
	public JLabel lname,lfatherName,gender,lDateOfEnq,lCourseOfEnq,lmobile1,lmobile2,lemail,lstate,ldistric,lpincode,laddress,lsession;
	public JTextField name,fatherName,mobile1,mobile2,email,pincode,address;
	public JComboBox CourseOfEnq,state,distric,session;
	public JRadioButton male,female;
	public ButtonGroup buttonGroup;
	public JDateChooser DateOfEnq;
	public boolean isUpdate;
	public NewEnquery(boolean isUpdate){
		super("New Enquery");
		this.isUpdate = isUpdate;
		
		createControlandShowForm();
		
		AddEnqueryLenstenr enqueryListenr =new AddEnqueryLenstenr(this);
		
		save.addActionListener(enqueryListenr);
		state.addActionListener(enqueryListenr);
		
		cancel.addActionListener(enqueryListenr);
	}
	private void createControlandShowForm() {
		lname = new JLabel("Enter Name");
		lfatherName= new JLabel("Enter Father Name");
		lDateOfEnq= new JLabel("Enter Date Of Birth");
		lCourseOfEnq= new JLabel("Select Course");
		lsession= new JLabel("Select Session");
		lmobile1= new JLabel("Enter Mobile number");
		lmobile2= new JLabel("Enter Mobile number");
		lemail= new JLabel("Enter Email");
		ldistric= new JLabel("Select Distric");
		lstate= new JLabel("Select State");
		lpincode= new JLabel("Enter Pincode");
		laddress= new JLabel("Enter address");
		gender = new JLabel("Select Gender");
		
		name = new JTextField(30);
		fatherName=new JTextField(30);
		
		DateOfEnq=new JDateChooser();
		DateOfEnq.setDateFormatString("yyyy/MM/dd");
		CourseOfEnq=new JComboBox();
		session=new JComboBox();
		
		mobile1=new JTextField(30);
		mobile2=new JTextField(30);
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		buttonGroup = new ButtonGroup();
		buttonGroup .add(male);
		buttonGroup .add(female);
		email=new JTextField(30);
		distric=new JComboBox();
		state=new JComboBox();
		pincode=new JTextField(30);
		address=new JTextField(30);
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		
		lname.setBounds(50, 40, 200, 25);
		name.setBounds(250, 40, 200, 25);
		lfatherName.setBounds(50, 70, 200, 25);
		fatherName.setBounds(250, 70, 200, 25);
		gender.setBounds(50, 100, 200, 25);
		male.setBounds(250, 100, 80, 25);
		female.setBounds(250+80, 100, 80, 25);
		lDateOfEnq.setBounds(50, 130, 200, 25);
		DateOfEnq.setBounds(250, 130, 200, 25);
		lCourseOfEnq.setBounds(50, 160, 200, 25);
		CourseOfEnq.setBounds(250, 160, 200, 25);
		lsession.setBounds(50, 190, 200, 25);
		session.setBounds(250, 190, 200, 25);
		lmobile1.setBounds(50, 220, 200, 25);
		mobile1.setBounds(250, 220, 200, 25);
		lmobile2.setBounds(50, 250, 200, 25);
		mobile2.setBounds(250, 250, 200, 25);
		lemail.setBounds(50, 280, 200, 25);
		email.setBounds(250, 280, 200, 25);
		
		lstate.setBounds(50, 310, 200, 25);
		state.setBounds(250, 310, 200, 25);
		ldistric.setBounds(50, 340, 200, 25);
		distric.setBounds(250, 340, 200, 25);
		lpincode.setBounds(50, 370, 200, 25);
		pincode.setBounds(250, 370, 200, 25);
		laddress.setBounds(50, 400, 200, 25);
		address.setBounds(250, 400, 200, 25);
		save.setBounds(250, 430, 100, 25);
		cancel.setBounds(350, 430, 100, 25);
		
		
		
		
		
		this.add(lname);
		this.add(name);
		this.add(lfatherName);
		this.add(fatherName);
		this.add(gender);
		this.add(male);
		this.add(female);
		this.add(lDateOfEnq);
		this.add(DateOfEnq);
		this.add(lCourseOfEnq);
		this.add(CourseOfEnq);
		this.add(lsession);
		this.add(session);
		this.add(lmobile1);
		this.add(mobile1);
		this.add(lmobile2);
		this.add(mobile2);
		this.add(lemail);
		this.add(email);
		this.add(lstate);
		this.add(state);
		this.add(ldistric);
		this.add(distric);
		this.add(lpincode);
		this.add(pincode);
		this.add(laddress);
		this.add(address);
		this.add(save);
		this.add(cancel);
		this.setSize(550, 550);
		this.setLocation(400, 50);
		
	}
	
	public static void main(String[] args) {
		new NewEnquery(false);
	}

	
}
