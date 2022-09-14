package Listeners.employee;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ProcessHandle.Info;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import Form.DocumentForm;
import Form.employee.AddNewEmployee;
import beans.Address;
import beans.Cities;
import beans.Course;
import beans.Document;
import beans.EmployeeBean;
import beans.EmployeeType;
import beans.FeeBean;
import beans.SessionBean;
import beans.State;
import beans.StudentBean;
import beans.Subject;
import dao.EmployeeDao;
import dao.FeePayment;
import dao.StudentDao;
import utilis.Camera;
import utilis.Gender;
import utilis.Icons;
import utilis.LoadData;
import utilis.Message;
import utilis.Photo;

public class AddEmployeeListenr implements ActionListener,ItemListener {
	AddNewEmployee addNewEmployee;
	private InputStream file;
	
	public AddEmployeeListenr(AddNewEmployee addNewEmployee) {
		this.addNewEmployee = addNewEmployee;
		loadDataCombo();
		if(addNewEmployee.id!=0 && addNewEmployee.isUpdate) {
			update();
			enableCmponent(true);
		}
	}
	void enableCmponent(boolean b){
		addNewEmployee.uploadDoc.setVisible(b);
		addNewEmployee.salaryPayment.setVisible(b);
		//addNewEmployee.print.setVisible(b);
	}
	
