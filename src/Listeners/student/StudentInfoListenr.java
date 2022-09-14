package Listeners.student;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Form.DocumentForm;
import Form.student.FeePaymentForm;
import Form.student.StudentInfo;
import beans.Address;
import beans.Cities;
import beans.Course;
import beans.Document;
import beans.FeeBean;
import beans.SessionBean;
import beans.State;
import beans.StudentBean;
import dao.FeePayment;
import dao.StudentDao;
import utilis.Camera;
import utilis.ComboBox;
import utilis.Gender;
import utilis.Icons;
import utilis.LoadData;
import utilis.Message;
import utilis.Photo;

public class StudentInfoListenr implements ActionListener {
StudentInfo info;
InputStream file;
List<Document> documents;
StudentListenr studentListenr;
	public StudentInfoListenr(StudentInfo info,StudentListenr studentListenr) {
	this.info = info;
	this.studentListenr=studentListenr;
	
	loadDataIntoComBoBox();
	enableCmponent(false);
	if(info.id!=0 && info.isUpdate) {
		update();
		enableCmponent(true);
	}
}
void enableCmponent(boolean b){
	info.uploadDoc.setVisible(b);
	info.addFee.setVisible(b);
	info.print.setVisible(b);
}
	private void update() {
//		this.getFeeDetails(info.id);
		this.getDocument(info.id);
		StudentBean studentBean= new StudentDao().getStudent(info.id);
		info.name.setText(studentBean.getName());
		info.fname.setText(studentBean.getFatherName());
		info.mname.setText(studentBean.getMothername());
		
		if(Gender.MALE == studentBean.getGender())
			 info.male.setSelected(true);
		else
			 info.female.setSelected(true);
		
		info.mobile1.setText(studentBean.getMobile1());
		info.mobile2.setText(studentBean.getMobile2());
		info.email.setText(studentBean.getEmail());

		new ComboBox().setSelected(info.course, studentBean.getCourse());
		
		info.session.setSelectedItem(studentBean.getSession());
		
		info.DOA.setDate( studentBean.getDoa());
		info.DOB.setDate( studentBean.getDOB());
		
		info.feesh.setText(String.valueOf(studentBean.getCourse().getFee()));
		
		//info.photo.setIcon( new ImageIcon( ) );
		Icons.getImage(info.photo,new Photo().getByte(studentBean.getPhoto()) );
		
		info.address.setText(studentBean.getAddress().getAdderss()  );
		info.state.setSelectedItem(studentBean.getAddress().getState());
		
		int stateId =  ((State)info.state.getSelectedItem()).getId();
		
		new LoadData().citiesComboBox(info.distric,stateId);
		System.out.println(studentBean.getAddress().getDistrict());
		info.distric.setSelectedItem(studentBean.getAddress().getDistrict());
		
		studentBean.setFees(new FeePayment().getFee(info.id));
		DefaultTableModel mode = new DefaultTableModel();
		mode.addColumn("Id");
		mode.addColumn("Amount");
		mode.addColumn("Date");
		info.jTable.setModel(mode);
		for (FeeBean feeBean : studentBean.getFees()) {
			Object o[] = {feeBean.getId(),feeBean.getFee(),feeBean.getDate() };
			mode.addRow(o);
		}
		
	}

	private void getDocument(int id) {
		documents = new dao.DocumentDao ().getDocument(id,DocumentForm.STUDENT);
		DefaultTableModel mode = new DefaultTableModel();
		mode.addColumn("Id");
		mode.addColumn("Document");
		
		info.jTableDoc.setModel(mode);
		for (Document document: documents) {
			Object o[] = {document.getId(),document.getDocTitle() };
			mode.addRow(o);
		}
	}

