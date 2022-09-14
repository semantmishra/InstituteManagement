package Form;

import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.io.InputStream;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import beans.Document;
import utilis.Icons;
import utilis.Photo;

public class DocumentForm extends Template {
public static final int EMPLOYEE=1,STUDENT=2;
public int studentId;
public JButton uploadDoc;
public JLabel photo;
private JTextField docTotle;
private JLabel lable;
public JButton first,next,pre,last;
int docType;
public DocumentForm(int studentId,int docType ) {
	super("Upload Document");
	this.studentId = studentId;
	this.docType =docType; 
	initComponent();
	event();
}

private void event() {
	uploadDoc.addActionListener(e ->{
		photo.setSize(500, 640);
//		System.out.println( photo.getHeight());
//		System.out.println(photo.getWidth());
		
		if(!docTotle.getText().trim().equals("")) {
			InputStream file = Icons.openImage(photo);
			if(file!=null) {
				Document doc=  new Document();
				doc.setStudentOrEmpId(studentId);
				doc.setDocTitle(docTotle.getText().trim());
				doc.setDocument(new Photo().getByte(file));
				String sql="";
				if(docType==DocumentForm.EMPLOYEE)
					sql = "INSERT INTO `empdoc`(`emp_id`,`doc_title`,`document`) VALUES (?,?,?)";
				else
					sql = "INSERT INTO `stddocument`(`student_id`,doc_title,`document`)VALUES(?,?,?)";
				
				if(new dao.DocumentDao().save(doc,sql)) {
					docTotle.setText("");	
					JOptionPane.showMessageDialog(this, "Document upload success . . .","Document" , JOptionPane.OK_OPTION, new ImageIcon(Icons.successIcon));
					file=null;
				}
			}	
		}else
			JOptionPane.showMessageDialog(this, "Enter Document title ","Document" , JOptionPane.OK_OPTION, new ImageIcon(Icons.deleteIcon));
			
		
	});
}

private void initComponent() {
	first = new JButton(Icons.getImage(Icons.first));
	next= new JButton(Icons.getImage(Icons.next));
	pre= new JButton(Icons.getImage(Icons.previous));
	last= new JButton(Icons.getImage(Icons.last));
	
	lable = new JLabel("Enter Docment Title");
	docTotle = new JTextField(20);
	uploadDoc = new JButton("Upload");
	photo = new JLabel();
	
	first.setBounds(570, 10, 20, 20);
	next.setBounds(600, 10, 20, 20);
	pre.setBounds(640, 10, 20, 20);
	last.setBounds(670, 10, 20, 20);
	
	lable.setBounds(10, 30, 200, 25);
	docTotle.setBounds(10, 60, 200, 25);
	uploadDoc.setBounds(220, 60, 100, 25);
	photo.setBounds(345, 35, 600, 700);
	photo.setSize(500, 640);
//	System.out.println( photo.getHeight());
//	System.out.println(photo.getWidth());
	this.add(lable);
	this.add(docTotle);
	this.add(uploadDoc);
	this.add(first);
	this.add(next);
	this.add(pre);
	this.add(last);
	this.add(photo);
	this.setSize(900, 650);
	this.setLocation(300, 50);
}
	public static void main(String[] args) {
		new DocumentForm(1,0);	
	}
	
}
