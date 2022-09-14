package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import database.DBInfo;
import beans.RegisterBean;
import beans.SecurityQustion;

public class RegisterDao {
	
	public boolean register1(RegisterBean bean)
	{
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO  `user` (`name`,`email`,`mobile`,`password`)VALUES(?,?,?,?)");

			st.setString(1, bean.getName());
			st.setString(2, bean.getEmail());
			st.setString(3, bean.getMobile());
			st.setString(4, bean.getPassword());
			
			if(st.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public RegisterBean getUseryEmail(String string) {
		Connection conn = DBConnection.getConnection();
		try {
			RegisterBean bean = new RegisterBean();
			PreparedStatement st = conn.prepareStatement("SELECT user.id as  user_id, name, email,mobile,password,user_type,securityqustion.Qustion,security_qstion_and,isActive"
					+ " FROM `user` left join securityqustion on user.security_qstion_id = securityqustion.id"
					+ " where email = ?");

			st.setString(1,string);
			ResultSet rs= st.executeQuery();
			if(rs.next()) {
				bean.setId(rs.getInt("user_id"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setMobile(rs.getString("mobile"));
				bean.setSecurityQustion(new SecurityQustion(0, rs.getString("Qustion")));
				
				
				return bean;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean resetPassword(RegisterBean bean) {
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement("update user set password=? where email=? and security_qstion_and=? ;");
			st.setString(1, bean.getPassword());
			st.setString(2, bean.getEmail());
			st.setString(3, bean.getSecurityA());
			if(st.executeUpdate()==1) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean register2(RegisterBean rb) {
		try {
			PreparedStatement st = database.DBConnection.getConnection().prepareStatement("UPDATE `user` SET `user_type` = ?,`security_qstion_id` = ?,`security_qstion_and` = ?,`isActive` = ? WHERE email =?;");
			st.setInt(1, rb.getUserType());
			st.setInt(2, rb.getSecurityQustion().getId());
			st.setString(3, rb.getSecurityA());
			st.setInt(4, rb.getIsAction());
			st.setString(5, rb.getEmail());
			return st.executeUpdate()==1?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean changePassword(String[] emailPassword) {
		try {
			PreparedStatement st = database.DBConnection.getConnection().prepareStatement("UPDATE `user` SET `password` = ? WHERE `email` = ? and `password`=?;");
			st.setString(1, emailPassword[2]);
			st.setString(2, emailPassword[0]);
			st.setString(3, emailPassword[1]);
			return st.executeUpdate()==1?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<RegisterBean> getAll() {
		List<RegisterBean> lists = new ArrayList<RegisterBean>();
		try {
			PreparedStatement st = database.DBConnection.getConnection().prepareStatement("SELECT `id` , `name` ,`email`, `mobile`,`password`, `user_type`, `security_qstion_id`, `security_qstion_and`, `isActive` FROM  `user`");
//SELECT `id`, `name`,`email`, `mobile`,`password`, `user_type`, `security_qstion_id`, `security_qstion_and`, `isActive`FROM  `user`;
			ResultSet sr = st.executeQuery();
			while(sr.next()) {
				RegisterBean bean = new RegisterBean();
				bean.setId(sr.getInt("id"));
				bean.setName( sr.getString("name") );
				bean.setEmail( sr.getString("email") );
				bean.setMobile( sr.getString("mobile") );
				bean.setUserType(sr.getInt("user_type"));
				bean.setIsAction(sr.getInt("isActive") );
			  lists.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
}
