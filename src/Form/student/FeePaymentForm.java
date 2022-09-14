package Form.student;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import beans.Course;
import beans.FeeBean;
import beans.StudentBean;
import dao.FeePayment;
import dao.StudentDao;
import utilis.Gender;
import utilis.Icons;

public class FeePaymentForm extends JFrame  {
	public JTable table;
	public JLabel lid, lname,lcourse,lfeeAmount,ltotalfee,dtotalfee,lbalanceFee,balanceFee,lpayed,payed;
	public JButton payFee,search;
	public JTextField id,feeAmount;
	public Date date;
	JTextArea area;
	DefaultTableModel model;
	StudentBean bean;
	private float totalFee;
	private float totalPayFee;
	public FeePaymentForm() {
	}
	public FeePaymentForm(int Stduentid) {
		id.setText(String.valueOf(Stduentid));
	}
	
	{
		System.out.println("sem");
		intiComponent();
		event();
	}
	
	private void intiComponent() {
		table = utilis.Component.getJtable();
		area = new JTextArea();
		search = new JButton(Icons.getImage(Icons.search));
		lid = new JLabel("Enter Student Id");
		lfeeAmount = new JLabel("Enter Fee Amount");
		feeAmount = new JTextField(10); 
		id = new JTextField(10);
		lname = new JLabel("Name\n : ");
		lcourse = new JLabel("Course : ");
		payFee = new JButton("Payment Fee");
		
		ltotalfee = new  JLabel("Total Fee");
		dtotalfee= new  JLabel("0");
		lbalanceFee= new  JLabel("Balance Fee");
		balanceFee= new  JLabel("0");
		lpayed= new  JLabel("Payed Fee");
		payed= new  JLabel("0");
		
		model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Date");
		model.addColumn("Fee");
		
		table.setModel(model);
		
		lid.setBounds(10, 10, 100, 30);
		id.setBounds(150, 10, 100, 32);
		search.setBounds(250, 10, 30, 30);
		lfeeAmount.setBounds(10, 50, 100, 30);
		feeAmount.setBounds(150, 50, 100, 30);
		payFee.setBounds(250, 50, 150, 30);
		area.setBounds(10, 90, 300, 300);
		
		ltotalfee.setBounds(720, 85, 200, 25);
		dtotalfee.setBounds(720, 110, 200, 25);
		lbalanceFee.setBounds(720, 140, 200, 25);
		balanceFee.setBounds(720, 160, 200, 25);
		lpayed.setBounds(720, 190, 200, 25);
		payed.setBounds(720, 210, 200, 25);
		
		this.add(lid);
		this.add(id);
		this.add(search);
		this.add(lfeeAmount);
		this.add(feeAmount);
		this.add(payFee);
		this.add(area );
		
		lfeeAmount.setVisible(false);
		feeAmount.setVisible(false);
		payFee.setVisible(false);
		area.setVisible(false);
		this.add(lfeeAmount);
		this.add(feeAmount);
		this.add(payFee);
		this.add(area );
		
		table.setRowSelectionAllowed(false);
		JScrollPane jsp =new JScrollPane(table) ;
		jsp.setBounds(410, 85, 300, 300);
		this.add( jsp);
		this.add(ltotalfee);
		this.add(dtotalfee);
		this.add(lbalanceFee);
		this.add(balanceFee);
		this.add(lpayed);
		this.add(payed);
		
		this.setSize(900, 500);
		this.setLocation(300, 100);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}

	private void event() {
		
		search.addActionListener((ActionEvent e)->{
			if( isNumeric(id.getText().trim()) )
			{
				bean = new StudentDao().getStudent(Integer.parseInt(id.getText().trim()));
				
				if(bean !=null) {
					payFee.setEnabled(true);
					this.totalFee = bean.getCourse().getFee();
					
					area .setText( String.format("id \t\t%d\nName \t\t%s\nFather Name \t\t%s\n"
							+ "Mother Name \t\t%s\nGender \t\t%s\nMobile \t\t%s\n"
							+ "Email \t\t%s\nCourse \t\t%s\n",bean.getId(),bean.getName(),
							bean.getFatherName(),bean.getMothername(),bean.getGender()==Gender.MALE?"Male":"Female",
							bean.getMobile1(),bean.getEmail(),bean.getCourse().getName()));
					
					List<FeeBean> fees = new FeePayment().getFee(bean.getId());
					
					this.totalPayFee = 0.0f;
					
					for (FeeBean feeBean : fees) {
						this.totalPayFee+=	feeBean.getFee();
						Object f[] = {feeBean.getId(),feeBean.getDate(),feeBean.getFee()  };
						model.addRow(f);
					}
					this.dtotalfee.setText(this.totalFee+"");
					this.payed.setText(this.totalPayFee+"");
					this.balanceFee.setText((this.totalFee-this.totalPayFee)+"");
					if(this.totalFee==this.totalPayFee) {
						JOptionPane.showMessageDialog(this, "Your Fee Payment Completed no more balance fee . . . !","Fee Completed" , JOptionPane.OK_OPTION, new ImageIcon(Icons.successIcon));
						return;
					}
					lfeeAmount.setVisible(true);
					feeAmount.setVisible(true);
					payFee.setVisible(true);
					area.setVisible(true);
				}else {
					lfeeAmount.setVisible(false);
					feeAmount.setVisible(false);
					payFee.setVisible(false);
					area.setVisible(false);
					JOptionPane.showMessageDialog(this, "Student not fount ","Error" , JOptionPane.OK_OPTION, new ImageIcon(Icons.deleteIcon));
				}
			}
			
		}); 
		
		
		
		payFee.addActionListener((ActionEvent e)->{
			
			if(isNumeric(feeAmount.getText().trim())){
				float amount = Float.parseFloat(feeAmount.getText().trim());
				float validPayAmount = this.totalFee-this.totalPayFee;
				if(amount>1) {
					if( validPayAmount>=amount) {
							FeeBean payment = new FeeBean();
							payment.setStudentId(bean.getId());
							payment.setFee(amount);
							payment.setDate(new Date( new java.util.Date().getTime() ) );
							
						if(new FeePayment().paymentFee(payment)) {
							area.append(String.format("Payed Amount\t\t%f\nPayment Date\t\t%s",payment.getFee() ,payment.getDate() ));
							id.setText("");
							feeAmount.setText("");
							payFee.setEnabled(false);
							JOptionPane.showMessageDialog(this, "Fee payment success . . . !","Payment success" , JOptionPane.OK_OPTION, new ImageIcon(Icons.successIcon));
							try {
								area.print();
							} catch (PrinterException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}	
					}else
						JOptionPane.showMessageDialog(this, "Balance Amount in less than your enter amount  ","Error" , JOptionPane.OK_OPTION, new ImageIcon(Icons.deleteIcon));
				}else
					JOptionPane.showMessageDialog(this, "Enter Amount gretor then 0 ","Error" , JOptionPane.OK_OPTION, new ImageIcon(Icons.deleteIcon));
			}else
				JOptionPane.showMessageDialog(this, "Enter Correct Amount ","Error" , JOptionPane.OK_OPTION, new ImageIcon(Icons.deleteIcon));	

		});
	}

	
	public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
}