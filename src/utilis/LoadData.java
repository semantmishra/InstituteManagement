package utilis;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import beans.Cities;
import beans.Course;
import beans.EmployeeType;
import beans.SessionBean;
import beans.State;
import dao.EnqueryDao;
import dao.LoadDataDao;

public class LoadData {
	
	public void empTypeComboBox(JComboBox<EmployeeType> comboBox) {
		List<EmployeeType>employeeTypes= new LoadDataDao().empTypeComboBox();
		DefaultComboBoxModel<EmployeeType> model = (DefaultComboBoxModel)comboBox.getModel(); //new DefaultComboBoxModel();
		model.addElement(new EmployeeType(0, "Select Employee Type", 0));
		model.addAll(employeeTypes);		
		}
	public void courseComboBox(JComboBox<Course> comboBox) {
		List<Course> courses = new LoadDataDao().courseComboBox("SELECT id,course,active,fee FROM courses WHERE active = 1");
//		DefaultComboBoxModel model = (DefaultComboBoxModel)comboBox.getModel(); //new DefaultComboBoxModel();
//		model.addAll(courses);
//		courses .forEach(course->model.addElement(course.getName()));
		
		courses.add(0,new Course(0,0,"Select course",0));
		courses .forEach(course->comboBox.addItem(course));
		//comboBox.setModel(model);
		}
	public void sessionComboBox(JComboBox<SessionBean> comboBox) {
		List<SessionBean> SessionBean= new LoadDataDao().sessionComboBox("SELECT id,name from session");
		comboBox.addItem(new SessionBean(0, "Select Session"));
		SessionBean .forEach(session->comboBox.addItem(session));
		}
	
	public void stateComboBox(JComboBox<State> state) {
		List<State> states = new LoadDataDao().stateComboBox();
		state.addItem(new State(0, "Select State"));
		states .forEach(stateData->state.addItem(stateData));
	}
	public void citiesComboBox(JComboBox<Cities> distric,int stateId) {
		List<Cities> cities= new LoadDataDao().districComboBox(stateId);
		distric.addItem(new Cities(0, 0, "Select Distric"));
		cities.forEach(citie->distric.addItem(citie));
	}

}
