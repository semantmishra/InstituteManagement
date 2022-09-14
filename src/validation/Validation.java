package validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.DBConnection;

public class Validation {

	public static boolean isEmpy(String str) {
		if ((str == null) || (str.trim().equals("")))
			return true;
		else
			return false;
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean isEmailCorrect(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
	
	public static boolean isExist(String text,String col,String table) {
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement("SELECT '#' from "+table+" where "+col+" = ?");
			st.setString(1, text);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

}
