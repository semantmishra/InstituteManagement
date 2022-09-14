package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Cities;
import beans.Course;
import beans.EmployeeBean;
import beans.EmployeeType;
import beans.SecurityQustion;
import beans.SessionBean;
import beans.State;
import database.DBConnection;

public class LoadDataDao {

	public List<Course> courseComboBox(String sql) {
			List<Course> courses = new ArrayList<Course>();
			Connection connection = DBConnection.getConnection();
			try {
				Statement statement= connection.createStatement();
				 ResultSet resultSet= statement.executeQuery(sql);
				 while (resultSet.next()) {
					 Course course = new Course();
					 course .setId(resultSet.getInt("id"));
					 course .setName(resultSet.getString("course"));
					 course .setFee(resultSet.getFloat("fee"));
					 course .setActive(resultSet.getInt("active"));
					 courses.add(course);
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return courses;
	}

	
	public List<SessionBean> sessionComboBox(String sql) {
		List<SessionBean> sessions= new ArrayList<SessionBean>();
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement= connection.createStatement();
			 ResultSet resultSet= statement.executeQuery(sql);
			 while (resultSet.next()) {
				 SessionBean session = new SessionBean();
				 session .setId(resultSet.getInt("id"));
				 session .setName(resultSet.getString("name"));
				
				 sessions.add(session);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sessions;
	}
	
	public List<SecurityQustion> securityQustionComboBox() {
		List<SecurityQustion> qustions= new ArrayList<SecurityQustion>();
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement= connection.createStatement();
			 ResultSet resultSet= statement.executeQuery("select id,Qustion from securityqustion");
			 while (resultSet.next()) {
				 SecurityQustion qustion = new SecurityQustion();
				 
				 qustion .setId(resultSet.getInt("id"));
				 qustion .setQustion(resultSet.getString("Qustion"));
				
				 qustions.add(qustion);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qustions;
	}


	public List<State> stateComboBox() {
		String sql = "SELECT `id`, `name` FROM `states`";
		List<State> states= new ArrayList<State>();
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement= connection.createStatement();
			 ResultSet resultSet= statement.executeQuery(sql);
			 while (resultSet.next()) {
				 State state = new State();
				 state .setId(resultSet.getInt("id"));
				 state .setState(resultSet.getString("name"));
				
				 states.add(state);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return states;

	}


	public List<Cities> districComboBox(int stateId) {
		String sql = "SELECT `id`, `name`, `state_id` FROM `cities` where state_id="+stateId;
		List<Cities> cities= new ArrayList<Cities>();
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement= connection.createStatement();
			 ResultSet resultSet= statement.executeQuery(sql);
			 while (resultSet.next()) {
				 Cities citie = new Cities();
				 citie.setId(resultSet.getInt("id"));
				 citie.setCities(resultSet.getString("name"));
				 citie.setState_id(resultSet.getInt("state_id"));
				 cities.add(citie);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cities;
	}
	
	public List<EmployeeType> empTypeComboBox() {
		List<EmployeeType> empType = new ArrayList<EmployeeType>();
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement= connection.createStatement();
			 ResultSet resultSet= statement.executeQuery("SELECT `id`,`employeeType`,`salary` FROM  `employeetype`");
			 while (resultSet.next()) {
				 EmployeeType employeeType = new EmployeeType();
				 employeeType.setId(resultSet.getInt("id"));
				 employeeType.setEmpType(resultSet.getString("employeeType"));
				 employeeType.setSalaray(resultSet.getFloat("salary"));
				 empType.add(employeeType);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empType;
}
	
}
