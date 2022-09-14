package Form.employee;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import Form.Template;
import Listeners.employee.AddEmployeeListenr;
import beans.Course;
import beans.SessionBean;
import utilis.Component;
import utilis.Icons;

public class AddNewEmployee extends Template  {

		public JTextField name,fname,mname,mobile1,mobile2,email,salray,address;
		public JDateChooser DOB,DOJ;
		public JComboBox distric,state,empType;
		public JLabel photo, lname,lemail,gender,lfname,lmname,lDOB,lDOJ,lmobile1,lmobile2,lsalary,laddress,ldistric,lstate,lempType;
		
		public JRadioButton male,female;
		public ButtonGroup buttonGroup;
		public JTabbedPane tab;
		public JPanel panel1,panel2,panel3;
		public JTable salaryDitails,jTableDoc;
		public JButton save,reset,addPhoto,export,salaryPayment,uploadDoc,assignSubject;
		public JToolBar bar;
		public boolean isUpdate;
		public int id;
		private JButton capturePhoto;
		public AddNewEmployee(boolean isUpdate,int id)
		{
			super("Add Employee");
			this.id=id;
			this.isUpdate =isUpdate;
			createControlAndAddForm();
		}
		private void createControlAndAddForm() {
			bar = new JToolBar();
			bar.setBounds(0, 0, 1000, 50);
			
			photo = new JLabel(Icons.noImage());
			addPhoto = new JButton(Icons.getImage(Icons.addIcon));
			salaryPayment = new JButton("Salary Payment");
			uploadDoc = new JButton("Upload Document");
			assignSubject = new JButton("Assign Subject");
			bar.add(salaryPayment);
			bar.add(uploadDoc);
			bar.add(assignSubject);
			addPhoto.setBorder(null);
			
			capturePhoto = new JButton();
			capturePhoto.setBounds(100, 270, 25, 25);
			capturePhoto.setIcon(new ImageIcon(Icons.getImage(Icons.camera).getImage().getScaledInstance(capturePhoto.getWidth(), capturePhoto.getHeight(), Image.SCALE_DEFAULT)));
			capturePhoto.setActionCommand("Camera");
			capturePhoto.setBorder(null);

			salaryDitails = Component.getJtable();
			jTableDoc = Component.getJtable();
			name = new JTextField(10);
			email = new JTextField(10);
			empType = new JComboBox();
			fname=new JTextField(10);
			mname = new JTextField(10);
			DOB= new JDateChooser();
			DOB.setDateFormatString("yyyy/MM/dd");
			DOJ = new JDateChooser();
			DOJ.setDateFormatString("yyyy/MM/dd");
			mobile1=new JTextField(10);
			mobile2=new JTextField(10);
			salray=new JTextField(10);

			address = new JTextField(10);
			distric=new JComboBox<Course>();
			state= new JComboBox<Course>();
			state.setActionCommand("state");
			male = new  JRadioButton("Male");
			female = new  JRadioButton("Female");	
			save = new JButton("Save");
			reset = new JButton("Reset");
			tab = new JTabbedPane();
			panel1 = new JPanel();
			panel2 = new JPanel();
			panel3 = new JPanel();
			panel1.setName("Salary Details");
			panel2.setName("Address");
			panel3.setName("Documents");
			panel2.setLayout(null);
			panel1.add(salaryDitails);
			panel3.add(jTableDoc);
			
			
			tab.add(panel1);
			tab.add(panel2);
			tab.add(panel3);
			buttonGroup =new ButtonGroup();
			buttonGroup.add(male);
			buttonGroup.add(female);
			
			lname =new JLabel("Enter Name");
			lemail =new JLabel("Enter Email");
			lempType =new JLabel("Employee Type");
			gender = new JLabel("Gender");
			
			
			lfname = new JLabel("Enter Father Name");
			lmname = new JLabel("Enter Mother Name");
			lDOB=new JLabel("Date Of Birth");
			lDOJ = new JLabel("Date Of Joining");
			
			lmobile1= new JLabel("Enter 1 Mobile No.");
			lmobile2 = new JLabel("Enter 2 Mobile No.");
			lsalary = new JLabel("Salaray");
			
			laddress=new JLabel("Address");
			ldistric=new JLabel("District");
			lstate=new JLabel("State");
			
			photo.setBounds(10, 70, 200, 200);
			addPhoto.setBounds(40, 270, 25, 25);
			
			addPhoto.setActionCommand("UploadImage");
			
		
			lname.setBounds(230, 80, 110, 25);//70
			lfname.setBounds(230, 120, 110, 25);
			lmname.setBounds(230, 160, 110, 25);
			gender.setBounds(230, 110+70, 110, 50);
			lmobile1.setBounds(230, 150+70, 110, 25);
			lmobile2.setBounds(230, 180+70, 110, 25);
			lemail.setBounds(580, 20+60, 110, 25);
			lempType.setBounds(580, 60+60, 110, 25);
			
			lDOB.setBounds(580, 85+70, 100, 25);
			lDOJ.setBounds(580, 120+70, 100, 25);
			lsalary.setBounds(580, 150+70, 50, 25);
			
			laddress.setBounds(10, 10, 50, 25);
			lstate.setBounds(10, 50, 50, 25);
			ldistric.setBounds(10, 90, 50, 25);
			
			
			name.setBounds(350, 80, 190, 25);
			fname.setBounds(350, 120, 190, 25);
			mname.setBounds(350, 85+70, 190, 25);
			male.setBounds(348, 120+70, 60, 20);
			female.setBounds(410, 120+70, 70, 20);
			mobile1.setBounds(350, 150+70, 190, 25);
			mobile2.setBounds(350, 180+70, 190, 25);
			
			email.setBounds(670, 20+60, 190, 25);
			empType.setBounds(670, 60+60, 190, 25);
			
			DOB.setBounds(670, 85+70, 190, 25);
			DOJ.setBounds(670, 120+70, 190, 25);
			salray.setBounds(670, 150+70, 190, 25);
			
			address.setBounds(70, 10, 600, 25);
			
			state.setBounds(70, 50, 600, 25);
			distric.setBounds(70, 90, 600, 25);
			
			tab.setBounds(100, 250+70, 755, 200);
			
			salaryDitails.setBounds(70, 10, 600, 100);
			jTableDoc.setBounds(70, 20, 600, 100);
			JScrollPane jScrollPane = new JScrollPane(jTableDoc);
			
			save.setBounds(640, 530, 100, 40);
			reset.setBounds(751, 530, 100, 40);
			
			AddEmployeeListenr addEmployeeListenr = new AddEmployeeListenr(this);
			state.addActionListener(addEmployeeListenr);
			capturePhoto.addActionListener(addEmployeeListenr);
			save.addActionListener(addEmployeeListenr);
			reset.addActionListener(addEmployeeListenr);
			addPhoto.addActionListener(addEmployeeListenr);
			salaryPayment.addActionListener(addEmployeeListenr);
			uploadDoc.addActionListener(addEmployeeListenr);
			bar.add(save);
			this.add(bar);
			this.add(photo);
			this.add(addPhoto);
			this.add(capturePhoto);
			this.add(lname);
			this.add(name);
			this.add(lfname);
			this.add(fname);
			this.add(lmname);
			this.add(mname);
			this.add(gender);
			this.add(male);
			this.add(female);
			this.add(lmobile1);
			this.add(mobile1);
			this.add(lmobile2);
			this.add(mobile2);
			this.add(lemail);
			this.add(email);
			this.add(lempType);
			this.add(empType);
			this.add(lDOB);
			this.add(DOB);
			this.add(lDOJ);
			this.add(DOJ);
			this.add(lsalary);
			this.add(salray);
			
			panel2.add(laddress);
			panel2.add(address);
			panel2.add(ldistric);
			panel2.add(distric);
			panel2.add(lstate);
			panel2.add(state);
			panel1.add(salaryDitails);
			panel3.add(jScrollPane);
			this.add(tab);
			this.add(save);
			this.add(reset);
			this.setSize(900, 650);
			this.setLocation(200, 50);
			this.setLayout(null);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			
		}
		public static void main(String[] args) {
			new AddNewEmployee(false,0);
		}

}