	private void update() {
		//this.getFeeDetails(info.id);
		this.getDocument(addNewEmployee.id);
		System.out.println(addNewEmployee.id);
		EmployeeBean employeeBean= new EmployeeDao().getEmployeeById(addNewEmployee.id);
		addNewEmployee.name.setText(employeeBean.getName());
		addNewEmployee.fname.setText(employeeBean.getFatherName());
		addNewEmployee.mname.setText(employeeBean.getMothername());
		
		if(Gender.MALE == employeeBean.getGender())
			addNewEmployee.male.setSelected(true);
		else
			addNewEmployee.female.setSelected(true);
		
		addNewEmployee.mobile1.setText(employeeBean.getMobile1());
		addNewEmployee.mobile2.setText(employeeBean.getMobile2());
		addNewEmployee.email.setText(employeeBean.getEmail());

		//info.course.setSelectedItem(studentBean.getCourse().toString());
		
		//info.course.setSelectedIndex(studentBean.getCourse().getId());
		
		addNewEmployee.DOJ.setDate( employeeBean.getDoj());
		addNewEmployee.DOB.setDate( employeeBean.getDOB());
		
//		addNewEmployee.salray.setText(String.valueOf(employeeBean.getEmployeeType().getSalaray()));
		
		//info.photo.setIcon( new ImageIcon( ) );
		Icons.getImage(addNewEmployee.photo,new Photo().getByte(employeeBean.getPhoto()) );
		
		addNewEmployee.address.setText(employeeBean.getAddress().getAdderss()  );
		addNewEmployee.state.setSelectedItem(employeeBean.getAddress().getState());
		
		int stateId =  ((State)addNewEmployee.state.getSelectedItem()).getId();
		
		new LoadData().citiesComboBox(addNewEmployee.distric,stateId);
		
		addNewEmployee.distric.setSelectedItem(employeeBean.getAddress().getDistrict());
		
//		studentBean.setFees(new FeePayment().getFee(addNewEmployee.id));
//		DefaultTableModel mode = new DefaultTableModel();
//		mode.addColumn("Id");
//		mode.addColumn("Amount");
//		mode.addColumn("Date");
//		addNewEmployee.jTable.setModel(mode);
//		for (FeeBean feeBean : studentBean.getFees()) {
//			Object o[] = {feeBean.getId(),feeBean.getFee(),feeBean.getDate() };
//			mode.addRow(o);
//		}
		
	}

	
	private void getDocument(int id) {
		List<Document> documents = new dao.DocumentDao ().getDocument(id,DocumentForm.EMPLOYEE);
		DefaultTableModel mode = new DefaultTableModel();
		mode.addColumn("Id");
		mode.addColumn("Document");
		
		
		addNewEmployee.jTableDoc.setModel(mode);
		for (Document document: documents) {
			Object o[] = {document.getId(),document.getDocTitle() };
			mode.addRow(o);
		}
		mode=null;
		documents=null;
	}
	private void loadDataCombo() {
	LoadData data=new LoadData();
	data.empTypeComboBox(addNewEmployee.empType);
	data.stateComboBox(addNewEmployee.state);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Save":
			save();
			break;
		case "Reset":
			reset();
			break;
		case "state":
			int state_id = ((State)addNewEmployee.state.getSelectedItem()).getId();
			new LoadData().citiesComboBox(addNewEmployee.distric , state_id);
			break;
		case "Fee Payment":
			feePayment();
			break;
		case "Upload Document":
			uploadDocument();
			break;
		case "UploadImage":
			file=Icons.openImage(addNewEmployee.photo);
			break;
		case "Camera":
			openCamera();
			break;
			
		}
			
	}
	private void openCamera() {
		new Thread(()->{
			String f =new Camera().capture();
			Icons.openImage(addNewEmployee.photo,f);
			try {
				file =new FileInputStream(f);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			//new File(f).delete();	
		}).start();
	}

	private void uploadDocument() {
		new DocumentForm(addNewEmployee.id,DocumentForm.EMPLOYEE );
	}
	private void feePayment() {
		// TODO Auto-generated method stub	
	}
	private void save() {
		
		if(validation.AddEmployeeValidation.isValidate(addNewEmployee)) {
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setId(addNewEmployee.id);
			employeeBean.setName( addNewEmployee.name.getText().trim());
			employeeBean.setFatherName(addNewEmployee.fname.getText().trim());
			employeeBean.setMothername(addNewEmployee.mname.getText().trim());
			employeeBean.setPhoto(file);
			employeeBean.setGender(addNewEmployee.male.isSelected()?Gender.MALE:Gender.FEMALE);
			employeeBean.setMobile1( addNewEmployee.mobile1.getText().trim());
			employeeBean.setMobile2( addNewEmployee.mobile2.getText().trim().isEmpty()?null:addNewEmployee.mobile2.getText().trim());
			employeeBean.setEmail( addNewEmployee.email.getText().trim());
			employeeBean.setEmployeeType((EmployeeType)addNewEmployee.empType.getSelectedItem());
			java.sql.Date date=new Date(addNewEmployee.DOB.getDate().getTime()) ; 	//get DOB
			java.sql.Date doa= new Date( addNewEmployee.DOJ.getDate().getTime());	//get DOA
			employeeBean .setDOB(date);
			employeeBean .setDoj(doa);
			employeeBean.setSubject(new Subject());
			Address  address = new Address();
			address.setAdderss( addNewEmployee.address.getText().trim() );
			address.setState( (State)addNewEmployee.state.getSelectedItem() );
			address.setDistrict((Cities) addNewEmployee.distric.getSelectedItem());
			employeeBean.setAddress(address);
			if(addNewEmployee.isUpdate) {
				if( new EmployeeDao().update(employeeBean) ){ 
					JOptionPane.showMessageDialog(addNewEmployee,"Update success", "Students", JOptionPane.INFORMATION_MESSAGE);
					addNewEmployee.dispose();
					}
				else 
					JOptionPane.showMessageDialog(addNewEmployee, "Updation Faild", "Employee", JOptionPane.ERROR_MESSAGE);
			}else {
				if(new EmployeeDao().save(employeeBean)) {
					JOptionPane.showMessageDialog(addNewEmployee, "Save success", "Employee", JOptionPane.INFORMATION_MESSAGE);
					addNewEmployee.dispose();
					}
				else 
					JOptionPane.showMessageDialog(addNewEmployee, "Save Faild", "Students", JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	
	
	private void reset() {
		
		addNewEmployee.photo.setIcon( Icons.noImage() );		
		addNewEmployee.name.setText("");
		addNewEmployee.fname.setText("");
		addNewEmployee.mname.setText("");
		addNewEmployee.male.setSelected(false);
		addNewEmployee.female.setSelected(false);
		addNewEmployee.mobile1.setText("");
		addNewEmployee.mobile2.setText("");
		addNewEmployee.DOB.setDate(null);
		addNewEmployee.DOJ.setDate(null);
		addNewEmployee.email.setText("");
		
		addNewEmployee.salray.setText("");
		addNewEmployee.address.setText("");
		addNewEmployee.distric.setSelectedIndex(0);
		addNewEmployee.state.setSelectedIndex(0);
		
	}
	
	
	public void itemStateChanged(ItemEvent e) {
		if(addNewEmployee.empType.getSelectedItem() instanceof EmployeeType) {
			float fee =  ((Course)addNewEmployee.empType.getSelectedItem()).getFee();
			addNewEmployee.salray.setText(String.valueOf(fee));
			addNewEmployee.salray.setEditable(false);
			return;
		}
		addNewEmployee.salray.setText("");
	}
}
