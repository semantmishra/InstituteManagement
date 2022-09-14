package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import beans.Address;
import beans.Course;
import beans.EnqueryBean;
import database.DBConnection;
import database.DBInfo;

public class EnqueryDao implements Dao {
	
	public boolean save(EnqueryBean bean) {
		
		try {
			String sql = "INSERT INTO `jdbcgui`.`enquery`(`name`,`fatherName`,`DOB`,`course`,"
					+ "`mobile1`,`mobile2`,`email`,`district`,`state`,`pincode`,`address`,"
					+ "`session_id`,`gender`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";

			PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getFatherName());
			ps.setDate(3, bean.getDOB());
			ps.setInt(4, bean.getCourse().getId());
			ps.setString(5, bean.getMobile1());
			ps.setString(6, bean.getMobile2());
			ps.setString(7, bean.getEmail());
			ps.setInt(8, bean.getAddress().getDistrict().getId());
			ps.setInt(9, bean.getAddress().getState().getId());
			ps.setString(10, bean.getAddress().getPincode());
			ps.setString(11, bean.getAddress().getAdderss());
			ps.setInt(12, bean.getSession().getId());
			ps.setInt(13, bean.getGender());
			if(ps.executeUpdate()==1)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public List<EnqueryBean> getEnquery() {
		List<EnqueryBean> beans= new Vector<EnqueryBean>();
		try {
			Statement statement = DBConnection.getConnection().createStatement();
			String sql ="SELECT enquery.id as id, `name`, `fatherName`, "
					+ "`DOB`, courses.course,courses.id as courseId , "
					+ "`mobile1`, `mobile2`, `email`,"
					+ "`address` FROM `enquery`LEFT JOIN courses ON enquery.course = courses.id";
			ResultSet resultSet= statement.executeQuery(sql);
			 while(resultSet.next())
			 {
				 EnqueryBean bean = new EnqueryBean();
				 Course course = new Course();
				 bean.setId(resultSet.getInt("id"));
				 bean.setName(resultSet.getString("name"));
				 bean.setFatherName(resultSet.getString("fatherName"));
				 bean.setDOB(resultSet.getDate("DOB"));
				 course.setId( resultSet.getInt("courseId"));
				 course.setName( resultSet.getString("course"));
				 bean.setCourse(course);
				 bean.setMobile1(resultSet.getString("mobile1"));
				 bean.setEmail(resultSet.getString("email"));
				 bean.setAddress(new Address("",resultSet.getString("address"), null,null));
				 
				 beans.add(bean);
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beans;
	}

	public  boolean delete(int delId,String sql) {
		try {
			PreparedStatement pst = database.DBConnection.getConnection().prepareStatement("DELETE FROM `enquery` WHERE id=?");
			pst.setInt(1, delId);
			if(pst.executeUpdate()==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
