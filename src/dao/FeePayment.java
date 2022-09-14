package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.FeeBean;
import database.DBConnection;

public class FeePayment {

	public List<FeeBean> getFee(int studentId) {
		List<FeeBean> list = new ArrayList<FeeBean>();
		Connection conn = DBConnection.getConnection();
		try {
			Statement st =  conn.createStatement();
		
			ResultSet r =  st.executeQuery("SELECT `id`, `student_id`, `amount`, `date` FROM `feedetails` WHERE student_id ="+studentId);
			while(r.next()) {
				FeeBean feeBean = new FeeBean();
				feeBean.setId( r.getInt("id") );
				feeBean.setStudentId(r.getInt("student_id") );
				feeBean.setFee(r.getFloat("amount") );
				feeBean.setDate(r.getDate("date") );
				list.add(feeBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public boolean paymentFee(FeeBean payment) {
		try {
			PreparedStatement st = DBConnection.getConnection().prepareStatement("INSERT INTO `feedetails`(`student_id`,`amount`,`date`)VALUES(?,?,?)");
			st.setInt(1, payment.getStudentId());
			st.setFloat(2, payment.getFee());
			st.setDate(3, payment.getDate());
			if(st.executeUpdate()==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
