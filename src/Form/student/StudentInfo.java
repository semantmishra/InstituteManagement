package Form.student;

import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;

import Listeners.student.StudentInfoListenr;
import Listeners.student.StudentListenr;
import beans.Course;
import beans.FeeBean;
import beans.SessionBean;
import beans.StudentBean;
import dao.FeePayment;
import dao.StudentDao;
import utilis.Component;
import utilis.Icons;
import utilis.LoadData;

public class StudentInfo extends JFrame {
	public boolean isOk;
	public JTextField name,fname,mname,mobile1,mobile2,email,feesh,address;
	public JDateChooser DOB,DOA;
	public JComboBox course,session,distric,state;
	public JLabel photo,lname,lemail,gender,lfname,lmname,lDOB,lDOA,lcourse,lsession,lmobile1,lmobile2,lfeesh,laddress,ldistric,lstate;
	
	public JRadioButton male,female;
	public ButtonGroup buttonGroup;
	public JTabbedPane tab;
	public JPanel panel1,panel2,panel3;
	public JTable feeTable;
	public JTable jTable,jTableDoc;
	public JButton save,reset,addPhoto,export,addFee,uploadDoc,capturePhoto,print;
	public JButton first,next,pre,last;
	public ImageIcon icon;
	public JToolBar bar;
	
	public boolean isUpdate;
	public int id;
	public StudentListenr studentListenr;
	public StudentInfo(boolean isUpdate,int id,StudentListenr studentListenr) {
		super("Student info");
		this.isUpdate = isUpdate;
		this.studentListenr = studentListenr;
		this.id = id;
		createControlAndAddForm();
	}

