package utilis;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import beans.Course;
import beans.SessionBean;

public class ComboBox {
	
	public void setSelected(JComboBox com,Course course){
		for(int i=0;i< com.getItemCount();i++) {
			if(course.getId()==((Course)com.getItemAt(i)).getId() )  {
				com.setSelectedIndex(i);
			}
		}
	}

}
