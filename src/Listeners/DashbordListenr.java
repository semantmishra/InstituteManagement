package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JFrame;

import Form.Dashbord;
import Form.enquery.ManageEnquery;
import Form.login.Login;
import Form.student.StudentFrom;
public class DashbordListenr implements ActionListener {
	Dashbord dashbord;
	Login login;
	public DashbordListenr(Dashbord dashbord,Login login) {
		this.dashbord = dashbord;
		this.login = login;
	}

	public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource()==dashbord.student)
			{
				new StudentFrom();
			}else if(arg0.getSource()==dashbord.logout){
				dashbord.setVisible(false);
				login.setVisible(true);
				
			}else if(arg0.getSource()==dashbord.enquery){
				new ManageEnquery();
			}
			
		
	}

}