	private void createControlAndAddForm() {
		
		first = new JButton(Icons.getImage(Icons.first));
		next= new JButton(Icons.getImage(Icons.next));
		pre= new JButton(Icons.getImage(Icons.previous));
		last= new JButton(Icons.getImage(Icons.last));
		print = new JButton("Print");
		bar = new JToolBar();
		bar.setBounds(0, 0, 1000, 50);
		icon = Icons.noImage();
		photo = new JLabel(icon);
		addPhoto = new JButton(Icons.getImage(Icons.addIcon));
		capturePhoto = new JButton(Icons.getImage(Icons.camera));
		
		
		addFee = new JButton("Fee Payment");
		uploadDoc = new JButton("Upload Document");
		
		bar.add(addFee);
		bar.add(uploadDoc);
		
		addPhoto.setBorder(null);
		capturePhoto.setBorder(null);
		jTable = Component.getJtable();
		jTableDoc = Component.getJtable();
		feeTable  =Component.getJtable();;
		name = new JTextField(10);
		email = new JTextField(10);
		fname=new JTextField(10);
		mname = new JTextField(10);
		DOB= new JDateChooser();
		DOB.setDateFormatString("yyyy/MM/dd");
		DOA = new JDateChooser();
		DOA.setDateFormatString("yyyy/MM/dd");
		mobile1=new JTextField(10);
		mobile2=new JTextField(10);
		feesh=new JTextField(10);
		session=new JComboBox<SessionBean>();
		course = new JComboBox<Course>();
		address = new JTextField(10);
		distric=new JComboBox<Course>();
		state= new JComboBox<Course>();
		
		male = new  JRadioButton("Male");
		female = new  JRadioButton("Female");	
		save = new JButton("Save");
		reset = new JButton("Reset");
		tab = new JTabbedPane();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.setName("Feesh Details");
		panel2.setName("Address");
		panel3.setName("Documents");
		panel2.setLayout(null);
		
		
		//panel1.add(jTable);
		//panel1.add(new JScrollPane(jTable));

		panel3.add(jTableDoc);
		
		jTableDoc.setBackground(Color.BLACK);
		
		tab.add(panel1);
		tab.add(panel2);
		tab.add(panel3);
		buttonGroup =new ButtonGroup();
		buttonGroup.add(male);
		buttonGroup.add(female);
		
		lname =new JLabel("Enter Name");
		lemail =new JLabel("Enter Email");
		gender = new JLabel("Gender");
		
		lfname = new JLabel("Enter Father Name");
		lmname = new JLabel("Enter Mother Name");
		lDOB=new JLabel("Date Of Birth");
		lDOA = new JLabel("Date Of Admion");
		lcourse=new JLabel("Select Course");
		lmobile1= new JLabel("Enter 1 Mobile No.");
		lmobile2 = new JLabel("Enter 2 Mobile No.");
		lfeesh = new JLabel("Feesh");
		lsession =  new JLabel("Session");
		laddress=new JLabel("Address");
		ldistric=new JLabel("District");
		lstate=new JLabel("State");
		
		photo.setBounds(10, 70, 200, 200);
		
		addPhoto.setBounds(40, 270, 25, 25);
		capturePhoto.setBounds(100, 270, 25, 25);
		capturePhoto.setIcon(new ImageIcon(Icons.getImage( Icons.camera ).getImage().getScaledInstance(capturePhoto.getWidth(), capturePhoto.getHeight(), Image.SCALE_DEFAULT)));
		addPhoto.setActionCommand("UploadImage");
		
	
		lname.setBounds(230, 80, 110, 25);//70
		lfname.setBounds(230, 120, 110, 25);
		lmname.setBounds(230, 160, 110, 25);
		gender.setBounds(230, 110+70, 110, 50);
		lmobile1.setBounds(230, 150+70, 110, 25);
		lmobile2.setBounds(230, 180+70, 110, 25);
		lemail.setBounds(580, 20+60, 110, 25);
		lcourse.setBounds(580, 45+70, 110, 25);
		lDOB.setBounds(580, 85+70, 100, 25);
		lDOA.setBounds(580, 120+70, 100, 25);
		lfeesh.setBounds(580, 150+70, 50, 25);
		lsession.setBounds(580, 180+70, 50, 25);
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
		course.setBounds(670, 50+70, 190, 25);
		DOB.setBounds(670, 85+70, 190, 25);
		DOA.setBounds(670, 120+70, 190, 25);
		feesh.setBounds(670, 150+70, 190, 25);
		session.setBounds(670, 180+70, 190, 25);
		address.setBounds(70, 10, 600, 25);
		
		state.setBounds(70, 50, 600, 25);
		distric.setBounds(70, 90, 600, 25);
		
		tab.setBounds(100, 250+70, 755, 200);
		save.setBounds(640, 530, 100, 40);
		reset.setBounds(751, 530, 100, 40);
		capturePhoto.setActionCommand("camera");
		
		StudentInfoListenr studentInfoListenr = new StudentInfoListenr(this,studentListenr);
		save.addActionListener(studentInfoListenr);
		reset.addActionListener(studentInfoListenr);
		addPhoto.addActionListener(studentInfoListenr);
		capturePhoto.addActionListener(studentInfoListenr);
		addFee.addActionListener(studentInfoListenr);
		uploadDoc.addActionListener(studentInfoListenr);
		course.addActionListener(studentInfoListenr);
		state.addActionListener(studentInfoListenr);
		bar.add(save);
		
//		bar.add(first,RIGHT_ALIGNMENT);
//		bar.add(next,RIGHT_ALIGNMENT);
//		bar.add(pre,RIGHT_ALIGNMENT);
//		bar.add(last,RIGHT_ALIGNMENT);
		
		bar.add(print);
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
		this.add(lcourse);
		
		this.add(course);
		this.add(lDOB);
		this.add(DOB);
		this.add(lDOA);
		this.add(DOA);
		this.add(lfeesh);
		this.add(feesh);
		this.add(lsession);
		this.add(session);
		panel2.add(laddress);
		panel2.add(address);
		panel2.add(ldistric);
		panel2.add(distric);
		panel2.add(lstate);
		panel2.add(state);
		JScrollPane j = new JScrollPane(jTable);
		panel1.add(j);
		panel3.add(new JScrollPane(jTableDoc));
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
		new StudentInfo(false,0,null);
	}
}
