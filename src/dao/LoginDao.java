package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;

import beans.RegisterBean;

public class LoginDao {
	RegisterBean loginBean;
	public boolean login(RegisterBean loginBean) {
		
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT name , email, user_type FROM user where email=? and password=? and user_type=?");
			pst .setString(1, loginBean.getEmail());
			pst .setString(2, loginBean.getPassword());
			
			pst .setInt(3, loginBean.getUserType());
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				loginBean.setUserType(rs.getInt("user_type"));
				loginBean.setName(rs.getString("name"));
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}


}
