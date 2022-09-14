package Listeners.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.log.SysoCounter;

import Form.enquery.NewEnquery;
import Form.student.FeePaymentForm;
import Form.student.StudentFrom;
import Form.student.StudentInfo;
import beans.Course;
import beans.SessionBean;
import beans.StudentBean;
import dao.Dao;
import dao.EnqueryDao;
import dao.MyDao;
import dao.StudentDao;
import utilis.Exports;
import utilis.Gender;
import utilis.Icons;
import utilis.LoadData;
import utilis.Message;

public class StudentListenr implements ActionListener,MouseListener,KeyListener {

	StudentFrom from;
	public StudentListenr(StudentFrom from){
		this.from = from;
		getStudentDetail();
		loadComBobox();
	}
	
	private void loadComBobox() {
		new LoadData().sessionComboBox(from.session);
	}

	public void getStudentDetail() {
		List<StudentBean> stduents = new StudentDao().getAllStudent();
		loadDataJtable(stduents);
	}

	
	private void loadDataJtable(List<StudentBean> stduents) {
		DefaultTableModel model = new DefaultTableModel();
		Vector<String> col = new Vector<String>();
		col.add("Id");
		col.add("Std id");
		col.add("Name");
		col.add("FatherName");
		col.add("MotherName");
		col.add("Gender");
		col.add("Email");
		col.add("Mobile1");
		col.add("Course");
		col.add("Session");
		model.setColumnIdentifiers(col);
		from.table.setModel(model);
		try {
			for (StudentBean student : stduents) {
				Object []o = {
						student.getId(),
						student.getMyId(),
						student.getName(),
						student.getFatherName(),
						student.getMothername(),
						Gender.getGender(student.getGender()),
						student.getEmail(),
						student.getMobile1(),
						student.getCourse().getName(),
						student.getSession().getName()
						};
				model.addRow(o);
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		model=null;
		stduents=null;
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case"session":
			searchBySession();
			break;
		case"Search":
			search();
			break;			
		case "Add Student":
			new StudentInfo(false,0,this);
			break;
		case "Delete Record":
		deleteRecord("dao.StudentDao",from.table,"DELETE FROM `students` WHERE id=?");
			break;
		case "Update Record":
			int selectIndex = from.table.getSelectedRow();
			int id = Integer.valueOf(from.table.getValueAt(selectIndex, 0).toString());
			new StudentInfo(true,id,this);
			break;
		case "View Record":
			System.out.println("Print Preview");
			break;
		case "Fee Payment":
			int index = from.table.getSelectedRow();
			int delId = Integer.valueOf(from.table.getValueAt(index, 0).toString());
			new FeePaymentForm(delId);
			break;
			
		case "Export":
			showExportMenu();
			break;
		case "PDF":
			new Exports().pdf(from.table);
			break;
		case "HTML":
			new Exports().csvOrHtml(from.table, Exports.HTML);
			break;
		case "Excel":
			new Exports().excel(from.table,"Student Details");
			break;
		case "CSV":
			new Exports().csvOrHtml(from.table,Exports.CSV);
			break;
			
		}


		
	}
	
	private void searchBySession() {
		//from.nameOrId.setText("");
		if(from.session.getSelectedIndex()!=0) {
			List<StudentBean> lists= new StudentDao().getStudentsBySession(((SessionBean)from.session.getSelectedItem()).getId());
			loadDataJtable(lists);	
		}else
			 getStudentDetail();
	}

	private void search() {
			from.session.setSelectedIndex(0);
			 if(validation.Validation.isNumeric(from.nameOrId.getText().trim())) {
				StudentBean list= new StudentDao().getStudent(Integer.valueOf(from.nameOrId.getText().trim()));
					loadDataJtable( Arrays.asList(list));	
			 }else {
				 getStudentDetail();
			 }
			 //sql
//			 String sql ="select * from students where name LIKE '%se%' || course_id = '3' || session_id =  ";
//			 String name = from.nameOrId.getText().trim().isEmpty()==true?"":from.nameOrId.getText().trim();
//			 int course = ((Course)from.course.getSelectedItem()).getId();
//			 int session = ((SessionBean)from.session.getSelectedItem()).getId();
//			 if(!from.nameOrId.getText().trim().isEmpty()) {
//				 sql+="name LIKE '%"+from.nameOrId.getText().trim()+"%'";
//			 }
//			 if(from.course.getSelectedIndex()!=0) {
//				 sql+=" and course_id= "+((Course)from.course.getSelectedItem()).getId()+"";
//			 }
			 
		 }

	private void showExportMenu() {
		from.exportMenu.show(from.export,from.export.HEIGHT-2, from.export.getWidth()-20);
	}

	private void deleteRecord(String classname,JTable table,String sql) {
		if(new MyDao().delete(classname, table,sql))
			getStudentDetail();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==3 && from.table.getSelectedRowCount()>0 ) {		
			from.studentMenu.show(from.table, e.getX(), e.getY());
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {	}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		search();
	}

}
