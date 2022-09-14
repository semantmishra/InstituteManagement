package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Address;
import beans.Cities;
import beans.Course;
import beans.EmployeeBean;
import beans.State;
import database.DBConnection;

public class EmployeeDao implements Dao {
	public boolean save(EmployeeBean employeeBean) {
		try {
			String sql = "INSERT INTO `employees` (`name`,`photo`,`fatherName`,`motherName`,`gender`,`email`,`mobile1`,`mobile2`,`dob`,`doj`,`state_id`,`cities_id`,`address`,`employee_type_id`)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
			ps.setString(1, employeeBean.getName());
			ps.setBinaryStream(2, employeeBean.getPhoto(), employeeBean.getPhoto().available());
			ps.setString(3, employeeBean.getFatherName());
			ps.setString(4, employeeBean.getMothername());
			ps.setInt(5, employeeBean.getGender());
			ps.setString(6, employeeBean.getEmail());
			ps.setString(7, employeeBean.getMobile1());
			ps.setString(8, employeeBean.getMobile2());
			ps.setDate(9, employeeBean.getDOB());
			ps.setDate(10, employeeBean.getDoj());
			ps.setInt(11, employeeBean.getAddress().getState().getId());
			ps.setInt(12, employeeBean.getAddress().getDistrict().getId());
			ps.setString(13, employeeBean.getAddress().getAdderss());
			ps.setInt(14, employeeBean.getEmployeeType().getId());
			return ps.executeUpdate()==1?true:false;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}

	public List<EmployeeBean> getAllEmployees() {
		List<EmployeeBean> students = new ArrayList<EmployeeBean>();
		try {

			Statement st = DBConnection.getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT  employees.`id`,employees.`name` as name, `fatherName`, `motherName`,  `gender`, `email`, `mobile1` , states.name as states_name ,cities.name as cities_name , `address` FROM `employees` LEFT JOIN states on employees.state_id = states.id left join cities on employees.cities_id = cities.id");
			while (resultSet.next()) {
				
				EmployeeBean bean = new EmployeeBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setFatherName(resultSet.getString("fatherName"));
				bean.setMothername(resultSet.getString("motherName"));
				bean.setGender(resultSet.getInt("gender"));
				bean.setEmail(resultSet.getString("email"));
				bean.setMobile1(resultSet.getString("mobile1"));
				Address addre = new Address(
						resultSet.getString("address"),
						"",
						new State(0, resultSet.getString("states_name")),
						new Cities(0, 0, resultSet.getString("cities_name"))
						);
				bean.setAddress(addre);
				students.add(bean);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(int id,String sql) {
		try {
			PreparedStatement pst = database.DBConnection
					.getConnection()
					.prepareStatement(sql);
			pst.setInt(1, id);
			if (pst.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public EmployeeBean getEmployeeById(int employeeId) {

		try {

			PreparedStatement st = DBConnection.getConnection().prepareStatement("SELECT employees.`id`,employees.`name`,`fatherName`,`motherName`,`gender`,`email`,`mobile1`,`mobile2`,`dob`,`doj`,"
					+ "employees.`state_id`,states.name as state_name ,"
					+ "`cities_id`,cities.name as citie_name,`address`,`photo`, "
					+ "`employee_type_id` ,employeetype.employeeType,employeetype.salary "
					+ " FROM `employees` "
					+ "left JOIN states on employees.state_id = states.id "
					+ "left JOIN cities on employees.cities_id = cities.id "
					+ "left JOIN employeetype on employees.employee_type_id = employeetype.id "
					+ "where employees.id = ?");
			
			st.setInt(1, employeeId);
			
			ResultSet resultSet = st.executeQuery();
			
			if (resultSet.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setFatherName(resultSet.getString("fatherName"));
				bean.setMothername(resultSet.getString("motherName"));
				bean.setGender(resultSet.getInt("gender"));
				bean.setMobile1(resultSet.getString("mobile1"));
				bean.setMobile2(resultSet.getString("mobile2"));
				bean.setEmail(resultSet.getString("email"));				
				bean.setDoj(resultSet.getDate("doj"));
				bean.setDOB(resultSet.getDate("dob"));

				bean.setPhoto(resultSet.getBinaryStream("photo"));
				Address addr = new Address(
						resultSet.getString("address"),
						null,
						new State(resultSet.getInt("state_id"), resultSet.getString("state_name")),
						new Cities(resultSet.getInt("cities_id"), 0, resultSet.getString("state_name"))
						);
				bean.setAddress(addr);
				
				return bean;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean update(EmployeeBean employeeBean) {
		try {
			String sql="";
			if (employeeBean.getPhoto()==null){
				sql = "UPDATE `employees`"
						+ "SET `name` =? ,`fatherName` =? ,`motherName` = ?,`gender` =?,`email` = ?,`mobile1` =?,`mobile2` = ?,"
						+ "`dob` = ?,`doj` = ?,`state_id` =? ,`cities_id` =? ,`address` = ?,`employee_type_id` =? WHERE `id` = ?";
			}else {
				sql = "UPDATE `employees`"
						+ "SET `name` =? ,`fatherName` =? ,`motherName` = ?,`gender` =?,`email` = ?,`mobile1` =?,`mobile2` = ?,"
						+ "`dob` = ?,`doj` = ?,`state_id` =? ,`cities_id` =? ,`address` = ?,`employee_type_id` =?, `photo` = ? WHERE `id` = ?";
			}
			PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
			ps.setString(1, employeeBean.getName());			
			ps.setString(2, employeeBean.getFatherName());
			ps.setString(3, employeeBean.getMothername());
			ps.setInt(4, employeeBean.getGender());
			ps.setString(5, employeeBean.getEmail());
			ps.setString(6, employeeBean.getMobile1());
			ps.setString(7, employeeBean.getMobile2());
			ps.setDate(8, employeeBean.getDOB());
			ps.setDate(9, employeeBean.getDoj());
			ps.setInt(10, employeeBean.getAddress().getState().getId());
			ps.setInt(11, employeeBean.getAddress().getDistrict().getId());
			ps.setString(12, employeeBean.getAddress().getAdderss());
			ps.setInt(13, employeeBean.getEmployeeType().getId());
			if (employeeBean.getPhoto()!=null){
				ps.setBinaryStream(14, employeeBean.getPhoto(), employeeBean.getPhoto().available());
				ps.setInt(15,employeeBean.getId());
			}else
				ps.setInt(14,employeeBean.getId());
			
			return ps.executeUpdate() == 1? true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
