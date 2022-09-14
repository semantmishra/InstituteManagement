package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import beans.Address;
import beans.Cities;
import beans.Course;
import beans.SessionBean;
import beans.State;
import beans.StudentBean;
import database.DBConnection;

public class StudentDao implements Dao {

	public boolean save(StudentBean studentBean) {
		
		try {
			int id = 0;
			String sql = "INSERT INTO `students`(`name`, `photo`, "
					+ "`fatherName`, `motherName`, `gender`, `email`, `mobile1`, "
					+ "`mobile2`, `dob`, `doa`, `state_id`, `cities_id`, `address`, "
					+ "`course_id`, `session_id`,`my_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql1="select id from students order by id desc limit 1";

			Connection conn = DBConnection.getConnection();
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs =  ps1.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
			Thread.sleep(5000);		
			PreparedStatement ps = conn.prepareStatement(sql);
			
			String finalId = "ANDRO"+new SimpleDateFormat("YYYYMMdd").format(new Date())+(id+1);
			
			ps.setString(1, studentBean.getName());
			ps.setBinaryStream(2, studentBean.getPhoto(), studentBean.getPhoto().available());
			ps.setString(3, studentBean.getFatherName());
			ps.setString(4, studentBean.getMothername());
			ps.setInt(5, studentBean.getGender());
			ps.setString(6, studentBean.getEmail());
			ps.setString(7, studentBean.getMobile1());
			ps.setString(8, studentBean.getMobile2());
			ps.setDate(9, new java.sql.Date(new Date().getTime()));
			ps.setDate(10, new java.sql.Date(new Date().getTime()));
			ps.setInt(11, studentBean.getAddress().getState().getId());
			ps.setInt(12, studentBean.getAddress().getDistrict().getId());
			ps.setString(13, studentBean.getAddress().getAdderss());
			ps.setInt(14, studentBean.getCourse().getId());
			ps.setInt(15, studentBean.getSession().getId());
			ps.setString(16, finalId);
			

			if (ps.executeUpdate() == 1)
				return true;
			else
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public List<StudentBean> getAllStudent() {
		List<StudentBean> students = new ArrayList<StudentBean>();
		try {

			Statement st = DBConnection.getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT students.id, students.name,"
					+ " `fatherName`, `motherName`, `gender`, `email`,"
					+ " `mobile1`, courses.course, session.name session,my_id FROM `students` "
					+ "LEFT JOIN courses on students.course_id = courses.id  LEFT JOIN session on students.session_id = session.id");

			//int i =resultSet.TYPE_SCROLL_SENSITIVE;
			
			while (resultSet.next()) {	
				StudentBean bean = new StudentBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setFatherName(resultSet.getString("fatherName"));
				bean.setMothername(resultSet.getString("motherName"));
				bean.setGender(resultSet.getInt("gender"));
				bean.setEmail(resultSet.getString("email"));
				bean.setMobile1(resultSet.getString("mobile1"));
				Course course = new Course();
				course.setName(resultSet.getString("course"));
				bean.setCourse(course);
				bean.setSession(new SessionBean(0, resultSet.getString("session")));
				bean.setMyId(resultSet.getString("my_id"));
				students.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public boolean delete(int id,String sql) {
		try {
			PreparedStatement pst = database.DBConnection.getConnection()
					.prepareStatement(sql);
			pst.setInt(1, id);
			if (pst.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public StudentBean getStudent(int studentId) {

		try {

			Statement st = DBConnection.getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT students.id,photo, students.name,"
					+ "`fatherName`, `motherName`, `gender`, `email`,"
					+ "`mobile1`,dob,doa,courses.id as coursse_id, courses.course,courses.fee, "
					+ "address,states.id as state_id,states.name as state_name,"
					+ "session.name as session_name,session.id as session_id, "
					+ "cities.id as citie_id,cities.name as citie_name,my_id FROM `students`"
					+ " LEFT JOIN courses on students.course_id = courses.id"
					+ " left JOIN states on students.state_id= states.id"
					+ " left JOIN session on students.session_id= session.id"
					+ " LEFT JOIN cities on students.cities_id =cities.id where students.id=" + studentId);

			if (resultSet.next()) {
				StudentBean bean = new StudentBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setFatherName(resultSet.getString("fatherName"));
				bean.setMothername(resultSet.getString("motherName"));
				bean.setGender(resultSet.getInt("gender"));
				bean.setMobile1(resultSet.getString("mobile1"));
				bean.setEmail(resultSet.getString("email"));
				
				bean.setCourse(new Course(resultSet.getInt("coursse_id"), 0, resultSet.getString("course"), resultSet.getFloat("fee")));
				
				bean.setDoa(resultSet.getDate("doa"));
				bean.setDOB(resultSet.getDate("dob"));

				bean.setPhoto(resultSet.getBinaryStream("photo"));
				Cities cities = new Cities(resultSet.getInt("citie_id"), 0, resultSet.getString("citie_name"));
				Address addr = new Address(resultSet.getString("address"), null,
						new State(resultSet.getInt("state_id"), resultSet.getString("state_name")), cities);
				bean.setAddress(addr);
				bean.setSession(new SessionBean(resultSet.getInt("session_id"), resultSet.getString("session_name")));
				bean.setMyId(resultSet.getString("my_id"));
				return bean;
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<StudentBean> getStudentsBySession(int session_id) {

		try {
			List<StudentBean> studentBeans = new ArrayList<StudentBean>();
			Statement st = DBConnection.getConnection().createStatement();
			ResultSet resultSet = st.executeQuery("SELECT students.id,photo, students.name,"
					+ "`fatherName`, `motherName`, `gender`, `email`,"
					+ "`mobile1`,dob,doa,courses.id as coursse_id, courses.course,courses.fee, "
					+ "address,states.id as state_id,states.name as state_name,"
					+ "session.name as session_name,session.id as session_id, "
					+ "cities.id as citie_id,cities.name as citie_name,my_id FROM `students`"
					+ " LEFT JOIN courses on students.course_id = courses.id"
					+ " left JOIN states on students.state_id= states.id"
					+ " left JOIN session on students.session_id= session.id"
					+ " LEFT JOIN cities on students.cities_id =cities.id where students.session_id=" + session_id);

			while (resultSet.next()) {
				StudentBean bean = new StudentBean();
				bean.setId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setFatherName(resultSet.getString("fatherName"));
				bean.setMothername(resultSet.getString("motherName"));
				bean.setGender(resultSet.getInt("gender"));
				bean.setMobile1(resultSet.getString("mobile1"));
				bean.setEmail(resultSet.getString("email"));
				
				bean.setCourse(new Course(resultSet.getInt("coursse_id"), 0, resultSet.getString("course"), resultSet.getFloat("fee")));
				
				bean.setDoa(resultSet.getDate("doa"));
				bean.setDOB(resultSet.getDate("dob"));

				bean.setPhoto(resultSet.getBinaryStream("photo"));
				Cities cities = new Cities(resultSet.getInt("citie_id"), 0, resultSet.getString("citie_name"));
				Address addr = new Address(resultSet.getString("address"), null,
						new State(resultSet.getInt("state_id"), resultSet.getString("state_name")), cities);
				bean.setAddress(addr);
				bean.setSession(new SessionBean(resultSet.getInt("session_id"), resultSet.getString("session_name")));
				bean.setMyId(resultSet.getString("my_id"));
				studentBeans.add(bean);
			}
			
			return studentBeans;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean update(StudentBean studentBean) {
		try {
			String sql="";
			if (studentBean.getPhoto()==null){
				sql = "UPDATE students "
						+ "SET name = ? ,fatherName = ?,motherName =?,gender = ?,email = ?,"
						+ "mobile1 = ?,mobile2 = ?,dob = ?,doa = ?,state_id = ?,cities_id = ?,"
						+ "address = ?,course_id = ?,session_id = ? WHERE id = ?";
			}else {
				sql = "UPDATE `students` "
						+ "SET `name` = ?,`fatherName` = ?,`motherName` =?,`gender` = ?,`email` = ?,"
						+ "`mobile1` = ?,`mobile2` = ?,`dob` = ?,`doa` = ?,`state_id` = ?,`cities_id` = ?,"
						+ "`address` = ?,`course_id` = ?,`session_id` = ?,`photo` = ? WHERE `id` = ?";
			}
			PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
			ps.setString(1, studentBean.getName());			
			ps.setString(2, studentBean.getFatherName());
			ps.setString(3, studentBean.getMothername());
			ps.setInt(4, studentBean.getGender());
			ps.setString(5, studentBean.getEmail());
			ps.setString(6, studentBean.getMobile1());
			ps.setString(7, studentBean.getMobile2());
			ps.setDate(8, studentBean.getDOB());
			ps.setDate(9, studentBean.getDoa());
			ps.setInt(10, studentBean.getAddress().getState().getId());
			ps.setInt(11, studentBean.getAddress().getDistrict().getId());
			ps.setString(12, studentBean.getAddress().getAdderss());
			ps.setInt(13, studentBean.getCourse().getId());
			ps.setInt(14, studentBean.getSession().getId());
			if (studentBean.getPhoto()!=null){
				ps.setBinaryStream(15, studentBean.getPhoto(), studentBean.getPhoto().available());
				ps.setInt(16,studentBean.getId());
			}else {
				ps.setInt(15,studentBean.getId());
			}
				
			if (ps.executeUpdate() == 1)
				return true;
			else
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		return false;
	}

}