	void loadDataIntoComBoBox(){
		LoadData data= new LoadData();
		data.courseComboBox(info.course);
		data.sessionComboBox(info.session);
		data.stateComboBox(info.state);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(info.state==e.getSource()) {
				int stateId =  ((State)info.state.getSelectedItem()).getId();
				new LoadData().citiesComboBox(info.distric,stateId);
		}
		
		if(info.course==e.getSource()) {			
				float fee =  ((Course)info.course.getSelectedItem()).getFee();
				info.feesh.setText(String.valueOf(fee));
				info.feesh.setEditable(false);
		}
		
		switch (e.getActionCommand()) {
		case "Save":
			save();
			break;
		case "Reset":
			reset();
			break;
		case "Fee Payment":
			feePayment();
			break;
		case "Upload Document":
			uploadDocument();
			break;
		case "UploadImage":
		file=Icons.openImage(info.photo);
			break;
		case "camera":
			new Thread(()->{
				String f =new Camera().capture();
				Icons.openImage(info.photo,f);
				try {
					file =new FileInputStream(f);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//new File(f).delete();	
			}).start();
			
			
				break;
			
			
		}
		
	}
	private void uploadDocument() {
		new DocumentForm(this.info.id,DocumentForm.STUDENT);
	}
	
	private void feePayment() {
		new FeePaymentForm(info.id);
	}
	
	private void save() {
		if(file==null) {
			Message.error(info, "Select Photo", "Error");
			return;
		}
			
		if(validation.StudentInfoValidate.isValidate(this.info)) {
			StudentBean studentBean = new StudentBean();
			studentBean.setId(info.id );
			studentBean .setName( info.name.getText().trim());
			studentBean .setPhoto(file);
			studentBean .setFatherName( info.fname.getText().trim());
			studentBean .setMothername( info.mname.getText().trim());
			studentBean .setGender(getGender());
			studentBean .setMobile1( info.mobile1.getText().trim() );
			studentBean .setMobile2( info.mobile2.getText().trim() );
			studentBean .setEmail( info.email.getText().trim() );
			Course course = new Course();
			course .setId( ((Course)info.course.getSelectedItem()).getId());
			studentBean .setCourse(course);
			java.sql.Date date= new java.sql.Date(info.DOB.getDate().getTime()); 	//get DOB
			 java.sql.Date doa= new java.sql.Date(info.DOA.getDate().getTime());	//get DOA
			 studentBean .setDOB(date);
			 studentBean .setDoa(doa);
			
			studentBean.setSession((SessionBean)info.session.getSelectedItem());
			
			Address  address = new Address();
			address.setAdderss( info.address.getText().trim() );
			address.setState( (State)info.state.getSelectedItem() );
			address.setDistrict((Cities) info.distric.getSelectedItem());
			studentBean.setAddress(address);
			if(info.isUpdate) {
				if( new StudentDao().update(studentBean) ){ 
					JOptionPane.showMessageDialog(null, "Update success", "Students", JOptionPane.INFORMATION_MESSAGE);
					studentListenr.getStudentDetail();
					info.dispose();
					
					}
				else 
					JOptionPane.showMessageDialog(null, "Updation Faild", "Students", JOptionPane.ERROR_MESSAGE);
			}else {
				if(new StudentDao().save(studentBean)) {
					JOptionPane.showMessageDialog(null, "Save success", "Students", JOptionPane.INFORMATION_MESSAGE);
					studentListenr.getStudentDetail();
					info.dispose();
					}
				else 
					JOptionPane.showMessageDialog(null, "Save Faild", "Students", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	private int getGender() {
		if(info.male.isSelected())
			return Gender.MALE;
		else
			return Gender.FEMALE;
	}
	
	private void reset() {
		
		info.photo.setIcon( Icons.noImage() );		
		info.name.setText("");
		info.fname.setText("");
		info.mname.setText("");
		
		info.male.setSelected(false);
		info.female.setSelected(false);
		
		info.mobile1.setText("");
		info.mobile2.setText("");
		//info.DOB.setText("");
		//info.DOA.setText("");
		info.email.setText("");
		info.course.setSelectedIndex(0);
		info.session.setSelectedIndex(0);
		
		info.feesh.setText("");
		info.address.setText("");
		info.distric.setSelectedIndex(0);
		info.state.setSelectedIndex(0);
		
	}
	
}
